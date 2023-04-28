package com.blagij

import eu.vendeli.tgbot.api.SendMessageAction
import eu.vendeli.tgbot.api.message
import eu.vendeli.tgbot.types.ParseMode
import org.jsoup.Jsoup

fun getNewNews() : List<News> {
    val lastId = getLastSync()

    val doc = Jsoup.connect("https://knu.ua/ajax/news_next").get()

    val news = doc.body().children().map {
        News(
            id = it.getElementsByTag("span").text().toLong(),
            url =it.getElementsByTag("a").first()!!.attr("href").toFullUrl(),
            title = it.getElementsByTag("p").text()
        )
    }

    if (news.maxOf { it.id } > lastId) {
        storeLastSync(news.maxOf { it.id })
    }

    return news.filter { it.id > lastId }
}

private fun String.toFullUrl(): String = "https://knu.ua/$this"

data class News(
    val id: Long,
    val url: String,
    val title: String
) {
    fun toMessage(): SendMessageAction {
        return message("Зявилась нова новина на сайті. Мерщій дивись:\n[$title]($url)")
            .options { parseMode = ParseMode.Markdown }
    }
}