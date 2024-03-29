package ru.hell.postapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import io.ktor.client.engine.cio.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private lateinit var postBlogAdapter : PostRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

    private fun initRecyclerView(){
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val topPadding = SpacingItemDecoration(20)
            addItemDecoration(topPadding)
            postBlogAdapter = PostRecyclerAdapter()
            adapter = postBlogAdapter
        }
    }

    private fun loadData() = launch {
        val client = ConnectionToJsonFile(CIO.create())
        client.getPost()
            .onEach { delay(100) }
            .onEach {
                when (val result = it) {
                    is DownloadResult.Error -> {
                        LoadData.isVisible = false
                        showToast("No internet connection")
                    }
                    is DownloadResult.Progress -> {
                        LoadData.isVisible = true
                        LoadData.progress = result.percent
                    }
                    is DownloadResult.Success -> {
                        LoadData.isVisible = false
                        postBlogAdapter.submitData(result.value)
                        //postBlogAdapter.notifyItemChanged(0, result.value.size)
                    }
                }
            }
            .collect()
    }

    private fun showToast(msg: String) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show()
    }
}

fun likeMath(like:Boolean, count:Int):String {
    val effect: String
    val summ: Int
    if (like) summ = (count + 1)
    else summ = (count)
    if (summ > 0){
        effect = summ.toString()
    } else {
        effect = " "
    }
    return effect
}

fun frendlyTime(time: Long): String =
    when (time) {
        in 0..59L -> "меньше минуты назад"
        in 0..3600L -> oneHour(time)
        in 3600..7199 -> "час назад"
        in 7200..10799 ->  "2 часа назад"
        in 10800..86399 ->  "несколько часов назад"
        in 86400..604799 -> oneDay(time)
        in 604800..2678399 -> oneWeek(time)
        in 2678400..31622399 -> oneMonth(time)
        in 31622399..157679999 -> oneYear(time)
        in 157680000..Long.MAX_VALUE -> "A long time ago, in a galaxy far, far away…"
        else -> "Неверные данные"
}

fun oneHour(sec: Long): String {
    var stringTime: String
    val time = (sec / 60)
    val lastСharacter: Long = time % 10
    if (time in 5..20) {
        stringTime = "$time минут назад"
    } else {
        if (lastСharacter == 1L) {
            stringTime = "$time минуту назад"
        } else if (lastСharacter in 5..9 || lastСharacter == 0L) {
            stringTime = "$time минут назад"
        } else {
            stringTime = "$time минуты назад"
        }
    }
    return stringTime
}

fun oneDay(sec: Long): String {
    var stringTime: String
    val time = (sec / 60 / 60 / 24)
    val lastСharacter: Long = time % 10
    if (time in 5..20) {
        stringTime = "$time дней назад"
    } else {
        if (lastСharacter == 1L) {
            stringTime = "$time день назад"
        } else if (lastСharacter in 5..9 || lastСharacter == 0L) {
            stringTime = "$time дней назад"
        } else {
            stringTime = "$time дня назад"
        }
    }
    return stringTime
}

fun oneWeek(sec: Long): String {
    var stringTime: String
    when (val time = (sec / 60 / 60 / 24 / 7)) {
        1L -> stringTime = "$time неделю назад"
        in 2..4 -> stringTime = "$time недели назад"
        else -> stringTime = "$time недель назад"
    }
    return stringTime
}

fun oneMonth(sec: Long): String =
    when (val time = (sec / 60 / 60 / 24 / 30)) {
        in 2..4 -> "$time месяца назад"
        in 5..12 -> "$time месяцев назад"
        else -> "$time месяц назад"
    }

fun oneYear(sec: Long): String =
    when (val time = (sec / 60 / 60 / 24 / 30 / 12)) {
        1L -> "$time год назад"
        in 2..4 -> "$time года назад"
        else ->  "$time лет назад"
    }