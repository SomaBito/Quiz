package app.bito.soma.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.ImageView
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.activity_quiz.answerButton1
import kotlinx.android.synthetic.main.activity_quiz.answerButton2
import kotlinx.android.synthetic.main.activity_quiz.answerButton3
import kotlinx.android.synthetic.main.activity_quiz.correctAnswerText
import kotlinx.android.synthetic.main.activity_quiz.judgeImage
import kotlinx.android.synthetic.main.activity_quiz.nextButton
import kotlinx.android.synthetic.main.activity_quiz.quizText
import kotlinx.android.synthetic.main.activity_quiz6.*
import kotlinx.android.synthetic.main.activity_result.*
import java.awt.font.TextAttribute
import kotlinx.android.synthetic.main.activity_quiz6.secondText as secondText1


class QuizActivity : AppCompatActivity() {

    val quizLists: List<QuizData> = listOf(
        QuizData("武田信玄", "明智光秀", "北条氏政","武田信玄", R.drawable.takedakamon),
        QuizData("織田信長", "石田三成", "井伊直政","石田三成", R.drawable.ishidakamon),
        QuizData("徳川家康", "豊臣秀吉", "毛利輝元","豊臣秀吉", R.drawable.toyotomikamon),
        QuizData("真田昌幸", "伊達政宗", "斎藤道三","真田昌幸", R.drawable.sanadakamon),
        QuizData("上杉謙信", "浅井長政", "島津義久","島津義久", R.drawable.shimazukamon)
    )

    val shuffledLists: List<QuizData> = quizLists.shuffled()

    var quizCount: Int = 0

    var correctAnswer: String = ""

    var correctCount: Int = 0

    var second = 5

    val timer : CountDownTimer = object : CountDownTimer(5000,1000){

        override fun onFinish() {
            secondText.text = second.toString()
            judgeImage.setImageResource(R.drawable.fuseikai)
            quizText.setText("不正解")
            correctAnswerText.text = "正解は$correctAnswer"
            answerButton1.isVisible = false
            answerButton2.isVisible = false
            answerButton3.isVisible = false
            kamonImage.isVisible = false

            showAnswer()

            quizCount++

            second = 6
        }

        override fun onTick(millisUntilFinished: Long) {
            second = second - 1
            secondText.text = second.toString()
        }
    }


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

                val resultsIntent: Intent = Intent( this, ResultActivity::class.java)

                resultsIntent.putExtra("QuizCount", quizLists.size)

                resultsIntent.putExtra("CorrectCount", correctCount)

                startActivity(resultsIntent)

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

        secondText.isVisible = true

        val question: QuizData = shuffledLists[quizCount]

        Log.d("debug", question.toString())

        answerButton1.text = question.question1
        answerButton2.text = question.question2
        answerButton3.text = question.question3

        correctAnswer = question.answer

        kamonImage.setImageResource(question.image)

        quizText.setText("これは誰の家紋？")

        answerButton1.isVisible = true
        answerButton2.isVisible = true
        answerButton3.isVisible = true
        kamonImage.isVisible = true

        secondText.text = second.toString()
        second = 6

        timer.start()

    }

    fun checkAnswer(answerText: String) {
        timer.cancel()

        if (answerText == correctAnswer) {
            judgeImage.setImageResource(R.drawable.seikai)
            correctCount++
            quizText.setText("")
            answerButton1.isVisible = false
            answerButton2.isVisible = false
            answerButton3.isVisible = false
            kamonImage.isVisible = false
            secondText.isVisible = false

        } else {
            judgeImage.setImageResource(R.drawable.fuseikai)
            quizText.setText("不正解")
            correctAnswerText.text = "正解は$correctAnswer"
            answerButton1.isVisible = false
            answerButton2.isVisible = false
            answerButton3.isVisible = false
            kamonImage.isVisible = false
            secondText.isVisible = false

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


    }}














