package fiix.challenge.fiixexercise.kotlinsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import fiix.challenge.fiixexercise.R

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var mockRepoData: MockRepo
    lateinit var recyclerViewAdapter: TriviaQuestionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.view_toolbar)
        toolbar.setTitle(R.string.app_title)
        recyclerView = findViewById(R.id.questionsRecyclerView)

        if (RawData.triviaQuestions == null) {
            mockRepoData = MockRepo()
            RawData.triviaQuestions = mockRepoData.triviaQuestions
            RawData.questionAnswered = HashMap()
        }

        recyclerViewAdapter = TriviaQuestionsAdapter(this, RawData.triviaQuestions!!)
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = recyclerViewAdapter
    }

    override fun onResume() {
        super.onResume()
        if (recyclerView.adapter != null) {
            recyclerView.adapter!!.notifyDataSetChanged()
        }
    }
}
