package com.example.motivation.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.motivation.R
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.infra.SecurityPreferences
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener  {
    private lateinit var mSharedPreferences: SecurityPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSharedPreferences = SecurityPreferences(this)
        setContentView(R.layout.activity_splash)
        if (supportActionBar!=null) {
            supportActionBar!!.hide()
        }
        buttonSave.setOnClickListener(this)
        verifyName()
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.buttonSave){
            handleSave()
        }
    }
    private fun handleSave(){
        val name = editName.text.toString()
        if(name != ""){
            mSharedPreferences.storeString(MotivationConstants.KEY.PERSON_NAME, name)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else{
            Toast.makeText(this,"Por favor, Informe seu nome!", Toast.LENGTH_SHORT).show()
        }
    }
    private fun verifyName(){
        val name = mSharedPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        if (name != ""){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}