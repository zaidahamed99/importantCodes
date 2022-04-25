package com.example.ex09_rss.Model


data class RSSObject(val status: String, val feed: Feed, val items: List<Item>)
