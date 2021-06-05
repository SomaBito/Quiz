package app.bito.soma.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result4)

        val quizCount: Int = intent.getIntExtra( "QuizCount", 0)

        quizCountText.text = "$quizCount 問中..."

        val correctCount: Int = intent.getIntExtra("CorrectCount", 0)

        correctCountText.text = correctCount.toString()

        againButton.setOnClickListener {
            val quizIntent4: Intent = Intent(this, QuizActivity4::class.java)
            startActivity(quizIntent4)
        }

        homeButton.setOnClickListener {
            val mainIntent: Intent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
        }
    }
}