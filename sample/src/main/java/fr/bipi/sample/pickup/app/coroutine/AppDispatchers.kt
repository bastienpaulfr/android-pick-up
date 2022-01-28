package fr.bipi.sample.pickup.app.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.MainCoroutineDispatcher

interface AppDispatchers {
    val main: MainCoroutineDispatcher
    val io: CoroutineDispatcher
}
