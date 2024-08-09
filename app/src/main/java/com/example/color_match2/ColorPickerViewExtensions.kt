import com.skydoves.colorpickerview.ColorPickerView
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener

fun ColorPickerView.setColorListener(listener: (Int) -> Unit) {
    setColorListener(ColorEnvelopeListener { envelope, fromUser ->
        listener(envelope.color)
    })
}