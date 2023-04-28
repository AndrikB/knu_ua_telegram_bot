package com.blagij

import eu.vendeli.tgbot.TelegramBot
import eu.vendeli.tgbot.annotations.CommandHandler
import eu.vendeli.tgbot.api.message
import eu.vendeli.tgbot.types.User
import eu.vendeli.tgbot.types.internal.ProcessedUpdate

class StartController {
    @CommandHandler(["/start"])
    suspend fun start(update: ProcessedUpdate, bot: TelegramBot, user: User) {
        message("Привіт! Я буду надсилати тобі повідомлення про появу новин на сайті knu.ua. Якщо захочеш відписатись, натисни /unsubscribe").send(user, bot)
        if (user.id !in getListOfUsers()) {
            addUserId(user.id)
        }
        println("start " + user.id)
    }

    @CommandHandler(["/unsubscribe"])
    suspend fun unsubscribe(update: ProcessedUpdate, bot: TelegramBot, user: User) {
        message("Я більше не буду тобі надсилати новини. Якщо ти захочеш знову їх отримувати, натисни /subscribe").send(user, bot)
        if (user.id !in getListOfUsers()) {
            removeUserId(user.id)
        }
        println("unsubscribe " + user.id)
    }


    @CommandHandler(["/subscribe"])
    suspend fun subscribe(update: ProcessedUpdate, bot: TelegramBot, user: User) {
        message("Я знову буду надсилати тобі повідомлення про появу новин на сайті knu.ua").send(user, bot)
        if (user.id !in getListOfUsers()) {
            addUserId(user.id)
        }
        println("subscribe " + user.id)
    }
}
