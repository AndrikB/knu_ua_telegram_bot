package com.blagij

import eu.vendeli.tgbot.TelegramBot
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class Periodic(private val bot: TelegramBot) {

    fun startProcessing(){
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                println("Task executed at ${Date()}")
                process()
                println("Finished execution of task ${Date()}")
            }
        }, 0, 60_000)

    }

    private fun process() {

        val newNews = getNewNews()

        println("received ${newNews.count()} news. Ids: ${newNews.map { it.id }}")


        newNews.forEach { news ->
            getListOfUsers().forEach {
                GlobalScope.launch {
                    println("sending news ${news.id} to user $it")
                    news.toMessage().sendAsync(it, bot).getCompleted().apply { println("response: $this") }
                }
            }
        }
    }

}
