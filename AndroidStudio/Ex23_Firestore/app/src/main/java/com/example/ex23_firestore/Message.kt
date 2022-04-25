package com.example.ex23_firestore

class Message (val id: String, val message: String, val rating: Int, val timeStamp: String ){
    constructor(): this("","",0,"")
}