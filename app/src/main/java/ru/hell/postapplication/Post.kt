package ru.hell.postapplication

data class Post(
        val type: PostType = PostType.POST,
        val id: Int,
        val author: String,
        val content: String? = null,
        val created: String,
        var liked: Boolean = false,
        var sharedCount: Int,
        var commentCount: Int,
        var likeCount: Int,
        val address: String? = null,
        val idVideoYT: String? = null,
        val location: Pair<Double, Double>? = null,
        val source: Post? = null,
        val img: String? = null,
        val url: String? = null
        ) {}