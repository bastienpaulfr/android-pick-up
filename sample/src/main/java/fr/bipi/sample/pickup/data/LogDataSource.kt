package fr.bipi.sample.pickup.data

import android.content.Context
import java.io.File

interface LogDataSource {
    fun getLogFolderForExport(): File
    fun getLogFiles(): Collection<File>
    fun clearFileLog()
    fun plant(context: Context)
}
