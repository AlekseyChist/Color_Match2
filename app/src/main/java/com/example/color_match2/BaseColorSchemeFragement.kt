package com.example.color_match2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.skydoves.colorpickerview.ColorEnvelope
import com.skydoves.colorpickerview.ColorPickerView
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener
import kotlinx.coroutines.launch

abstract class BaseColorSchemeFragment<T : ViewBinding> : Fragment() {
    protected lateinit var binding: T
    protected val colorMatcher: ColorMatcher by lazy { ColorMatcher(RetrofitClient.instance) }

    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var spinner3: Spinner
    private lateinit var colorPicker: ColorPickerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = getViewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setupColorPicker()
        setupSpinner1()
    }

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): T

    private fun initViews() {
        spinner1 = binding.root.findViewById(R.id.spinner_color1)
        spinner2 = binding.root.findViewById(R.id.spinner_color2)
        spinner3 = binding.root.findViewById(R.id.spinner_color3)
        colorPicker = binding.root.findViewById(R.id.colorPicker)
    }

    private fun setupColorPicker() {
        colorPicker.setColorListener(object : ColorEnvelopeListener {
            override fun onColorSelected(envelope: ColorEnvelope, fromUser: Boolean) {
                if (fromUser) {
                    val hex = "#${envelope.hexCode}"
                    updateColors(hex)
                }
            }
        })
    }

    private fun setupSpinner1() {
        val allColors = BaseColors.colors
        val adapter = ColorSpinnerAdapter(requireContext(), allColors)
        spinner1.adapter = adapter

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedColor = allColors[position]
                viewLifecycleOwner.lifecycleScope.launch {
                    setupSpinner2(selectedColor)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private suspend fun setupSpinner2(color1: Color) {
        val compatibleColors = getCompatibleColors(color1)
        val adapter = ColorSpinnerAdapter(requireContext(), compatibleColors)
        spinner2.adapter = adapter
        spinner2.visibility = View.VISIBLE

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedColor = compatibleColors[position]
                viewLifecycleOwner.lifecycleScope.launch {
                    setupSpinner3(color1, selectedColor)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private suspend fun setupSpinner3(color1: Color, color2: Color) {
        val compatibleColors = getCompatibleColors(color1, color2)
        val adapter = ColorSpinnerAdapter(requireContext(), compatibleColors)
        spinner3.adapter = adapter
        spinner3.visibility = View.VISIBLE
    }

    /*private fun generateImage(color1: String, color2: String, color3: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val prompt = "Generate an image of a person wearing clothes in $color1, $color2, and $color3 colors."
                val imageUrl = ChatGptApi.generateImage(prompt)
                // Загрузка изображения с помощью библиотеки Glide
                Glide.with(this@BaseColorSchemeFragment)
                    .load(imageUrl)
                    .into(binding.generatedImageView)
            } catch (e: Exception) {
                // Обработка ошибок
            }
        }
    }*/

    abstract fun updateColors(hex: String)
    abstract suspend fun getCompatibleColors(color1: Color): List<Color>
    abstract suspend fun getCompatibleColors(color1: Color, color2: Color): List<Color>
}