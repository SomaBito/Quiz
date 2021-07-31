package app.bito.soma.quiz

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quizIntent: Intent = Intent(this, AllQuizActivity::class.java)

        startButton.setOnClickListener{
            quizIntent.putExtra("key", "kamon")
            startActivity(quizIntent)

        }

        val quizIntent2: Intent = Intent(this, QuizActivity2::class.java)

        startButton2.setOnClickListener{
            quizIntent.putExtra("key","kamon2")
            startActivity(quizIntent)

        }

        val quizIntent3: Intent = Intent(this, QuizActivity3::class.java)

        startButton3.setOnClickListener{
            quizIntent.putExtra("key","ikusa")
            startActivity(quizIntent)

        }

        val quizIntent4: Intent = Intent(this, QuizActivity4::class.java)

        startButton4.setOnClickListener{
            quizIntent.putExtra("key","ikusa2")
            startActivity(quizIntent)

        }

        val quizIntent5: Intent = Intent(this, QuizActivity5::class.java)

        startButton5.setOnClickListener{
            quizIntent.putExtra("key","bushou")
            startActivity(quizIntent)

        }

        val quizIntent6: Intent = Intent(this, QuizActivity6::class.java)

        startButton6.setOnClickListener{
            quizIntent.putExtra("key","bushou2")
            startActivity(quizIntent)

        }


    }

    override fun onResume() {
        super.onResume()

        val dataStore: SharedPreferences = getSharedPreferences("DataStore", Context.MODE_PRIVATE)
        textView4.text = dataStore.getInt("seikaiCount",0).toString()
    }
}