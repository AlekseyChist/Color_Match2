package com.example.color_match2

import BaseColorSchemeFragment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.color_match2.databinding.FragmentComplementaryBinding
import kotlinx.coroutines.launch

class ComplementaryFragment : BaseColorSchemeFragment<FragmentComplementaryBinding>() {

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentComplementaryBinding {
        return FragmentComplementaryBinding.inflate(inflater, container, false)
    }

    override fun updateColors(hex: String) {
        lifecycleScope.launch {
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
}