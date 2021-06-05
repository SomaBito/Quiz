package app.bito.soma.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.activity_quiz.answerButton1
import kotlinx.android.synthetic.main.activity_quiz.answerButton2
import kotlinx.android.synthetic.main.activity_quiz.answerButton3
import kotlinx.android.synthetic.main.activity_quiz.correctAnswerText
import kotlinx.android.synthetic.main.activity_quiz.judgeImage
import kotlinx.android.synthetic.main.activity_quiz.nextButton
import kotlinx.android.synthetic.main.activity_quiz.quizText
import kotlinx.android.synthetic.main.activity_quiz3.*

class QuizActivity3 : AppCompatActivity() {

    val quizLists: List<List<String>> = listOf(
        listOf("1553~1564年","上杉謙信vs武田信玄","川中島の戦い","姉川の戦い","石山合戦","川中島の戦い"),
        listOf("1560年","織田信長vs今川義元","備中高松城の戦い","桶狭間の戦い","山崎の戦い","桶狭間の戦い"),
        listOf("","","","","",""),
        listOf("","","","","",""),
        listOf("","","","","","")
    )

    val shuffledLists: List<List<String>> = quizLists.shuffled()

    var quizCount: Int = 0

    var correctAnswer: String = ""

    var correctCount: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz3)

        showquestion()

        answerButton1.setOnClickListener {
            checkAnswer(answerButton1.text.toString())
        }

        answerButton2.setOnClickListener {
            checkAnswer(answerButton2.text.toString())
        }

        answerButton3.setOnClickListener {
            checkAnswer(answerButton3.text.toString())
        }

        nextButton.setOnClickListener {

            if ( quizCount == quizLists.size) {

                val resultsIntent2: Intent = Intent( this, ResultActivity2::class.java)

                resultsIntent2.putExtra("QuizCount", quizLists.size)

                resultsIntent2.putExtra("CorrectCount", correctCount)

                startActivity(resultsIntent2)

            }else{
                judgeImage.isVisible = false
                nextButton.isVisible = false

                answerButton1.isEnabled = true
                answerButton2.isEnabled = true
                answerButton3.isEnabled = true

                correctAnswerText.text = ""

                showquestion()

            }
        }
    }

    fun showquestion() {
        val question: List<String> = shuffledLists[quizCount]

        Log.d("debug", question.toString())

        textView.text = question[1]
        textView2.text = question[2]

        answerButton1.text = question[3]
        answerButton2.text = question[4]
        answerButton3.text = question[5]

        correctAnswer = question[6]

        answerButton1.isVisible = true
        answerButton2.isVisible = true
        answerButton3.isVisible = true

        quizText.isVisible = false
    }

    fun checkAnswer(answerText: String) {

        if (answerText == correctAnswer) {
            judgeImage.setImageResource(R.drawable.seikai)
            correctCount++
            quizText.isVisible = false
            answerButton1.isVisible = false
            answerButton2.isVisible = false
            answerButton3.isVisible = false


        } else {
            judgeImage.setImageResource(R.drawable.fuseikai)
            quizText.isVisible = true
            correctAnswerText.text = "正解は$correctAnswer"
            answerButton1.isVisible = false
            answerButton2.isVisible = false
            answerButton3.isVisible = false


        }
        showAnswer()

        quizCount++
    }

    fun showAnswer(){



        judgeImage.isVisible = true

        nextButton.isVisible = true

        answerButton1.isEnabled = false
        answerButton2.isEnabled = false
        answerButton3.isEnabled = false


    }
}


