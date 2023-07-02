package com.example.filmsandseries.util

fun String.clearText(): String {
    return replace("<p>", "")
        .replace("</p>", "")
        .replace("<b>", "")
        .replace("</b>", "")
}

fun List<String>.clearList(): String {
    return toString().replace("[", "").replace("]", "")
}

