package app.bito.soma.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quizIntent: Intent = Intent(this, QuizActivity::class.java)

        startButton.setOnClickListener{
           startActivity(quizIntent)

        }

        val quizIntent2: Intent = Intent(this, QuizActivity2::class.java)

        startButton2.setOnClickListener{
            startActivity(quizIntent2)

        }

    }

}