package com.andrepc.desafio_elefante.view

import android.os.Bundle
import android.view.View
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

        onStepClick()
    }

    /**
     * Check if the stored date is expired and call update Elefante
     */
    private fun checkExpirationDate() {
        val isDateExpired = MutableLiveData<Boolean>()

        viewModel.getExpiration().observe(this, { listExpiration ->

            viewModel.getExpiration().removeObservers(this)

            if (listExpiration.isNotEmpty()) {
                for (i in listExpiration.indices) {
                    isDateExpired.value = isAfterCurrentDate(listExpiration[i].date)
                }
            } else {
                updateExpirationDate()
            }
        })

        isDateExpired.observe(this, { value ->
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
            if (listFacts != null) {
                for (i in listFacts.indices) {
                    val texto = listFacts[i].text
                    val posicao = i + 1
                    viewModel.insertElefante(posicao, texto)
                    updateExpirationDate()
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
     * Change the elephant position on step click and the visibility of the number
     */
    private fun onStepClick() {
        viewModel.getClickedPosition().observe(this, { posicao ->
            when (posicao) {
                1 -> {
                    binding.elefanteStep1.visibility = View.VISIBLE
                    binding.elefanteStep2.visibility = View.INVISIBLE
                    binding.elefanteStep3.visibility = View.INVISIBLE
                    binding.elefanteStep4.visibility = View.INVISIBLE
                    binding.elefanteStep5.visibility = View.INVISIBLE

                    binding.numStep1.visibility = View.INVISIBLE
                    binding.numStep2.visibility = View.VISIBLE
                    binding.numStep3.visibility = View.VISIBLE
                    binding.numStep4.visibility = View.VISIBLE
                    binding.numStep5.visibility = View.VISIBLE

                    getElefanteText(1)
                }
                2 -> {
                    binding.elefanteStep1.visibility = View.INVISIBLE
                    binding.elefanteStep2.visibility = View.VISIBLE
                    binding.elefanteStep3.visibility = View.INVISIBLE
                    binding.elefanteStep4.visibility = View.INVISIBLE
                    binding.elefanteStep5.visibility = View.INVISIBLE

                    binding.numStep1.visibility = View.VISIBLE
                    binding.numStep2.visibility = View.INVISIBLE
                    binding.numStep3.visibility = View.VISIBLE
                    binding.numStep4.visibility = View.VISIBLE
                    binding.numStep5.visibility = View.VISIBLE

                    getElefanteText(2)
                }
                3 -> {
                    binding.elefanteStep1.visibility = View.INVISIBLE
                    binding.elefanteStep2.visibility = View.INVISIBLE
                    binding.elefanteStep3.visibility = View.VISIBLE
                    binding.elefanteStep4.visibility = View.INVISIBLE
                    binding.elefanteStep5.visibility = View.INVISIBLE

                    binding.numStep1.visibility = View.VISIBLE
                    binding.numStep2.visibility = View.VISIBLE
                    binding.numStep3.visibility = View.INVISIBLE
                    binding.numStep4.visibility = View.VISIBLE
                    binding.numStep5.visibility = View.VISIBLE

                    getElefanteText(3)
                }
                4 -> {
                    binding.elefanteStep1.visibility = View.INVISIBLE
                    binding.elefanteStep2.visibility = View.INVISIBLE
                    binding.elefanteStep3.visibility = View.INVISIBLE
                    binding.elefanteStep4.visibility = View.VISIBLE
                    binding.elefanteStep5.visibility = View.INVISIBLE

                    binding.numStep1.visibility = View.VISIBLE
                    binding.numStep2.visibility = View.VISIBLE
                    binding.numStep3.visibility = View.VISIBLE
                    binding.numStep4.visibility = View.INVISIBLE
                    binding.numStep5.visibility = View.VISIBLE

                    getElefanteText(4)
                }
                5 -> {
                    binding.elefanteStep1.visibility = View.INVISIBLE
                    binding.elefanteStep2.visibility = View.INVISIBLE
                    binding.elefanteStep3.visibility = View.INVISIBLE
                    binding.elefanteStep4.visibility = View.INVISIBLE
                    binding.elefanteStep5.visibility = View.VISIBLE

                    binding.numStep1.visibility = View.VISIBLE
                    binding.numStep2.visibility = View.VISIBLE
                    binding.numStep3.visibility = View.VISIBLE
                    binding.numStep4.visibility = View.VISIBLE
                    binding.numStep5.visibility = View.INVISIBLE

                    getElefanteText(5)
                }
            }
        })
    }

    /**
     * Get "Elefante" from Room Database and show message by the step position
     */
    private fun getElefanteText(posicao: Int) {
        viewModel.getElefante().observe(this, { listElefante ->
            showToast(listElefante[posicao - 1].texto)
        })
    }

    /**
     * Show Toaster with the param message
     */
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}