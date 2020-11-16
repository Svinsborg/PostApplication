package ru.hell.postapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val post = Post(1,"Andy","Some very long text for testing an application for Android","3 aug 2020",false,13,2,1)

        val author: TextView = findViewById(R.id.author)
        val dateCreated: TextView = findViewById(R.id.created)
        val content: TextView = findViewById(R.id.content)
        var likeBtn: ImageView = findViewById(R.id.likeBtn)
        var likeCount: TextView = findViewById(R.id.likeCount)
        var commentCount: TextView = findViewById(R.id.commentCount)
        var sharedCount: TextView = findViewById(R.id.sharedCount)

        author.text = post.author
        dateCreated.text = post.created
        content.text = post.content
        if (post.likeCount > 0){ likeCount.text = post.likeCount.toString()
                                 post.liked = true}
        if (post.liked){ likeBtn.setImageResource(R.drawable.favoriteon)}
        if (post.commentCount > 0){ commentCount.text = post.commentCount.toString() }
        if (post.sharedCount > 0){ sharedCount.text = post.sharedCount.toString() }
        
    }
}

