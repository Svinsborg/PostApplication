package ru.hell.postapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var postBlogAdapter : PostRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        loadData()

/*        val post = Post(
                  1,
                "Andy",
                "Some very long text for testing application for Android",
                1605914700L, // 20 November 2020 г., 23:25:00
                false,
                13, //13
                2,
                5,
                "Gotham City",
                "m05_CeMOsJU",
                Pair(40.76876535856855, -73.98833914503419)
        )*/

/*        val moscowGMT = 10800L // +3 GMT
        val startDate: Long = post.created
        val timestamp = System.currentTimeMillis() / 1000 + moscowGMT
        val timePost = frendlyTime((timestamp - startDate))

        val author: TextView = findViewById(R.id.author)
        val dateCreated: TextView = findViewById(R.id.created)
        val content: TextView = findViewById(R.id.content)
        var likeBtn: ImageView = findViewById(R.id.likeBtn)
        var likeCount: TextView = findViewById(R.id.likeCount)
        var commentCount: TextView = findViewById(R.id.commentCount)
        var sharedCount: TextView = findViewById(R.id.sharedCount)
        val address: TextView = findViewById(R.id.address)*/

/*        val youtubefragment = supportFragmentManager.findFragmentById(R.id.youtube_view) as YouTubePlayerSupportFragment
        youtubefragment.initialize(R.string.key_api.toString(), object: YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider?,
                player: YouTubePlayer?,
                restored: Boolean
            ) {
                if(player == null) return
                if (restored)
                    player.play()
                else{
                    player.cueVideo(post.idVideoYT) //Video ID
                    player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
                }
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                TODO("Not yet implemented")
            }
        })*/
            //
/*        author.text = post.author
        dateCreated.text = timePost
        content.text = post.content
        likeCount.text = likeMath(post.liked, post.likeCount)
        address.text = post.address*/

/*        likeBtn.setOnClickListener() {
            likeBtn.setImageResource(
                    if (post.liked) {
                        R.drawable.favoriteoff
                    } else {
                        R.drawable.favoriteon
                    }
            )
            post.liked =! post.liked
            likeCount.text = likeMath(post.liked, post.likeCount)
        }*/

/*        address.setOnClickListener(){
            val lat = post.location.first
            val lng = post.location.second
            val data = Uri.parse("geo:$lat,$lng")
            val intent = Intent(Intent.ACTION_VIEW, data)
            startActivity(intent)
        }*/

/*        if (post.commentCount > 0) {
            commentCount.text = post.commentCount.toString()
        }
        if (post.sharedCount > 0) {
            sharedCount.text = post.sharedCount.toString()
        }*/
    }
    private fun loadData(){
        val data = DateResource.createDataSet()
        postBlogAdapter.submitData(data)
    }


    private fun initRecyclerView(){
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            postBlogAdapter = PostRecyclerAdapter()
            adapter = postBlogAdapter
        }
    }
}




fun likeMath(like:Boolean, count:Int):String {
    val effect: String
    var summ: Int
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
