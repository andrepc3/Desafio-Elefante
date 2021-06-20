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

        onStepClick() // Click handler

    }

    /**
     * Check if the stored date is expired and call insert local step
     */
    private fun checkExpirationDate() {
        val isDateExpired = MutableLiveData<Boolean>()

        viewModel.getElephant().observe(this, { listElephant ->

            viewModel.getElephant().removeObservers(this)

            if (listElephant.isNotEmpty()) {
                for (i in listElephant.indices) {
                    val position = listElephant[i]?.position
                    if (position != null) {
                        //Change the initial elephant position
                        changeElephantStepPosition(position, false)
                    }

                    val date = listElephant[i]?.date
                    if (date != null) {
                        isDateExpired.value = isAfterCurrentDate(date)
                    }

                }
            } else {
                insertElephant()
                isDateExpired.value = true
            }
        })

        isDateExpired.observe(this, { value ->
            if (value == true) {
                getStepMessage()
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
     * Request Api and update the message of "Step"
     */
    private fun getStepMessage() {
        viewModel.getFacts().observe(this, { listFacts ->
            if (listFacts.isNotEmpty()) {
                for (i in listFacts.indices) {
                    val position = i + 1
                    val text = listFacts[i].text
                    viewModel.insertStep(position, text)
                }
                updateElephantDate()
                showToast("Api ok")
            } else {
                showToast("Api error")
            }
        })
    }

    /**
     * Add a first elephant to Room Database
     */
    private fun insertElephant() {
        val currentDate = viewModel.getCurrentDate()

        viewModel.insertElephant(1, currentDate)
    }

    /**
     * Update the expiration date on Room Database with the current date
     */
    private fun updateElephantDate() {
        val currentDate = viewModel.getCurrentDate()

        viewModel.updateElephantDate(currentDate)
    }

    /**
     * Update the actual elephant position on Room Database
     */
    private fun updateElephantPosition(position: Int) {
        viewModel.updateElephantPosition(position)
    }

    /**
     * Change the actual elephant position on step click
     */
    private fun onStepClick() {
        viewModel.getClickedPosition().observe(this, { position ->
            updateElephantPosition(position)
            changeElephantStepPosition(position, true)
        })
    }

    /**
     * Change the elephant position on and the visibility of the number
     * And define if the Toaster will be shown
     */
    private fun changeElephantStepPosition(position: Int, showMessage: Boolean) {
        when (position) {
            1 -> {
                binding.elephantStep1.visibility = View.VISIBLE
                binding.elephantStep2.visibility = View.INVISIBLE
                binding.elephantStep3.visibility = View.INVISIBLE
                binding.elephantStep4.visibility = View.INVISIBLE
                binding.elephantStep5.visibility = View.INVISIBLE

                binding.numStep1.visibility = View.INVISIBLE
                binding.numStep2.visibility = View.VISIBLE
                binding.numStep3.visibility = View.VISIBLE
                binding.numStep4.visibility = View.VISIBLE
                binding.numStep5.visibility = View.VISIBLE

                if (showMessage) {
                    getStepMessage(1)
                }
            }
            2 -> {
                binding.elephantStep1.visibility = View.INVISIBLE
                binding.elephantStep2.visibility = View.VISIBLE
                binding.elephantStep3.visibility = View.INVISIBLE
                binding.elephantStep4.visibility = View.INVISIBLE
                binding.elephantStep5.visibility = View.INVISIBLE

                binding.numStep1.visibility = View.VISIBLE
                binding.numStep2.visibility = View.INVISIBLE
                binding.numStep3.visibility = View.VISIBLE
                binding.numStep4.visibility = View.VISIBLE
                binding.numStep5.visibility = View.VISIBLE

                if (showMessage) {
                    getStepMessage(2)
                }
            }
            3 -> {
                binding.elephantStep1.visibility = View.INVISIBLE
                binding.elephantStep2.visibility = View.INVISIBLE
                binding.elephantStep3.visibility = View.VISIBLE
                binding.elephantStep4.visibility = View.INVISIBLE
                binding.elephantStep5.visibility = View.INVISIBLE

                binding.numStep1.visibility = View.VISIBLE
                binding.numStep2.visibility = View.VISIBLE
                binding.numStep3.visibility = View.INVISIBLE
                binding.numStep4.visibility = View.VISIBLE
                binding.numStep5.visibility = View.VISIBLE

                if (showMessage) {
                    getStepMessage(3)
                }
            }
            4 -> {
                binding.elephantStep1.visibility = View.INVISIBLE
                binding.elephantStep2.visibility = View.INVISIBLE
                binding.elephantStep3.visibility = View.INVISIBLE
                binding.elephantStep4.visibility = View.VISIBLE
                binding.elephantStep5.visibility = View.INVISIBLE

                binding.numStep1.visibility = View.VISIBLE
                binding.numStep2.visibility = View.VISIBLE
                binding.numStep3.visibility = View.VISIBLE
                binding.numStep4.visibility = View.INVISIBLE
                binding.numStep5.visibility = View.VISIBLE

                if (showMessage) {
                    getStepMessage(4)
                }
            }
            5 -> {
                binding.elephantStep1.visibility = View.INVISIBLE
                binding.elephantStep2.visibility = View.INVISIBLE
                binding.elephantStep3.visibility = View.INVISIBLE
                binding.elephantStep4.visibility = View.INVISIBLE
                binding.elephantStep5.visibility = View.VISIBLE

                binding.numStep1.visibility = View.VISIBLE
                binding.numStep2.visibility = View.VISIBLE
                binding.numStep3.visibility = View.VISIBLE
                binding.numStep4.visibility = View.VISIBLE
                binding.numStep5.visibility = View.INVISIBLE

                if (showMessage) {
                    getStepMessage(5)
                }
            }
        }
    }

    /**
     * Get step from Room Database and show message by the position
     */
    private fun getStepMessage(position: Int) {
        viewModel.getStep().observe(this, { listStep ->
            if (listStep.isNotEmpty()) {
                val message = listStep[position - 1]?.message
                if (message != null) {
                    showToasterFragment(message)
                    //showToast(message)
                }
            }
        })
    }

    /**
     * Show Toaster Dialog Fragment with the param message
     */
    private fun showToasterFragment(message: String) {

        println("show** $message")
        val dialog = ToasterFragment.getFragmentInstance(message)

        dialog.show(supportFragmentManager, "toasterFragmentDialog")
    }

    /**
     * Show Toast with the param string
     */
    private fun showToast(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }

}