package fr.bipi.sample.pickup.app.extensions

fun Throwable?.flattenCauses(): List<Throwable> = this?.run {
    mutableListOf(this).apply {
        if (cause != null) {
            addAll(cause.flattenCauses())
        }
    }
} ?: emptyList()
