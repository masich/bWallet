package com.masic.bwallet.login

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.masic.bwallet.R
import com.masic.bwallet.data.Repository
import com.masic.bwallet.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.androidannotations.annotations.EActivity

@EActivity(R.layout.activity_main)
class LoginActivity : AppCompatActivity(), LoginContract.View {
    val TAG = this.javaClass.simpleName

    private var presenter: LoginContract.Presenter? = null

    override fun setDownloadProgress(progress: Int) {
        Log.d(TAG, "setDownloadProgress($progress)")
        pb_login_progress.post {
            pb_login_progress.progress = progress
        }
    }

    override fun getWalletDir() = cacheDir!!

    override fun displayProgressBar(show: Boolean) {
        Log.d(TAG, "displayProgressBar($show)")
        pb_login_progress.post {
            pb_login_progress.visibility = if (show) View.VISIBLE else View.GONE
        }
    }


    override fun getMnemonicCode(): String = et_wallet_seed.text.toString()

    override fun showMessageToUser(messageResId: Int) {
        Log.d(TAG, "showMessageToUser()")
        tv_error_message.post {
            tv_error_message.visibility = View.VISIBLE
            tv_error_message.setText(messageResId)
        }
    }

    override fun next(cls: Class<MainActivity>) {
        val intent = Intent(this@LoginActivity, cls)
        intent.flags = intent.flags or Intent.FLAG_ACTIVITY_NO_HISTORY
        startActivity(intent)
    }

    override fun close() {
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        setContentView(R.layout.activity_login)
        initUi()
        presenter?.viewIsReady()
    }

    private fun initData() {
        presenter = LoginPresenter(this)
    }


    private fun initUi() {
        b_confirm_button.setOnClickListener { _ -> presenter?.onConfirm() }
        tv_create_new_wallet.setOnClickListener { _ -> presenter?.onCreateOrLoadWallet() }
    }

    override fun getAppContext() = this.applicationContext!!
}
