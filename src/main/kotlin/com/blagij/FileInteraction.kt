package com.blagij

import java.io.File

fun getBotToken(): String = File("token.txt").readText()

fun getListOfUsers(): List<Long> = File("users.txt").readLines().mapNotNull { it.toLongOrNull() }

fun removeUserId(id: Long) {
    if (id in getListOfUsers()) {
        File("users.txt")
            .writeText(getListOfUsers().minus(id).joinToString(transform = { it.toString() }, separator = "\n"))
    }
}

fun addUserId(id: Long) {
    if (id !in getListOfUsers())
        addUserIdToFile(id)
}

private fun addUserIdToFile(id: Long) = File("users.txt").appendText("\n$id")

fun getLastSync(): Long = File("sync.txt").readText().toLong()

fun storeLastSync(id: Long) = File("sync.txt").writeText(id.toString())