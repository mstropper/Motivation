package com.aula.motivation.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aula.motivation.R
import com.aula.motivation.infra.MotivationConstants
import com.aula.motivation.infra.MotivationConstants.FraseFilter.FELIZ
import com.aula.motivation.infra.MotivationConstants.FraseFilter.INFINITO
import com.aula.motivation.infra.MotivationConstants.FraseFilter.SOL
import com.aula.motivation.repository.Mock
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var nFraseFilter : Int = MotivationConstants.FraseFilter.INFINITO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val shared = this.getSharedPreferences("motivation", Context.MODE_PRIVATE)
        textName.text = shared.getString("name","")

        if (supportActionBar != null){
            supportActionBar!!.hide()
        }

        imageInfinito.setColorFilter(resources.getColor(R.color.colorAccent))
        handleNovaFrase()

        buttonNovaFrase.setOnClickListener(this)
        imageInfinito.setOnClickListener(this)
        imageFeliz.setOnClickListener(this)
        imageSol.setOnClickListener(this)
    }

    override fun onClick(view: View) {

        val id = view.id

        if (id == R.id.buttonNovaFrase) {
            handleNovaFrase();
        } else if (id == R.id.imageInfinito) {
            handleFilter(INFINITO);
        } else if (id == R.id.imageFeliz) {
            handleFilter(FELIZ);
        } else if (id == R.id.imageSol) {
            handleFilter(SOL)
        }
    }


    private fun handleFilter(id:Int){
        //garantindo que as imagens serão brancas
        imageInfinito.setColorFilter(resources.getColor(R.color.white))
        imageFeliz.setColorFilter(resources.getColor(R.color.white))
        imageSol.setColorFilter(resources.getColor(R.color.white))

        when (id) {
            INFINITO -> {
        //deixando as imagens rosa claro ao clicar
                imageInfinito.setColorFilter(resources.getColor(R.color.colorAccent))
                nFraseFilter = MotivationConstants.FraseFilter.INFINITO
            }
            FELIZ -> {
                imageFeliz.setColorFilter(resources.getColor(R.color.colorAccent))
                nFraseFilter = MotivationConstants.FraseFilter.FELIZ
            }
            SOL -> {
                imageSol.setColorFilter(resources.getColor(R.color.colorAccent))
                nFraseFilter = MotivationConstants.FraseFilter.SOL
            }
        }
    }

    private fun handleNovaFrase(){
        textFrase.text = Mock().getFrase(nFraseFilter)
    }

}