package fiix.challenge.fiixexercise.kotlinsample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingwithmitch.kotlinrecyclerviewexample.QuestionRecyclerAdapter
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
        val answers = dp.getAnswers()
        initRecyclerView()
        addDataSet()
    }

    private fun addDataSet() {
        data = MockRepo.triviaQuestions
        val dbHelper: QuestionDbHelper? = QuestionDbHelper.getInstance(this)

        questionAdapter.submitList(data, this)
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


    override fun onAnswerButtonClick(question: TriviaQuestion?) {
        question?.isShowing = true
        data.find { it.id == question?.id }?.isShowing = true
        questionAdapter.submitList(data, this)
        questionAdapter.notifyDataSetChanged()
    }

    companion object {
        const val EXTRA_QUESTION = "extraQuestion"
    }
}
