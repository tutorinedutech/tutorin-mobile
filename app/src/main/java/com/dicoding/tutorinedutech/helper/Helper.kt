package com.dicoding.tutorinedutech.helper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Helper {

    // TODO: update safeDate, try not to use safeDate (use the real time from BE)
    fun formatDate(date: Date?, format: String = "dd-MM-yyyy"): String {
        val safeDate = date ?: Date()
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        return dateFormat.format(safeDate)
    }

    fun formatTime(date: Date?, format: String = "HH.MM"): String {
        val safeDate = date ?: Date()
        val timeFormat = SimpleDateFormat(format, Locale.getDefault())
        return timeFormat.format(safeDate)
    }

    fun formatDateTime(date: Date?, format: String = "dd MMMM yyyy  HH.MM"): String {
        val safeDate = date ?: Date()
        val dateTimeFormat = SimpleDateFormat(format, Locale.getDefault())
        return dateTimeFormat.format(safeDate)
    }

    suspend fun downloadAndRenderPdf(context: Context, pdfUrl: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            try {
                val url = URL(pdfUrl)
                val connection = url.openConnection()
                val inputStream = connection.getInputStream()
                val file = File(context.cacheDir, "temp.pdf")
                val outputStream = FileOutputStream(file)

                inputStream.use { input ->
                    outputStream.use { output ->
                        input.copyTo(output)
                    }
                }

                val fileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
                val pdfRenderer = PdfRenderer(fileDescriptor)
                val page = pdfRenderer.openPage(0)

                val bitmap = Bitmap.createBitmap(page.width, page.height, Bitmap.Config.ARGB_8888)
                page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)

                page.close()
                pdfRenderer.close()
                fileDescriptor.close()

                file.delete()

                return@withContext bitmap
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext null
            }
        }
    }

}