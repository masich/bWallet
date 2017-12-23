package com.masic.bwallet.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.masic.bwallet.R
import com.masic.bwallet.main.fragments.receive.ReceiveFragment
import com.masic.bwallet.main.fragments.transfer.TransferFragment
import com.masic.bwallet.main.fragments.wallet.WalletFragment
import com.masic.bwallet.settings.SettingsActivity
import com.masic.bwallet.utils.replaceFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), MainContract.View {
    val TAG = this.javaClass.simpleName

    private var presenter: MainContract.Presenter? = null

    override fun showReceiveFragment() {
        this.replaceFragment(ReceiveFragment.newInstance(), R.id.fl_container)
    }

    override fun showTransferFragment() {
        this.replaceFragment(TransferFragment.newInstance(), R.id.fl_container)
    }

    override fun showWalletFragment() {
        this.replaceFragment(WalletFragment.newInstance(), R.id.fl_container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initUi()
        setSupportActionBar(toolbar_tb)
        presenter?.viewIsReady()
    }

    private fun initData() {
        presenter = MainPresenter(this)
    }

    private fun initUi() {
        bottom_navigation.setOnNavigationItemSelectedListener(
                BottomNavigationView.OnNavigationItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.navigation_receive_btc -> {
                            Log.d(TAG, "nav Receive pressed.")
                            presenter?.onReceive()
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.navigation_wallet -> {
                            Log.d(TAG, "nav Wallet pressed.")
                            presenter?.onWallet()
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.navigation_transfer_btc -> {
                            Log.d(TAG, "nav Transfer pressed.")
                            presenter?.onTransfer()
                            return@OnNavigationItemSelectedListener true
                        }
                    }
                    false
                })
        bottom_navigation.selectedItemId = R.id.navigation_wallet
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val fragment = supportFragmentManager.findFragmentById(R.id.fl_container)
        if (data != null) {
            fragment.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_settings -> {
                Log.d(TAG, "Action menu settings pressed.")
                toast("Action menu settings pressed.")
                val intent = Intent(this@MainActivity, SettingsActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return false
    }


//    override fun onSaveInstanceState(outState: Bundle?) {
//        super.onSaveInstanceState(outState)
//        outState!!.putInt("someVarA", someVarA)
//        outState.putString("someVarB", someVarB)
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        someVarA = savedInstanceState.getInt("someVarA")
//        someVarB = savedInstanceState.getString("someVarB")
//    }

}
