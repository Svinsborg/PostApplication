package ru.hell.postapplication

open class Post(
        var type: PostType = PostType.POST,
        val id: Int,
        val author: String,
        val content: String,
        val created: Long,
        var liked: Boolean = false,
        var sharedCount: Int,
        var commentCount: Int,
        var likeCount: Int,
        val idVideoYT: String
) {
}