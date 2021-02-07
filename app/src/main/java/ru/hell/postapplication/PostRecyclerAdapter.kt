package ru.hell.postapplication

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.android.synthetic.main.post_view.view.*

class PostRecyclerAdapter :  RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Post> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_view, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PostViewHolder -> {
                holder.bind(items[position]) //TUT
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun submitData(postlist : List<Post>) {
        items = postlist
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val author: TextView = itemView.author
        private val content: TextView = itemView.content
        private val created: TextView = itemView.created
        private val liked: ImageView = itemView.likeBtn
        private val sharedCount: TextView = itemView.sharedCount
        private val commentCount: TextView = itemView.commentCount
        private val likeCount: TextView = itemView.likeCount
        private val address: TextView = itemView.address
        private val image: ImageView = itemView.imageCommercial
        private val youTubePlayerView: YouTubePlayerView = itemView.youtube_view

        private lateinit var youTubePlayer: YouTubePlayer

        init{
            youTubePlayerView.getPlayerUiController().showFullscreenButton(true)
            youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {

                    // Сохраняем на будущее
                    this@PostViewHolder.youTubePlayer = youTubePlayer

                    // Если быстро скроллили, пока плеер не был готов, грузим
                    val videoId = youTubePlayerView.tag as? String ?: return
                    youTubePlayer.cueVideo(videoId, 0f)
                }
            })
        }

        fun bind(post: Post) {
            // обработка ошибки загрузки картинки
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_no_connect)

            // Загрузка картинки
            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(post.img)
                .into(image)

            val moscowGMT = 10800L // +3 GMT
            val startDate: Long = post.created.toLong()
            val timestamp = System.currentTimeMillis() / 1000 + moscowGMT
            val timePost = frendlyTime((timestamp - startDate))
            val videoId = post.idVideoYT

            author.text = post.author
            created.text = timePost
            content.text = post.content
            likeCount.text = likeMath(post.liked, post.likeCount)
            address.text = post.address


            // Проверяем заполнена ли переменная
            if (::youTubePlayer.isInitialized) {
                videoId?.let { youTubePlayer.cueVideo(it, 0f) }
                youTubePlayerView.tag = null
            } else {

                // Если нет, пишем во вьюшку на будущее
                youTubePlayerView.tag = post.idVideoYT
            }

            // Наверное, плеер стоит скрыть, если видео нет?
            youTubePlayerView.isVisible = videoId!= null


            liked.setOnClickListener {
                liked.setImageResource(
                        if (post.liked) {
                            R.drawable.favoriteoff
                        } else {
                            R.drawable.favoriteon
                        }
                )
                post.liked = !post.liked
                likeCount.text = likeMath(post.liked, post.likeCount)
            }

            address.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    val lat = post.location?.first
                    val lng = post.location?.second
                    data = Uri.parse("geo:$lat,$lng")
                }
                it.context.startActivity(intent)
            }

            if (post.commentCount > 0) {
                commentCount.text = post.commentCount.toString()
            }
            if (post.sharedCount > 0) {
                sharedCount.text = post.sharedCount.toString()
            }
        }
    }
}