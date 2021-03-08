package fiix.challenge.fiixexercise.kotlinsample

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingwithmitch.kotlinrecyclerviewexample.TopSpacingItemDecoration
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.dp.DataProcessor
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), QuestionRecyclerAdapter.OnItemClickListener {

    val dp = DataProcessor(LocalDataSource())
    var data: ArrayList<TriviaQuestion> = arrayListOf()
    private lateinit var questionAdapter: QuestionRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()

    }

    private fun addDataSet() {
        val dbHelper: QuestionDbHelper? = QuestionDbHelper(this)
        if (dbHelper?.allQuestion != null && dbHelper?.allQuestion.size != 0) {
            questionAdapter.submitList(dbHelper.allQuestion, this)
        } else {
            data = MockRepo.triviaQuestions
            for (question in data) {
                dbHelper?.insertQuestion(question)
            }
            questionAdapter.submitList(data, this)
        }
    }

    override fun onResume() {
        super.onResume()
        addDataSet()
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            questionAdapter = QuestionRecyclerAdapter()
            adapter = questionAdapter
        }
    }

    override fun onItemClick(item: TriviaQuestion?) {
        val intent = Intent(this@MainActivity, QuestionDetailsActivity::class.java)
        intent.putExtra(EXTRA_QUESTION, item)
        startActivity(intent)
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onAnswerButtonClick(question: TriviaQuestion?) {
        question?.isShowing = true
        val dbHelper: QuestionDbHelper? = QuestionDbHelper(this)
        dbHelper?.update(question!!)
        var data1: ArrayList<TriviaQuestion> = dbHelper?.allQuestion!!
        data1?.find { it.id == question?.id }?.isShowing = true
        questionAdapter.submitList(data1, this)
        questionAdapter.notifyDataSetChanged()
    }

    companion object {
        const val EXTRA_QUESTION = "extraQuestion"
    }
}
