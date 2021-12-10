package com.aula.motivation.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.aula.motivation.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        buttonSave.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id = view.id//recebe o id clicado
        if (id == R.id.buttonSave) {
            handleSaved()//criada para tratar a lógica
        }
    }

    private fun handleSaved() {
        //verificar se o campo editName não está vazio
        //buscando o valor informado
        val name = editName.text.toString()
        //se o valor informado for diferente de vazio
        if (name != "") {
            val shared = this.getSharedPreferences("motivation", Context.MODE_PRIVATE)
            shared.edit().putString("name", name).commit()

            //navegar para a MainActivity
            //intent é uma intenção de começar uma nova activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            //se for vazio informar o usuário
            Toast.makeText(this, "Informe seu nome!", Toast.LENGTH_SHORT).show()
        }
    }
}