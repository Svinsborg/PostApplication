package ru.hell.postapplication

class EventsPost : Post {
    val address: String
    val location: Pair<Double, Double>

    constructor(type: PostType = PostType.EVENTS,
                id: Int,
                author: String,
                content: String,
                created: Long,
                liked: Boolean = false,
                sharedCount: Int,
                commentCount: Int,
                likeCount: Int,
                idVideoYT: String,
                adrs: String,
                loct: Pair<Double, Double>) : super(
            type,
            id,
            author,
            content,
            created,
            liked,
            sharedCount,
            commentCount,
            likeCount,
            idVideoYT) {
        address = adrs
        location = loct
    }
}