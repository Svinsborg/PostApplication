package ru.hell.postapplication

class Post(
        val id: Long,
        val author: String,
        val content: String,
        val created: Long,
        var liked: Boolean = false,
        var sharedCount: Int,
        var commentCount: Int,
        var likeCount: Int) {}