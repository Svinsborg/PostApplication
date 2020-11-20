package ru.hell.postapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val post = Post(1,"Andy",
                "Some very long text for testing an application for Android",
                1605914700L, // 20 November 2020 г., 23:25:00
                false,
                13,
                2,
                1)

        val moscowGMT = 10800L // +3 GMT
        val startDate: Long = post.created
        val timestamp = System.currentTimeMillis()/1000 + moscowGMT
        val timePost = frendlyTime((timestamp - startDate))

        val author: TextView = findViewById(R.id.author)
        val dateCreated: TextView = findViewById(R.id.created)
        val content: TextView = findViewById(R.id.content)
        var likeBtn: ImageView = findViewById(R.id.likeBtn)
        var likeCount: TextView = findViewById(R.id.likeCount)
        var commentCount: TextView = findViewById(R.id.commentCount)
        var sharedCount: TextView = findViewById(R.id.sharedCount)

        author.text = post.author
        dateCreated.text = timePost
        content.text = post.content
        if (post.likeCount > 0){ likeCount.text = post.likeCount.toString()
                                 post.liked = true}
        if (post.liked){ likeBtn.setImageResource(R.drawable.favoriteon)}
        if (post.commentCount > 0){ commentCount.text = post.commentCount.toString() }
        if (post.sharedCount > 0){ sharedCount.text = post.sharedCount.toString() }
        
    }
}

fun frendlyTime(time:Long): String {

    var stringTime: String
    when (time){
        in 0..59L -> stringTime = "меньше минуты назад"
        in 0..3600L -> stringTime = oneHour(time)
        in 3600..7199 -> stringTime = "час назад"
        in 7200..10799 -> stringTime = "2 часа назад"
        in 10800..86399 -> stringTime = "несколько часов назад"
        in 86400..604799 -> stringTime = oneDay(time)
        in 604800..2678399 -> stringTime = oneWeek(time)
        in 2678400..31622399 -> stringTime = oneMonth(time)
        in 31622399..157679999 -> stringTime = oneYear(time)
        in 157680000..Long.MAX_VALUE -> stringTime = "A long time ago, in a galaxy far, far away…"
        else -> stringTime = "Неверные данные"
    }
    return stringTime
}

fun oneHour(sec:Long): String {
    var stringTime: String

    val time = ( sec / 60 )
    val lastСharacter: Long = time%10
    if(time in 5..20){
        stringTime = "$time минут назад"
    } else {
        if (lastСharacter == 1L ) {
            stringTime = "$time минуту назад"
        } else if (lastСharacter in 5..9 || lastСharacter == 0L) {
            stringTime = "$time минут назад"
        } else{
            stringTime = "$time минуты назад"
        }
    }
    return stringTime
}

fun oneDay(sec:Long): String {
    var stringTime: String
    val time = ( sec / 60 / 60 / 24 )
    stringTime ="$time дней назад"
    return stringTime
}

fun oneWeek(sec:Long): String {
    var stringTime: String
    when (val time = (sec / 60 / 60 / 24 / 7)) {
        1L -> stringTime = "$time неделю назад"

        in 2..4 ->  stringTime = "$time недели назад"

        else -> stringTime ="$time недель назад"
        }
    return stringTime
}

fun oneMonth(sec:Long): String {
    var stringTime: String
    when (val time = ( sec / 60 / 60 / 24 / 30 )) {
        in 2..4 -> {
            stringTime ="$time месяца назад"
        }
        in 5..12 -> {
            stringTime ="$time месяцев назад"
        }
        else -> {
            stringTime ="$time месяц назад"
        }
    }
    return stringTime
}

fun oneYear(sec:Long): String {
    var stringTime: String
    when (val time = ( sec / 60 / 60 / 24 / 30 / 12 )) {
        1L -> {
            stringTime = "$time год назад"
        }
        in 2..4 -> {
            stringTime = "$time года назад"
        }
        else -> {
            stringTime = "$time лет назад"
        }
    }
    return stringTime
}
