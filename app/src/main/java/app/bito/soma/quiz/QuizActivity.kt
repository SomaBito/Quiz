package app.bito.soma.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_quiz.*
import java.awt.font.TextAttribute


class QuizActivity : AppCompatActivity() {

    val quizLists: List<List<String>> = listOf(
        listOf("武田信玄", "明智光秀", "北条氏政","武田信玄"),
        listOf("織田信長", "石田三成", "井伊直政","石田三成"),
        listOf("徳川家康", "豊臣秀吉", "毛利輝元","豊臣秀吉"),
        listOf("真田昌幸", "伊達政宗", "斎藤道三","真田昌幸"),
        listOf("上杉謙信", "浅井長政", "島津義久","島津義久")
    )

    val shuffledLists: List<List<String>> = quizLists.shuffled()

    var quizCount: Int = 0

    var correctAnswer: String = ""

    var correctCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

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

        answerButton1.text = question[0]
        answerButton2.text = question[1]
        answerButton3.text = question[2]

        correctAnswer = question[3]

        quizText.setText("これは誰の家紋？")
    }

    fun checkAnswer(answerText: String) {

        if (answerText == correctAnswer) {
            judgeImage.setImageResource(R.drawable.seikai)
            correctCount++
            quizText.setText("")

        } else {
            judgeImage.setImageResource(R.drawable.fuseikai)
            quizText.setText("不正解")
            correctAnswerText.text = "正解は$correctAnswer"
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