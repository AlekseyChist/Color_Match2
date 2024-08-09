package com.example.color_match2

import BaseColorSchemeFragment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.color_match2.databinding.FragmentTriadicBinding
import kotlinx.coroutines.launch

class TriadicFragment : BaseColorSchemeFragment<FragmentTriadicBinding>() {

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentTriadicBinding {
        return FragmentTriadicBinding.inflate(inflater, container, false)
    }

    override fun updateColors(hex: String) {
        lifecycleScope.launch {
            try {
                val triadicColors = colorMatcher.getTriadicColors(hex)
                binding.triadicColor1.setBackgroundColor(android.graphics.Color.parseColor(triadicColors[0].hexCode))
                binding.triadicColor2.setBackgroundColor(android.graphics.Color.parseColor(triadicColors[1].hexCode))
                binding.triadicColor3.setBackgroundColor(android.graphics.Color.parseColor(triadicColors[2].hexCode))
                binding.triadicColor1Name.text = triadicColors[0].name
                binding.triadicColor2Name.text = triadicColors[1].name
                binding.triadicColor3Name.text = triadicColors[2].name
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}