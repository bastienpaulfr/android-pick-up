package fr.bipi.sample.pickup.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.bipi.sample.pickup.app.coroutine.AppDispatchers
import fr.bipi.sample.pickup.data.LogDataSource
import kotlinx.coroutines.launch
import timber.log.Timber

class AppSettingsViewModel(
    private val logDataSource: LogDataSource,
    private val dispatchers: AppDispatchers
) : ViewModel() {

    val exportState: LiveData<ExportLogState>
        get() {
            return _exportState
        }
    private val _exportState: MutableLiveData<ExportLogState> = MutableLiveData()

    fun exportLogs() {
        viewModelScope.launch(dispatchers.io) {
            _exportState.postValue(ExportLogState.Running)

            logDataSource.getLogFolderForExport().also { folder ->
                logDataSource.getLogFiles().forEach { log ->
                    folder.resolve("${log.name}.logcat").apply {
                        Timber.v("Copying ${log.absolutePath} to $absolutePath")
                        if (exists()) {
                            delete()
                        }
                        log.copyTo(this)
                    }
                }
            }

            _exportState.postValue(ExportLogState.Done)
        }
    }

    fun clearLogs() {
        logDataSource.clearFileLog()
    }

    sealed class ExportLogState {
        object Running : ExportLogState()
        object Done : ExportLogState()
    }
}
