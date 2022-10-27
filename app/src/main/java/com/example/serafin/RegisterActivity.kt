package com.example.serafin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.serafin.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var btnBack = findViewById<ImageView>(R.id.btnRegisterBack)
        btnBack.setOnClickListener{
            val loginActivity: Intent = Intent(this, LoginActivity::class.java)
            startActivity(loginActivity)
        }

        var btnSubmit = findViewById<Button>(R.id.btnRegisterSubmit)
        btnSubmit.setOnClickListener{
            val loginActivity: Intent = Intent(this, LoginActivity::class.java)
            startActivity(loginActivity)
        }
    }
}