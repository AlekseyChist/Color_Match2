import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.color_match2.databinding.FragmentAnalogousBinding
import kotlinx.coroutines.launch

class ComplementaryFragment : BaseColorSchemeFragment<FragmentAnalogousBinding>() {
    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentAnalogousBinding {
        return FragmentAnalogousBinding.inflate(inflater, container, false)
    }

    override fun updateColors(hex: String) {
        lifecycleScope.launch {
            try {
                val analogousColors = colorMatcher.getComplementaryColors(hex)
                binding.analogousColor1.setBackgroundColor(android.graphics.Color.parseColor(analogousColors[0].hexCode))
                binding.analogousColor2.setBackgroundColor(android.graphics.Color.parseColor(analogousColors[1].hexCode))
                binding.analogousColor3.setBackgroundColor(android.graphics.Color.parseColor(analogousColors[2].hexCode))
                binding.analogousColor1Name.text = analogousColors[0].name
                binding.analogousColor2Name.text = analogousColors[1].name
                binding.analogousColor3Name.text = analogousColors[2].name
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}