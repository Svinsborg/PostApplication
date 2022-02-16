package ru.hell.postapplication

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.android.synthetic.main.post_view.view.*



class PostRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Post> = ArrayList()

        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.post_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is PostViewHolder -> {
                holder.bind(items[position])
                //TUT
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun submitData(postlist: List<Post>) {
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
        private val imgGeo: ImageView = itemView.location
        private val repost: LinearLayout = itemView.repost
        private val repAuthor: TextView = itemView.repAuthor
        private val repDate: TextView = itemView.repDate
        private val repContent: TextView = itemView.repContent

        private lateinit var youTubePlayer: YouTubePlayer

        init {
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

            loadImg(post.img)

            val moscowGMT = 10800L // +3 GMT
            val startDate: Long = post.created?.toLong() ?: System.currentTimeMillis()
            val timestamp = System.currentTimeMillis() / 1000 + moscowGMT
            val timePost = frendlyTime((timestamp - startDate))
            val videoId = post.idVideoYT

            author.text = post.author
            created.text = timePost
            content.text = post.content
            likeCount.text = likeMath(post.liked, post.likeCount)
            address.text = post.address


            if (post.source != null) { //else
                repAuthor.text = post.source.author
                val startDate: Long = post.source.created?.toLong() ?: System.currentTimeMillis()
                val timePost = frendlyTime((timestamp - startDate))
                repDate.text = timePost
                repContent.text = post.source.content
                repContent.textSize = 18.0F
                repDate.textSize = 10.0F
                repAuthor.textSize = 15.0F
            }  else {
      //TODO
            }

            // Проверяем заполнена ли переменная
            if (::youTubePlayer.isInitialized) {
                videoId?.let { youTubePlayer.cueVideo(it, 0f) }
                youTubePlayerView.tag = null
            } else {
                // Если нет, пишем во вьюшку на будущее
                youTubePlayerView.tag = post.idVideoYT
            }
            // Наверное, плеер стоит скрыть, если видео нет?
            youTubePlayerView.isVisible = videoId != null

            liked.setImageResource(
                if (post.liked) {
                    R.drawable.favoriteon
                } else {
                    R.drawable.favoriteoff
                }
            )


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

            image.setOnClickListener {
                val url = post.url
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                it.context.startActivity(intent)
            }

            if (post.commentCount > 0) { //else
                commentCount.text = post.commentCount.toString()
            }  else {
                 //TODO
            }
            if (post.sharedCount > 0) { //else
                sharedCount.text = post.sharedCount.toString()
            }  else {
                 //TODO
            }

            when (post.type) {
                //               PostType.POST -> showHide(imgGeo)
                PostType.EVENTS -> imgGeo.isVisible = true
                PostType.REPOST -> repost.isVisible = true
//                PostType.REPLY -> showHide(imgGeo)
//                PostType.VIDEO -> showHide(imgGeo)
                PostType.COMMERCIAL -> image.isVisible = true
                else -> {
                    imgGeo.isInvisible = true
                    repost.isInvisible = true
//                PostType.REPLY -> showHide(imgGeo)
//                PostType.VIDEO -> showHide(imgGeo)
                    image.isInvisible = true  //TODO
                }
            }
        }

        private fun loadImg(img: String?) {
            if (img != null) {
                imgBind(img)
            } else {
                image.isInvisible = true
            }
        }

        private fun imgBind(img: String?) {
            val USER_AGENT = "Post application v1.0.3"
            try {
                val imgUrl = GlideUrl(
                    img, LazyHeaders.Builder()
                        .addHeader("User-Agent", USER_AGENT)
                        .build()
                )
                val requestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_no_connect)
                Glide.with(itemView.context)
                    .applyDefaultRequestOptions(requestOptions)
                    .load(imgUrl)
                    .timeout(6000)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            Log.e(TAG, "=====>>>> Load failed", e)
                            return false
                        }
                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }
                    }).into(image)
            } catch (e: Exception) {
                print("===>>>> Exception: ${e.message}")
            }
        }


        private fun showHide(view: View) {
            view.visibility = View.VISIBLE
        }
    }
}




