package com.example.ex06_searchdialog

import ir.mirrajabi.searchdialog.core.Searchable //This is a third party package, mostly posted by people on the internet

//THIS CLASS IS CREATED TO CREATE THE LIST STYLE/TEMPLATE
class SearchModel (private var mTile: String):Searchable {

    override fun getTitle(): String {
        return mTile
    }
}