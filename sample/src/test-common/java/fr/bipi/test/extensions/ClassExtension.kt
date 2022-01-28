package fr.bipi.test.extensions

import java.io.IOException

/**
 * Extract file content in resources type directory
 *
 * @param path relative path
 * @param clazz
 *
 * @return file content as a String
 * @exception IllegalStateException
 */
fun Class<*>.getResourceAsString(path: String): String = try {
    this.getResourceAsStream(path)?.readBytes()?.decodeToString() ?: ""
} catch (e: IOException) {
    throw IllegalStateException("Resource loading failure: ${e.message}")
}
