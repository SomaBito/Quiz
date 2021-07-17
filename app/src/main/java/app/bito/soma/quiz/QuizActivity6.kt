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
import kotlinx.android.synthetic.main.activity_quiz3.secondText as secondText1

class QuizActivity6 : AppCompatActivity() {

    val quizLists: List<List<String>> = listOf(
        listOf("1557~1615年","出羽出身（山形）","片倉小十郎","丹羽長秀","山本勘助","片倉小十郎"),
        listOf("1521~1555年","周防出身（山口）","北条早雲","竹中半兵衛","陶晴賢","陶晴賢"),
        listOf("1552~1615年","摂津出身（大阪）","加藤清正","高山右近","立花道雪","高山右近"),
        listOf("1533~1605年","尾張出身（愛知）","前田慶次","宇喜多秀家","酒井忠次","前田慶次"),
        listOf("1548~1606年","三河出身（愛知）","上杉景勝","大谷吉継","榊原康政","榊原康政")
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

                val resultsIntent6: Intent = Intent( this, ResultActivity6::class.java)

                resultsIntent6.putExtra("QuizCount", quizLists.size)

                resultsIntent6.putExtra("CorrectCount", correctCount)

                startActivity(resultsIntent6)

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
        second = 5
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



