package com.andrepc.desafio_elefante.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.andrepc.desafio_elefante.R
import com.andrepc.desafio_elefante.databinding.ActivityMainBinding
import com.andrepc.desafio_elefante.viewmodel.MainViewModel

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
        binding.lifecycleOwner = this

        viewModel.getFacts().observe(this, { facts ->
            println("text: " + facts[0].text)
            println("text: " + facts[1].text)
            println("text: " + facts[2].text)
            println("text: " + facts[3].text)
            println("text: " + facts[4].text)
        })

    }


}