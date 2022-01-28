package fr.bipi.sample.pickup.ui.common.components

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast

/**
 * Class that display a toast in canceling the previous one if any.
 */
class Toaster(
    private val context: Context
) {
    private var toast: Toast? = null

    fun toast(resId: Int, vararg args: Any) {
        toast(context.getString(resId, *args))
    }

    fun toast(m: String) {
        if (Looper.myLooper() == Looper.getMainLooper()) { // we are on ui thread
            showToast(m)
        } else {
            Handler(Looper.getMainLooper()).post {
                showToast(m)
            }
        }
    }

    private fun showToast(m: String) {
        toast?.cancel()
        toast = Toast.makeText(context, m, Toast.LENGTH_SHORT).apply {
            show()
        }
    }
}
