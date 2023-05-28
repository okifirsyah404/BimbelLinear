package com.okifirsyah.bimbellinear.utils.helper

import java.io.File

fun renameFile(filePath: String, fileName: String): File? {
    filePath.let {
        val oldFile = File(it)
        val newFile = File(oldFile.parent, "$fileName.${getFileExtension(it)}")
        if (oldFile.exists()) {
            oldFile.renameTo(newFile)
            return newFile
        }
    }
    return null
}

fun getFileExtension(filePath: String): String {
    val lastDotIndex = filePath.lastIndexOf(".")
    return if (lastDotIndex != -1) {
        filePath.substring(lastDotIndex + 1)
    } else {
        ""
    }
}