package com.fleetmaster.fleetmaster

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.fleetmaster.fleetmaster.register.RegisterUserActivity

class PreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pref = this.getSharedPreferences("register", Context.MODE_PRIVATE)
        val id = pref.getInt("id", -1)
        val name = pref.getString("name", "noName42")

        val intent: Intent
        if(id == -1 || name == "noName42") {
            intent = Intent(this, RegisterUserActivity::class.java)

        } else {
            intent = Intent(this, MainActivity::class.java)
        }

        startActivity(intent)
        finish()
    }
}
