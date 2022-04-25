package com.example.ex12_basicsqllite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase

//WE USE IT TO MANAGE 3 MAIN FUNCTION
//CREATE, READ, UPDATE, DELETE 

class CommentDataSource(context: Context) {

    // Database fields
    private var database: SQLiteDatabase? = null
    private val dbHelper: MySQLiteHelper
    private val allColumns = arrayOf<String>(MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_COMMENT)

    // make sure to close the cursor
    val allComments: List<Comment>
    //read data
        get() {
            val comments = ArrayList<Comment>()

            val cursor = database!!.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, null, null, null, null, null)

            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val comment = cursorToComment(cursor)
                comments.add(comment)
                cursor.moveToNext()
            }
            cursor.close()
            return comments
        }
    //initializes db helper, when it is called, it executes in onCreate
    init {
        dbHelper = MySQLiteHelper(context)
    }

    @Throws(SQLException::class)
    fun open() {
        database = dbHelper.getWritableDatabase()
    }

    fun close() {
        dbHelper.close()
    }

    fun createComment(comment: String): Comment {
        val values = ContentValues()
        values.put(MySQLiteHelper.COLUMN_COMMENT, comment)
        //Insert a new record to Comment Table
        val insertId = database!!.insert(MySQLiteHelper.TABLE_COMMENTS, null,
            values)
        //Read a new record from the Comment Table
        val cursor = database!!.query(MySQLiteHelper.TABLE_COMMENTS,
            allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null)
        cursor.moveToFirst()
        val newComment = cursorToComment(cursor)
        cursor.close()
        return newComment
    }

    fun deleteComment(comment: Comment) {
        val id = comment.id
        println("Comment deleted with id: " + id)
        database!!.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null)
    }

    private fun cursorToComment(cursor: Cursor): Comment {
        val comment = Comment()
        comment.id = cursor.getLong(0)
        comment.message = cursor.getString(1)
        return comment
    }
}