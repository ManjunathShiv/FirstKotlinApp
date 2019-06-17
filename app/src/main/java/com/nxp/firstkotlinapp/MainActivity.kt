package com.nxp.firstkotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.app.TaskStackBuilder
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar : androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)
        toolbar.title = "Greeting"
        toolbar.subtitle = "Welcome"
        var dbPath  = getDatabasePath("UserDatabase").absolutePath
        Log.d(dbPath,"This is room database path")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var msg = ""
        when(item.itemId) {
            R.id.delete -> msg = "Delete"
            R.id.search -> msg = "Search"
            R.id.setting -> msg = "Settings"
            R.id.edit -> msg = "Edit"
            R.id.logout -> msg = "Logout"
        }
        Toast.makeText(applicationContext,msg+" Tapped",Toast.LENGTH_SHORT).show()
        return super.onOptionsItemSelected(item)
    }
}
