package app.bito.soma.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AllQuizActivity : AppCompatActivity() {

    var shuffledLists: List<QuizData>? = null

    var quizCount: Int = 0

    var correctAnswer: String = ""

    var correctCount: Int = 0

    var second = 5
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_quiz)

        val key = intent.getStringExtra("key")

        when(key){
            "kamon" ->{
                shuffledLists = QuizLists1.shuffled()
            }

        }
    }
}