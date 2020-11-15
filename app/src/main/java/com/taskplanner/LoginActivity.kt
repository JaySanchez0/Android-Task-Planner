package com.taskplanner

import android.content.Context
import android.content.Intent
import android.os.AsyncTask.execute
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.taskplanner.model.User
import com.taskplanner.service.LoginService
import com.taskplanner.service.Token
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class LoginActivity : AppCompatActivity(){
    var loginService = Retrofit.Builder()
        .baseUrl("http:/10.0.2.2:8080/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build().create(LoginService::class.java)
    var executorService: ExecutorService = Executors.newFixedThreadPool( 1 );

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
    }

    fun login(view: View){
        var emailEdit:EditText = findViewById(R.id.editTextTextPersonName)
        var passwordEdit:EditText = findViewById(R.id.editTextTextPassword)
        var user = User(emailEdit.text.toString(),passwordEdit.text.toString())
        executorService.execute {
            login(user)
        }
    }

    fun login(user:User){
        var token:Token? = loginService.getToken(user).execute().body()
        var shared = getSharedPreferences( getString( R.string.preference_file_key ), Context.MODE_PRIVATE )
        var editor = shared.edit()
        if(token!=null) {
            editor.putString("TOKEN_KEY",token.accessToken);
            editor.commit()
            println("------------ Token -------"+token.accessToken)
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}