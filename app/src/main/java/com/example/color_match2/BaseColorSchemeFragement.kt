import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.color_match2.ColorMatcher
import com.example.color_match2.R
import com.example.color_match2.RetrofitClient
import com.skydoves.colorpickerview.ColorPickerView

abstract class BaseColorSchemeFragment<T : ViewBinding> : Fragment() {
    private var _binding: T? = null
    protected val binding get() = _binding!!
    protected val colorMatcher: ColorMatcher by lazy { ColorMatcher(RetrofitClient.instance) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupColorPicker()
    }

    private fun setupColorPicker() {
        binding.root.findViewById<ColorPickerView>(R.id.colorPicker).setColorListener { color ->
            val hex = String.format("#%06X", (0xFFFFFF and color))
            updateColors(hex)
        }
    }

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): T
    abstract fun updateColors(hex: String)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}