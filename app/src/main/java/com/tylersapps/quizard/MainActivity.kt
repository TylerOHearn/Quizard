package com.tylersapps.quizard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonStart: Button = findViewById(R.id.btnStart)
        val etName: AppCompatEditText = findViewById(R.id.etName)
        buttonStart.setOnClickListener {
            if (etName.text.toString().isEmpty()) {
                Timber.w("User left name blank")

                Toast.makeText(this, "Please Enter Your Name", Toast.LENGTH_SHORT).show()
            } else {
                Timber.i("User has submitted name")

                val intent = Intent(this@MainActivity, QuizQuestionsActivity::class.java)

                intent.putExtra(Constants.USER_NAME, etName.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}