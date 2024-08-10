package com.example.color_match2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.color_match2.BaseColorSchemeFragment
import com.example.color_match2.Color
import com.example.color_match2.databinding.FragmentComplementaryBinding
import kotlinx.coroutines.launch

class ComplementaryFragment : BaseColorSchemeFragment<FragmentComplementaryBinding>() {

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentComplementaryBinding {
        return FragmentComplementaryBinding.inflate(inflater, container, false)
    }

    override fun updateColors(hex: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val complementaryColors = colorMatcher.getComplementaryColors(hex)
                binding.complementaryColor1.setBackgroundColor(android.graphics.Color.parseColor(complementaryColors[0].hexCode))
                binding.complementaryColor2.setBackgroundColor(android.graphics.Color.parseColor(complementaryColors[1].hexCode))
                binding.complementaryColor1Name.text = complementaryColors[0].name
                binding.complementaryColor2Name.text = complementaryColors[1].name
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
    override suspend fun getCompatibleColors(color1: Color): List<Color> {
        return colorMatcher.getComplementaryColors(color1.hexCode)
    }

    override suspend fun getCompatibleColors(color1: Color, color2: Color): List<Color> {
        // Для комплементарных цветов обычно используются только два цвета
        return listOf(color2)
    }
}