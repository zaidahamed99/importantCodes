package com.example.ex12_basicsqllite


class Comment {
    var id: Long  = 0
    var message: String = ""

    override fun toString(): String {
        return message.toString()
    }
}