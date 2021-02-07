package ru.hell.postapplication

import kotlin.collections.ArrayList


class DateResource {

    companion object {

        fun createDataSet(): ArrayList<Post> {
            val list = ArrayList<Post>()

/*            list.add(
                Post(
                        type = PostType.POST,
                        id = 1,
                        author = "Andy",
                        content = "Some very long text for testing application for Android",
                        created = "1605914700", // 20 November 2020 г., 23:25:00
                        liked = false,
                        sharedCount = 13,
                        commentCount = 2,
                        likeCount = 5
                )
            )

            list.add(
                    Post(
                            type = PostType.REPOST,
                            id = 2,
                            author = "Filth",
                            created = "1612299228",
                            liked =  false,
                            sharedCount = 13,
                            commentCount = 2,
                            likeCount = 5,
                            source = list[1]
                    )
            )

            list.add(
                Post(
                        type = PostType.EVENTS,
                        id = 3,
                        author = "RIDan",
                        content = "Сломал все что было, кто ченить будет? /n",
                        created = "1606785652",
                        liked = true,
                        sharedCount = 22,
                        commentCount = 0,
                        likeCount = 14,
                        address = "Kandor",
                        location = Pair(61.66611486351941, 7.00560833613891)
                )
            )

            list.add(
                Post(
                        type = PostType.VIDEO,
                        id = 4,
                        author = "Svinsborg",
                        content = "Свинья свиньей!",
                        created = "1605610533",
                        liked = false,
                        sharedCount = 2,
                        commentCount = 5,
                        likeCount = 0
                )
            )

            list.add(
                Post(
                        type = PostType.COMMERCIAL,
                        id = 5,
                        author = "Satan",
                        content = "Работай! А то так и будешь пыль с камер протирать!",
                        created = "1606577272",
                        liked = false,
                        sharedCount = 0,
                        commentCount = 0,
                        likeCount = 0,
                        img = "http://95.165.135.238/img/res/pic/001.jpg",
                        url = "https://www.youtube.com/channel/UCbnY0I0Dt9-SK8I32y1WyjQ"
                )
            )*/

            list.add(
                Post(
                        type = PostType.POST,
                        id = 1,
                        author = "Andy",
                        content = "Some very long text for testing application for Android",
                        created = "1605914700", // 20 November 2020 г., 23:25:00
                        liked = false,
                        sharedCount = 13,
                        commentCount = 2,
                        likeCount = 5,
                        address = "Gotham City",
                        idVideoYT = "m05_CeMOsJU",
                        location = Pair(40.76876535856855, -73.98833914503419),
                        img = "http://pravo.gov.ru/upload/medialibrary/db9/db93036a8b261613f0e3dc981e9447ad.jpg",
                        url = "https://www.youtube.com/channel/UCbnY0I0Dt9-SK8I32y1WyjQ"
                )
            )

            list.add(
                Post(
                        type = PostType.POST,
                        id = 2,
                        author = "Filth",
                        content = "Как всегда, ничего не работает!",
                        created = "1606938567",
                        liked = false,
                        sharedCount = 0,
                        commentCount = 5,
                        likeCount = 12,
                        address = "New Genesis",
                        idVideoYT = "a4Uzufh4X3A",
                        location = Pair(35.95125187624326, 138.8906123061434),
                        img = "https://cdn1.ozone.ru/multimedia/1014598895.jpg",
                        url = "https://www.youtube.com/channel/UCbnY0I0Dt9-SK8I32y1WyjQ"
                )
            )
/*
            list.add(
                Post(
                        type = PostType.POST,
                        id = 3,
                        author = "RIDan",
                        content = "Сломал все что было, кто ченить будет?",
                        created = "1606785652",
                        liked = false,
                        sharedCount = 22,
                        commentCount = 0,
                        likeCount = 14,
                        address = "Kandor",
                        idVideoYT = "Cv45cYW1zn8",
                        location = Pair(61.66611486351941, 7.00560833613891),
                        url = "https://www.youtube.com/channel/UCbnY0I0Dt9-SK8I32y1WyjQ"
                )
            )

            list.add(
                Post(
                        type = PostType.POST,
                        id = 4,
                        author = "Svinsborg",
                        content = "Свинья свиньей!",
                        created = "1605610533",
                        liked = false,
                        sharedCount = 2,
                        commentCount = 5,
                        likeCount = 0,
                        address = "Calvin City",
                        idVideoYT = "zDj8F7rFt5I",
                        location = Pair(11.309692637213368, 124.8817415331434),
                        url = "https://www.youtube.com/channel/UCbnY0I0Dt9-SK8I32y1WyjQ"
                )
            )

            list.add(
                Post(
                        type = PostType.POST,
                        id = 5,
                        author = "Satan",
                        content = "Работай! А то так и будешь пыль с камер протирать!",
                        created = "1606577272",
                        liked = false,
                        sharedCount = 0,
                        commentCount = 0,
                        likeCount = 0,
                        address = "Argo City",
                        idVideoYT = "EzjzKLEVubE",
                        location = Pair(73.18092555450909, 127.27186210943175),
                        url = "https://www.youtube.com/channel/UCbnY0I0Dt9-SK8I32y1WyjQ"
                )
            )

            list.add(
                Post(
                        type = PostType.POST,
                        id = 6,
                        author = "Brute",
                        content = "Сломал ногу об гранит науки",
                        created = "1559061882",
                        liked = false,
                        sharedCount = 0,
                        commentCount = 0,
                        likeCount = 2,
                        address = "Keystone City",
                        idVideoYT = "okHcprcgKGc",
                        location = Pair(-32.308481928577415, 22.55679169777107),
                        url = "https://www.youtube.com/channel/UCbnY0I0Dt9-SK8I32y1WyjQ"
                )
            )

            list.add(
                Post(
                        type = PostType.POST,
                        id = 7,
                        author = "Android",
                        content = "Сколько можно это заполнять??!",
                        created = "1523715254",
                        liked = false,
                        sharedCount = 0,
                        commentCount = 0,
                        likeCount = 43,
                        address = "Gotham City",
                        idVideoYT = "eG0TiCeJ_2w",
                        location = Pair(14.639379877203488, -86.80604747239683),
                        url = "https://www.youtube.com/channel/UCbnY0I0Dt9-SK8I32y1WyjQ"
                )
            )

            list.add(
                Post(
                        type = PostType.POST,
                        id = 8,
                        author = "Kotlin",
                        content = "Спать пора! Заканчивай!",
                        created = "1498162435",
                        liked = false,
                        sharedCount = 11,
                        commentCount = 0,
                        likeCount = 0,
                        address = "Kryptonopolis",
                        idVideoYT = "hLA34hWXeEE",
                        location = Pair(55.53869032854246, -102.31003645966574),
                        url = "https://www.youtube.com/channel/UCbnY0I0Dt9-SK8I32y1WyjQ"
                )
            )

            list.add(
                Post(
                        type = PostType.POST,
                        id = 9,
                        author = "Java",
                        content = "В декабре разве так рано встает солнце?",
                        created = "1492262435",
                        liked = false,
                        sharedCount = 0,
                        commentCount = 21,
                        likeCount = 0,
                        address = "Midway City",
                        idVideoYT = "3i5weg3ALyc",
                        location = Pair(52.90266413734028, 172.97796996156944),
                        url = "https://www.youtube.com/channel/UCbnY0I0Dt9-SK8I32y1WyjQ"
                )
            )

            list.add(
                Post(
                        type = PostType.POST,
                        id = 10,
                        author = "Jesika",
                        content = "Снова в бой!",
                        created = "1498162235",
                        liked = false,
                        sharedCount = 1,
                        commentCount = 1,
                        likeCount = 1,
                        address = "Gotham City",
                        idVideoYT = "yyyzGysLim0",
                        location = Pair(-23.67788022618058, 133.89661924651026),
                        url = "https://www.youtube.com/channel/UCbnY0I0Dt9-SK8I32y1WyjQ"
                )
            )

            list.add(
                Post(
                        type = PostType.POST,
                        id = 11,
                        author = "Rouse",
                        content = "Инструкция по нерабочему проекту",
                        created = "1498155435",
                        liked = false,
                        sharedCount = 1,
                        commentCount = 2,
                        likeCount = 3,
                        address = "National City",
                        idVideoYT = "39w-HZD8lJw",
                        location = Pair(-75.10384201489077, 123.35179884308245),
                        url = "https://www.youtube.com/channel/UCbnY0I0Dt9-SK8I32y1WyjQ"
                )
            )

            list.add(
                Post(
                        type = PostType.POST,
                        id = 12,
                        author = "Andy",
                        content = "Лучше удали эту х@3#Y",
                        created = "1495562435",
                        liked = false,
                        sharedCount = 4,
                        commentCount = 5,
                        likeCount = 6,
                        address = "Star City",
                        idVideoYT = "A0XnPOJk0RE",
                        location = Pair(42.34598838198658, 25.078331462687224),
                        url = "https://www.youtube.com/channel/UCbnY0I0Dt9-SK8I32y1WyjQ"
                )
            )

            list.add(
                Post(
                        type = PostType.POST,
                        id = 13,
                        author = "Judi",
                        content = "Пожалуй хватит",
                        created = "1473405735",
                        liked = false,
                        sharedCount = 7,
                        commentCount = 8,
                        likeCount = 9,
                        address = "Coast City",
                        idVideoYT = "GQjNnXVrBAY",
                        location = Pair(23.89672760821924, 120.37252999583876),
                        url = "https://www.youtube.com/channel/UCbnY0I0Dt9-SK8I32y1WyjQ"
                )
            )*/
            return list
        }
    }
}

