package ru.hell.postapplication

data class Post(
        val id: Int,
        val author: String,
        val content: String,
        val created: String,
        var liked: Boolean = false,
        var sharedCount: Int,
        var commentCount: Int,
        var likeCount: Int,
        val address: String,
        val idVideoYT: String,
        val location: Pair<Double, Double>
        ) {}