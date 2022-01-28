package fr.bipi.sample.pickup.app.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import timber.log.Timber

fun Fragment.requireActionbar() = requireActivity().requireActionbar()

fun Fragment.findNavController(resId: Int) = try {
    Navigation.findNavController(requireActivity(), resId)
} catch (e: Exception) {
    Timber.w(e)
    null
}
