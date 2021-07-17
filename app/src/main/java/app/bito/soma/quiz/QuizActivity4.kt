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

class QuizActivity4 : AppCompatActivity() {

    val quizLists: List<List<String>> = listOf(
        listOf("1578~1580年","別所長治vs羽柴秀吉","四万十川の戦い","三木城の戦い","今山の戦い","三木城の戦い"),
        listOf("1572年","徳川家康vs武田信玄","三方ヶ原の戦い","三増峠の戦い","高遠城の戦い","三方ヶ原の戦い"),
        listOf("1560~1570年","上杉謙信vs北条氏康","摺上原の戦い","長谷堂城の戦い","唐沢山城の戦い","唐沢山城の戦い"),
        listOf("1584年","島津義久vs龍造寺隆信","賤ヶ岳の戦い","沖田畷の戦い","耳川の戦い","沖田畷の戦い"),
        listOf("1585年","佐竹義重vs伊達政宗","人取橋の戦い","七尾城の戦い","厳島の戦い","人取橋の戦い")
    )

    val shuffledLists: List<List<String>> = quizLists.shuffled()

    var quizCount: Int = 0

    var correctAnswer: String = ""

    var correctCount: Int = 0

    var second = 6

    val timer : CountDownTimer = object : CountDownTimer(5000,1000){

        override fun onFinish() {
            secondText.text = second.toString()
            judgeImage.setImageResource(R.drawable.fuseikai)
            quizText.setText("不正解")
            correctAnswerText.text = "正解は$correctAnswer"
            answerButton1.isVisible = false
            answerButton2.isVisible = false
            answerButton3.isVisible = false


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

                val resultsIntent4: Intent = Intent( this, ResultActivity4::class.java)

                resultsIntent4.putExtra("QuizCount", quizLists.size)

                resultsIntent4.putExtra("CorrectCount", correctCount)

                startActivity(resultsIntent4)

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

