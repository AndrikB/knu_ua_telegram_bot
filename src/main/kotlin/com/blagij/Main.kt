package com.blagij

import eu.vendeli.tgbot.TelegramBot
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    println("Hello World!")

    val bot = TelegramBot(getBotToken(), "com.blagij")

    Periodic(bot).startProcessing()

    bot.handleUpdates()
}