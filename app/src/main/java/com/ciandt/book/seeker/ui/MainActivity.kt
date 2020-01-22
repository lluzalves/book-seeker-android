package com.ciandt.book.seeker.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.ciandt.book.seeker.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        Navigation.findNavController(
            this,
            R.id.nav_host_fragment
        ).navigate(R.id.searchFragment)
        search.setOnClickListener(this)
        appinfo.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.search -> {
                Navigation.findNavController(
                    this,
                    R.id.nav_host_fragment
                ).popBackStack()
                Navigation.findNavController(
                    this,
                    R.id.nav_host_fragment
                ).navigate(R.id.searchFragment)
            }
            R.id.appinfo -> {
                Toast.makeText(this, getString(R.string.app_info), Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    fun hideKeyboard() {
        val view = window.currentFocus
        if (view != null) {
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}
