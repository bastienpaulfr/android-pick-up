package fr.bipi.sample.pickup.data.sources.log

import android.content.Context
import android.os.Environment
import android.util.Log
import fr.bipi.sample.pickup.BuildConfig
import fr.bipi.sample.pickup.data.LogDataSource
import fr.bipi.tressence.base.PriorityTree
import fr.bipi.tressence.file.FileLoggerTree
import timber.log.Timber
import java.io.File

class LogDataSourceImpl : LogDataSource {

    companion object {
        private const val FOLDER_LOGS = "logs"
    }

    private var fileTree: FileLoggerTree? = null

    override fun getLogFiles(): Collection<File> {
        return fileTree?.files ?: emptyList()
    }

    override fun clearFileLog() {
        fileTree?.let {
            it.files.forEach { file ->
                file.writeText("")
                Timber.v("${file.absolutePath} deleted")
            }
        }
    }

    override fun plant(context: Context) {
        when {
            BuildConfig.DEBUG -> Timber.plant(Timber.DebugTree())
            else -> Timber.plant(PriorityTree(Log.WARN))
        }

        // File Log
        plantFileTree(context)
    }

    override fun getLogFolderForExport(): File {
        return Environment.getExternalStorageDirectory()
            .resolve(BuildConfig.APPLICATION_ID)
            .resolve(FOLDER_LOGS)
            .apply {
                if (isFile) {
                    delete()
                }
                if (!isDirectory) {
                    mkdirs()
                }
            }
    }

    private fun plantFileTree(context: Context) {
        provideFileTree(context)?.also {
            Timber.plant(it)
        }
    }

    private fun provideFileTree(context: Context): FileLoggerTree? {
        return try {
            FileLoggerTree.Builder()
                .withDirName(context.filesDir.absolutePath)
                .withMinPriority(Log.VERBOSE).build().also {
                    fileTree = it
                }
        } catch (e: Exception) {
            Timber.w(e)
            null
        }
    }
}
