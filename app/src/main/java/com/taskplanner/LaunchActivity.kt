package com.taskplanner

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LaunchActivity : AppCompatActivity(){

    var TOKEN_KEY = "TOKEN_KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var shared = getSharedPreferences(getString(R.string.preference_file_key),Context.MODE_PRIVATE)
        var intent: Intent? = null
        if(shared.contains(TOKEN_KEY)){
            intent = Intent(this,MainActivity::class.java)
        }else{
            intent = Intent(this,LoginActivity::class.java)
        }
        startActivity(intent);
        finish()
    }

}