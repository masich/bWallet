package com.masic.bwallet.settings

import android.content.Intent
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import com.masic.bwallet.R
import com.masic.bwallet.StartActivity
import com.masic.bwallet.login.LoginActivity
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class SettingsActivity : AppCompatActivity(), SettingsContract.View {
    private lateinit var presenter: SettingsContract.Presenter
    val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setupActionBar()
        initData()
        initUi()
        presenter.viewIsReady()
    }

    override fun displayAddressesLabel(display: Boolean) {
        tv_addresses_label.visibility = if (display) View.VISIBLE else View.GONE
    }

    override fun displayAllOfUsedAddresses(addresses: List<String>) {
        if (!addresses.isEmpty()) {
            val array = addresses.toTypedArray()
            val adapter: ListAdapter = ArrayAdapter<String>(this.applicationContext, android.R.layout.simple_list_item_1, array)
            lv_used_addreses.adapter = adapter
        }
    }

    private fun initData() {
        presenter = SettingsPresenter(this)
    }

    private fun initUi() {
        b_log_out_button.setOnClickListener {
            Log.d(TAG, "log out pressed")
            presenter.onLogOut()
        }
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar_tb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun next(cls: Class<StartActivity>) {
        val intent = Intent(this@SettingsActivity, cls)
        intent.flags = intent.flags or Intent.FLAG_ACTIVITY_NO_HISTORY
        startActivity(intent)
    }
}
