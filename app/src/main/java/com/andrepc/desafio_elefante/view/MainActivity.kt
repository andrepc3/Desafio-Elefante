package com.andrepc.desafio_elefante.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import com.andrepc.desafio_elefante.R
import com.andrepc.desafio_elefante.databinding.ActivityMainBinding
import com.andrepc.desafio_elefante.viewmodel.MainViewModel

/**
 * Created by André Castro
 */

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        // init ViewModel
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // init DataBinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        checkExpirationDate()

        binding.imgStep1.setOnClickListener {
            viewModel.getFacts().observe(this, { facts ->
                println("text: " + facts[0].text)
                println("text: " + facts[1].text)
                println("text: " + facts[2].text)
                println("text: " + facts[3].text)
                println("text: " + facts[4].text)
            })
        }
    }

    /**
     * Check if the stored date is expired and call update Elefante
     */
    private fun checkExpirationDate() {
        val isDateExpired = MutableLiveData<Boolean>()

        viewModel.getExpiration().observe(this, { listExpiration ->
            println("indices** " + listExpiration.indices.toString())
            if (listExpiration.isNotEmpty()) {
                for (i in listExpiration.indices) {
                    println("getExpiration** " + listExpiration[i].date)
                    isDateExpired.value = isAfterCurrentDate(listExpiration[i].date)
                }
            } else {
                updateExpirationDate()
            }
        })

        isDateExpired.observe(this, { value ->
            println("isDateExpired** $value")
            if (value == true) {
                updateTextoElefante()
            }
        })
    }

    /**
     * Return if stored date param is after the current date
     */
    private fun isAfterCurrentDate(storedDate: String): Boolean {
        return viewModel.isAfterCurrentDate(storedDate)
    }

    /**
     * Request Api and update the text of "Elefante"
     */
    private fun updateTextoElefante() {
        viewModel.getFacts().observe(this, { listFacts ->
            println("getFacts**")
            if (listFacts != null) {
                for (i in listFacts.indices) {
                    val texto = listFacts[i].text
                    val posicao = i + 1
                    viewModel.updateTextoElefante(texto, posicao)
                    //updateExpirationDate()
                }
                showToast("Api ok")
            } else {
                showToast("Api error")
            }
        })
    }

    /**
     * Update the expiration date with the current date
     */
    private fun updateExpirationDate() {
        val currentDate = viewModel.getCurrentDate()

        viewModel.insertExpiration(currentDate)
    }

    /**
     * Show Toaster with the param message
     */
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}