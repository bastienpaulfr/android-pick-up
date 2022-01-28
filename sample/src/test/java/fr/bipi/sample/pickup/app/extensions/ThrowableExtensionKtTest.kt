package fr.bipi.sample.pickup.app.extensions

import org.amshove.kluent.`should be equal to`
import org.junit.Test

class ThrowableExtensionKtTest {

    @Test
    fun `getting causes of null throwable return empty list`() {
        val t: Throwable? = null
        t.flattenCauses().`should be equal to`(emptyList())
    }

    @Test
    fun `getting causes of single throwable should return list of itself`() {
        val t = Throwable()
        t.flattenCauses().`should be equal to`(listOf(t))
    }

    @Test
    fun `getting causes of several throwable should return a list`() {
        val t1 = Throwable()
        val t2 = Throwable(t1)
        val t3 = Throwable(t2)

        t3.flattenCauses().`should be equal to`(listOf(t3, t2, t1))
    }
}
