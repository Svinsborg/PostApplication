package ru.hell.postapplication

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_view.view.*

class PostRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var items: List<Post> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
      return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_view, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is PostViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun submitData(postlist : List<Post>){
        items = postlist
    }




    class PostViewHolder
    constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

        val author = itemView.author
        val content = itemView.content
        val created = itemView.created
        var liked = itemView.likeBtn
        val sharedCount = itemView.sharedCount
        val commentCount = itemView.commentCount
        val likeCount = itemView.likeCount
        val address = itemView.address
        val location = itemView.location

        fun bind(post: Post){

            val moscowGMT = 10800L // +3 GMT
            val startDate: Long = post.created.toLong()
            val timestamp = System.currentTimeMillis() / 1000 + moscowGMT
            val timePost = frendlyTime((timestamp - startDate))


            author.text = post.author
            created.text = timePost
            content.text = post.content
            likeCount.text = likeMath(post.liked, post.likeCount)
            address.text = post.address

            liked.setOnClickListener() {
                liked.setImageResource(
                    if (post.liked) {
                        R.drawable.favoriteoff
                    } else {
                        R.drawable.favoriteon
                    }
                )
                post.liked =! post.liked
                likeCount.text = likeMath(post.liked, post.likeCount)
            }

/*            address.setOnClickListener(){
                val lat = post.location.first
                val lng = post.location.second
                val data = Uri.parse("geo:$lat,$lng")
                val intent = Intent(Intent.ACTION_VIEW, data)
                startActivity(intent)
            }*/

            if (post.commentCount > 0) {
                commentCount.text = post.commentCount.toString()
            }
            if (post.sharedCount > 0) {
                sharedCount.text = post.sharedCount.toString()
            }
        }
    }
}