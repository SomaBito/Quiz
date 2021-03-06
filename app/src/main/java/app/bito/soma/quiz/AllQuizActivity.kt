package app.bito.soma.quiz

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_quiz.*

class AllQuizActivity : AppCompatActivity() {

    var shuffledLists: List<QuizData>? = null

    var quizCount: Int = 0

    var correctAnswer: String = ""

    var correctCount: Int = 0

    var second = 5

    var key:String? = ""

    val timer : CountDownTimer = object : CountDownTimer(5000,1000){

        override fun onFinish() {
            secondText.text = second.toString()
            judgeImage.setImageResource(R.drawable.fuseikai)
            quizText.setText("不正解")
            correctAnswerText.text = "正解は$correctAnswer"
            answerButton1.isVisible = false
            answerButton2.isVisible = false
            answerButton3.isVisible = false
            kamonImage.visibility = View.GONE

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
        setContentView(R.layout.activity_all_quiz)

        key = intent.getStringExtra("key")


        when(key){
            "kamon" ->{
                shuffledLists = QuizLists1.shuffled()
                title = "家紋"
            }
            "kamon2" ->{
                shuffledLists = QuizLists2.shuffled()
                title = "家紋（難）"
            }
            "ikusa" ->{
                shuffledLists = QuizLists3.shuffled()
                title = "戦"
            }
            "ikusa2" ->{
                shuffledLists = QuizLists4.shuffled()
                title = "戦（難）"
            }
            "bushou" ->{
                shuffledLists = QuizLists5.shuffled()
                title = "武将"
            }
            "bushou2" ->{
                shuffledLists = QuizLists6.shuffled()
                title = "武将（難）"
            }

        }

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

            if ( quizCount == shuffledLists?.size) {

                val resultsIntent: Intent = Intent( this, ResultActivity::class.java)

                resultsIntent.putExtra("QuizCount", shuffledLists?.size!!)

                resultsIntent.putExtra("CorrectCount", correctCount)

                resultsIntent.putExtra("key", key)

                startActivity(resultsIntent)
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

        if(shuffledLists == null){
            Log.d("suffledLists", "null")
            return
        }

        val question: QuizData = shuffledLists?.get(quizCount)!!

        Log.d("question", "${question.mondaibun}, ${question.image.toString()}")

        answerButton1.text = question.question1
        answerButton2.text = question.question2
        answerButton3.text = question.question3

        correctAnswer = question.answer

        if(question.image != null) {
            kamonImage.visibility = View.VISIBLE
            kamonImage.setImageResource(question.image)
        }else{
            kamonImage.visibility = View.INVISIBLE
        }

        quizText.text = question.mondaibun

        answerButton1.isVisible = true
        answerButton2.isVisible = true
        answerButton3.isVisible = true

        secondText.text = second.toString()
        second = 5

        timer.start()

    }

    fun checkAnswer(answerText: String) {
        timer.cancel()

        val datastore = getSharedPreferences("DataStore", Context.MODE_PRIVATE)
        var seikaiCount = datastore.getInt("seikaiCount", 0)


        if (answerText == correctAnswer) {
            judgeImage.setImageResource(R.drawable.seikai)
            correctCount++
            quizText.setText("")
            answerButton1.isVisible = false
            answerButton2.isVisible = false
            answerButton3.isVisible = false
            kamonImage.visibility = View.INVISIBLE
            secondText.isVisible = false


            seikaiCount = seikaiCount + 1


        } else {
            judgeImage.setImageResource(R.drawable.fuseikai)
            quizText.setText("不正解")
            correctAnswerText.text = "正解は$correctAnswer"
            answerButton1.isVisible = false
            answerButton2.isVisible = false
            answerButton3.isVisible = false
            kamonImage.visibility = View.INVISIBLE
            secondText.isVisible = false
            seikaiCount = 0

        }
        showAnswer()

        val editor = datastore.edit()
        editor.putInt("seikaiCount",seikaiCount)
        //if文使う
        //editor.putInt("saikoCount",seikaiCount)
        editor.apply()

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