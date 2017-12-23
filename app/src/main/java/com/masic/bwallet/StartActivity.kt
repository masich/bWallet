package com.masic.bwallet

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.masic.bwallet.data.Repository
import com.masic.bwallet.data.api.BitcoinApiHelper
import com.masic.bwallet.login.LoginActivity
import com.masic.bwallet.main.MainActivity
import org.bitcoinj.core.listeners.DownloadProgressTracker
import org.jetbrains.anko.toast

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRepo()
        val dialog: ProgressDialog
        if (!Repository.instance.isWalletOnStorage()) {
            val intent = Intent(this@StartActivity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        } else {
            if (BitcoinApiHelper.isCreated && BitcoinApiHelper.instance.walletLoaded) {
                goToMainActivity()
            } else {
                dialog = ProgressDialog.show(this, null, resources.getString(R.string.loading))
                BitcoinApiHelper.init(this.applicationContext)
                Log.d("cache dir: ", (this.applicationContext.cacheDir.absolutePath))
                BitcoinApiHelper.instance.setDownloadListenerToKit(object : DownloadProgressTracker() {
                    override fun doneDownload() {
                        super.doneDownload()
                        dialog.dismiss()
                        goToMainActivity()
                    }
                })
                BitcoinApiHelper.instance.loadOrCreateNewWallet()
            }
        }
    }

    private fun goToMainActivity() {
        val intent = Intent(this@StartActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    private fun initRepo() {
        Repository.init(applicationContext)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(this.javaClass.simpleName, "onDestroy")
    }

    override fun onStop() {
        super.onStop()
        Log.d(this.javaClass.simpleName, "onDestroy")
    }
}
