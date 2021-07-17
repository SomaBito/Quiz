package app.bito.soma.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quizIntent: Intent = Intent(this, AllQuizActivity::class.java)

        startButton.setOnClickListener{
            quizIntent.putExtra("Key", "kamon")
           startActivity(quizIntent)
            finish()

        }

        val quizIntent2: Intent = Intent(this, QuizActivity2::class.java)

        startButton2.setOnClickListener{
            startActivity(quizIntent)
            finish()

        }

        val quizIntent3: Intent = Intent(this, QuizActivity3::class.java)

        startButton3.setOnClickListener{
            startActivity(quizIntent3)
            finish()

        }

        val quizIntent4: Intent = Intent(this, QuizActivity4::class.java)

        startButton4.setOnClickListener{
            startActivity(quizIntent4)
            finish()

        }

        val quizIntent5: Intent = Intent(this, QuizActivity5::class.java)

        startButton5.setOnClickListener{
            startActivity(quizIntent5)
            finish()

        }

        val quizIntent6: Intent = Intent(this, QuizActivity6::class.java)

        startButton6.setOnClickListener{
            startActivity(quizIntent6)
            finish()

        }



    }

}