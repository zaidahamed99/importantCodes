package com.example.ex11_file

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    private var editText: EditText? = null
    private var data: String? = null
    private val file = "mydata"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
    }

    fun save(view:View){
        data = editText!!.text.toString()

        //write or save data into a file
        try {
            val fOut = openFileOutput(file, Context.MODE_PRIVATE)
            fOut.write(data!!.toByteArray())
            fOut.close()
            Toast.makeText(baseContext, "Data has been saved", Toast.LENGTH_SHORT).show()
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun read(view:View){
        try {
            val fIn = openFileInput(file)
            val mFile = InputStreamReader(fIn)
            val br = BufferedReader(mFile)
            var line = br.readLine()
            val all = StringBuilder()
            while (line != null){
                all.append(line + "\n")
                line = br.readLine()
            }
            br.close()
            mFile.close()
            fIn.close()

            editText!!.setText(all)
            Toast.makeText(baseContext, "Read Data Successful", Toast.LENGTH_SHORT).show()

        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}