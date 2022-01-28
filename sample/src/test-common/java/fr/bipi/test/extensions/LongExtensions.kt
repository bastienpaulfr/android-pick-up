package fr.bipi.test.extensions

fun Long.sleep() {
    try {
        if (this > 0) {
            Thread.sleep(this)
        }
    } catch (e: InterruptedException) {
        e.printStackTrace()
    }
}
