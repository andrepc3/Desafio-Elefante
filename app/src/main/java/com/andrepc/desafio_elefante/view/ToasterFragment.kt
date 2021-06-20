package com.andrepc.desafio_elefante.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.andrepc.desafio_elefante.R
import com.andrepc.desafio_elefante.databinding.FragmentToasterBinding

/**
 * Created by André Castro
 */

class ToasterFragment : DialogFragment() {

    companion object {

        const val MESSAGE = "Api error"

        /**
         * Instance of fragment message param
         */
        fun getFragmentInstance(message: String): ToasterFragment{
            val fragment = ToasterFragment()

            val bundle = Bundle().apply {
                putString(MESSAGE, message)
            }

            fragment.arguments = bundle

            return fragment
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentToasterBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_toaster, container, false)
        binding.lifecycleOwner = this

        binding.toasterText.text = arguments?.getString(MESSAGE)

        binding.toasterButton.setOnClickListener{
            this.dismiss()
        }

        return binding.root
    }

}