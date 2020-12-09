package ru.hell.postapplication

import ru.hell.postapplication.Post
import kotlin.collections.ArrayList


class DateResource {

    companion object {

        fun createDataSet(): ArrayList<Post> {
            val list = ArrayList<Post>()

            list.add(
                Post(
                    1,
                    "Andy",
                    "Some very long text for testing application for Android",
                    "1605914700", // 20 November 2020 г., 23:25:00
                    false,
                    13,
                    2,
                    5,
                    "Gotham City",
                    "m05_CeMOsJU",
                    Pair(40.76876535856855, -73.98833914503419)
                )
            )

            list.add(
                Post(
                    2,
                    "Filth",
                    "Как всегда, ничего не работает!",
                    "1606938567",
                    false,
                    0,
                    5,
                    12,
                    "New Genesis",
                    "a4Uzufh4X3A",
                    Pair(35.95125187624326, 138.8906123061434)
                )
            )

            list.add(
                Post(
                    3,
                    "RIDan",
                    "Сломал все что было, кто ченить будет?",
                    "1606785652",
                    false,
                    22,
                    0,
                    14,
                    "Kandor",
                    "Cv45cYW1zn8",
                    Pair(61.66611486351941, 7.00560833613891)
                )
            )

            list.add(
                Post(
                    4,
                    "Svinsborg",
                    "Свинья свиньей!",
                    "1605610533",
                    false,
                    2,
                    5,
                    0,
                    "Calvin City",
                    "zDj8F7rFt5I",
                    Pair(11.309692637213368, 124.8817415331434)
                )
            )

            list.add(
                Post(
                    5,
                    "Satan",
                    "Работай! А то так и будешь пыль с камер протирать!",
                    "1606577272",
                    false,
                    0,
                    0,
                    0,
                    "Argo City",
                    "EzjzKLEVubE",
                    Pair(73.18092555450909, 127.27186210943175)
                )
            )

            list.add(
                Post(
                    6,
                    "Brute",
                    "Сломал ногу об гранит науки",
                    "1559061882",
                    false,
                    0,
                    0,
                    2,
                    "Keystone City",
                    "okHcprcgKGc",
                    Pair(-32.308481928577415, 22.55679169777107)
                )
            )

            list.add(
                Post(
                    7,
                    "Android",
                    "Сколько можно это заполнять??!",
                    "1523715254",
                    false,
                    0,
                    0,
                    43,
                    "Gotham City",
                    "eG0TiCeJ_2w",
                    Pair(14.639379877203488, -86.80604747239683)
                )
            )

            list.add(
                Post(
                    8,
                    "Kotlin",
                    "Спать пора! Заканчивай!",
                    "1498162435",
                    false,
                    11,
                    0,
                    0,
                    "Kryptonopolis",
                    "hLA34hWXeEE",
                    Pair(55.53869032854246, -102.31003645966574)
                )
            )

            list.add(
                Post(
                    9,
                    "Java",
                    "В декабре разве так рано встает солнце?",
                    "1492262435",
                    false,
                    0,
                    21,
                    0,
                    "Midway City",
                    "3i5weg3ALyc",
                    Pair(52.90266413734028, 172.97796996156944)
                )
            )

            list.add(
                Post(
                    10,
                    "Jesika",
                    "Снова в бой!",
                    "1498162235",
                    false,
                    1,
                    1,
                    1,
                    "Gotham City",
                    "yyyzGysLim0",
                    Pair(-23.67788022618058, 133.89661924651026)
                )
            )

            list.add(
                Post(
                    11,
                    "Rouse",
                    "Инструкция по нерабочему проекту",
                    "1498155435",
                    false,
                    1,
                    2,
                    3,
                    "National City",
                    "39w-HZD8lJw",
                    Pair(-75.10384201489077, 123.35179884308245)
                )
            )

            list.add(
                Post(
                    12,
                    "Andy",
                    "Лучше удали эту х@3#Y",
                    "1495562435",
                    false,
                    4,
                    5,
                    6,
                    "Star City",
                    "A0XnPOJk0RE",
                    Pair(42.34598838198658, 25.078331462687224)
                )
            )

            list.add(
                Post(
                    13,
                    "Judi",
                    "Пожалуй хватит",
                    "1473405735",
                    false,
                    7,
                    8,
                    9,
                    "Coast City",
                    "GQjNnXVrBAY",
                    Pair(23.89672760821924, 120.37252999583876)
                )
            )
            return list
        }
    }
}

