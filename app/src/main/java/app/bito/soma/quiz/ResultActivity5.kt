package app.bito.soma.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result5)

        val quizCount: Int = intent.getIntExtra( "QuizCount", 0)

        quizCountText.text = "$quizCount 問中..."

        val correctCount: Int = intent.getIntExtra("CorrectCount", 0)

        correctCountText.text = correctCount.toString()

        homekotoba.text = when (correctCount) {
            0 -> {
                "頑張ろう！"
            }
            1 -> {
                "まだまだ!"
            }
            2 -> {
                "頑張って！"
            }
            3 -> {
                "すごいね！"
            }
            4 -> {
                "さすが！"
            }
            5 -> {
                "天才！"
            }
            else ->{
                ""
            }
        }

        againButton.setOnClickListener {
            val quizIntent5: Intent = Intent(this, QuizActivity5::class.java)
            startActivity(quizIntent5)
        }

        homeButton.setOnClickListener {
            val mainIntent: Intent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
        }
    }
}

