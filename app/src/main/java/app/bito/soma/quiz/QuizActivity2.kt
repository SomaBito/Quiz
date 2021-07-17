package app.bito.soma.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity2 : AppCompatActivity() {

    val quizLists: List<QuizData> = listOf(
        QuizData("龍造寺隆信","細川忠興","鳥居元忠","龍造寺隆信", R.drawable.ryuuzoujikamon),
        QuizData("九鬼嘉隆","清水宗治","前田利家","前田利家",R.drawable.maedakamon),
        QuizData("柴田勝家","上杉謙信","大友宗麟","柴田勝家",R.drawable.shibatakamon),
        QuizData("今川義元","池田恒興","加藤清正","池田恒興",R.drawable.ikedakamon),
        QuizData("小西行長","京極高次","毛利輝元","毛利輝元",R.drawable.mourikamon)
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

                    val resultsIntent2: Intent = Intent( this, ResultActivity2::class.java)

                    resultsIntent2.putExtra("QuizCount", quizLists.size)

                    resultsIntent2.putExtra("CorrectCount", correctCount)

                    startActivity(resultsIntent2)
                    finish()

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

            } else {
                judgeImage.setImageResource(R.drawable.fuseikai)
                quizText.setText("不正解")
                correctAnswerText.text = "正解は$correctAnswer"
                answerButton1.isVisible = false
                answerButton2.isVisible = false
                answerButton3.isVisible = false
                kamonImage.isVisible = false

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

