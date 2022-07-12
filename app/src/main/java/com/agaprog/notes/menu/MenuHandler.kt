package com.agaprog.notes.menu

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.MenuItem
import com.agaprog.notes.R
import com.agaprog.notes.config.ConfigActivity
import com.agaprog.notes.list.ListActivity
import com.agaprog.notes.login.LoginActivity

class MenuHandler constructor(val context:Context, val current: String) {
    var intent:Intent? = null

    fun itemHandler(item: MenuItem) {
        when (item.itemId) {
            R.id.configMenu -> openConfig(current)
            R.id.listMenu -> openList(current)
            R.id.loginMenu -> openLogin(current)
        }
    }

    private fun openConfig(current: String) {
        if (current == "config") {
            intent = null
        } else {
            intent = Intent(context, ConfigActivity::class.java)
        }
    }

    private fun openList(current: String) {
        if (current == "list") {
            intent = null
        } else {
            intent = Intent(context, ListActivity::class.java)
        }
    }

    private fun openLogin(current: String) {
        if (current == "login") {
            intent = null
        } else {
            intent = Intent(context, LoginActivity::class.java)
        }
    }
}