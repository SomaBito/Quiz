package app.bito.soma.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
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
import kotlinx.android.synthetic.main.activity_quiz.secondText as secondText1

class QuizActivity5 : AppCompatActivity() {

    val quizLists: List<List<String>> = listOf(
        listOf("1497~1571年","安芸出身（広島）","毛利輝元","毛利隆元","毛利元就","毛利元就"),
        listOf("1519~1560年","駿河出身（静岡）","石田三成","今川義元","小早川秀秋","今川義元"),
        listOf("1538~1599年","尾張出身（愛知）","織田信長","前田利家","本多忠勝","前田利家"),
        listOf("1547~1611年","信濃出身（長野）","真田信之","真田幸村","真田昌幸","真田昌幸"),
        listOf("1561~1602年","遠江出身（静岡）","井伊直政","柴田勝家","伊達政宗","井伊直政")
    )

    val shuffledLists: List<List<String>> = quizLists.shuffled()

    var quizCount: Int = 0

    var correctAnswer: String = ""

    var correctCount: Int = 0

    var second = 5

    val timer : CountDownTimer = object : CountDownTimer(5000,1000){

        override fun onFinish() {
            secondText.text = second.toString()
            judgeImage.setImageResource(R.drawable.fuseikai)
            quizText.isVisible = true
            quizText.setText("不正解")
            correctAnswerText.text = "正解は$correctAnswer"
            answerButton1.isVisible = false
            answerButton2.isVisible = false
            answerButton3.isVisible = false
            textView.isVisible = false
            textView2.isVisible = false


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
        setContentView(R.layout.activity_quiz4)

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

                val resultsIntent5: Intent = Intent( this, ResultActivity5::class.java)

                resultsIntent5.putExtra("QuizCount", quizLists.size)

                resultsIntent5.putExtra("CorrectCount", correctCount)

                startActivity(resultsIntent5)

            }else{
                judgeImage.isVisible = false
                nextButton.isVisible = false

                answerButton1.isEnabled = true
                answerButton2.isEnabled = true
                answerButton3.isEnabled = true

                textView.isVisible = true
                textView2.isVisible = true

                correctAnswerText.text = ""

                showquestion()

            }
        }
    }

    fun showquestion() {

        secondText.isVisible = true
        val question: List<String> = shuffledLists[quizCount]

        Log.d("debug", question.toString())

        textView.text = question[0]
        textView2.text = question[1]

        answerButton1.text = question[2]
        answerButton2.text = question[3]
        answerButton3.text = question[4]

        correctAnswer = question[5]

        answerButton1.isVisible = true
        answerButton2.isVisible = true
        answerButton3.isVisible = true

        quizText.isVisible = false

        textView.isVisible = true
        textView2.isVisible = true

        secondText.text = second.toString()
        second = 6
        timer.start()

    }

    fun checkAnswer(answerText: String) {
        timer.cancel()

        if (answerText == correctAnswer) {
            judgeImage.setImageResource(R.drawable.seikai)
            correctCount++
            quizText.isVisible = false
            answerButton1.isVisible = false
            answerButton2.isVisible = false
            answerButton3.isVisible = false
            textView.isVisible = false
            textView2.isVisible = false

        } else {
            judgeImage.setImageResource(R.drawable.fuseikai)
            quizText.isVisible = true
            correctAnswerText.text = "正解は$correctAnswer"
            answerButton1.isVisible = false
            answerButton2.isVisible = false
            answerButton3.isVisible = false
            textView.isVisible = false
            textView2.isVisible = false


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


