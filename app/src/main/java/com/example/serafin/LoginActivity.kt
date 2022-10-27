package com.example.serafin

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // var edtUsername = findViewById<EditText>(R.id.edtLoginUsername)
        // var edtPassword = findViewById<EditText>(R.id.edtLoginPassword)

        var btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener{
            val username = "Sandro" // edtUsername.text.toString()
            val password = "pass" // edtPassword.text.toString()

            if(isLoginValid(username, password)){
                Toast.makeText(this@LoginActivity, "Welcome back $username!", Toast.LENGTH_SHORT).show()

                val mainActivity = Intent(this, MainActivity::class.java)
                startActivity(mainActivity)
            } else {
                Toast.makeText(this@LoginActivity, "Username or Password is incorrect.", Toast.LENGTH_LONG)
            }
        }

        var btnRegister = findViewById<Button>(R.id.btnRegister)
        btnRegister.backgroundTintList = null
        btnRegister.setOnClickListener{
            val registerActivity = Intent(this, RegisterActivity::class.java)
            startActivity(registerActivity)
        };
    }

    private fun isLoginValid(username: String, password: String): Boolean {

        /*

        To-do: Add validation!

         */

        return true;
    }
}