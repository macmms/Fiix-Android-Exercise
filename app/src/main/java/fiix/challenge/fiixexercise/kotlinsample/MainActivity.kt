package fiix.challenge.fiixexercise.kotlinsample

import android.content.ClipData.newIntent
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import fiix.challenge.fiixexercise.R
import fiix.challenge.fiixexercise.dp.DataProcessor

class MainActivity : AppCompatActivity(), MainView, MainRvAdapter.QnaSelectListener {

    private val dp = DataProcessor(LocalDataSource())
    lateinit var presenter: MainPresenter
    lateinit var adapter: MainRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvMain: RecyclerView = findViewById(R.id.rv_main)
        adapter = MainRvAdapter(selectListener = this)
        rvMain.adapter = adapter

        presenter = MainPresenter(dp, this)
        presenter.bind()
    }

    override fun onDestroy() {
        presenter.unBind()
        super.onDestroy()
    }

    override fun showError(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show()
    }

    override fun showQuestions(questions: List<TriviaQuestion>) {
        Toast.makeText(this, "${questions.size} questions found", Toast.LENGTH_SHORT).show()
        adapter.updateData(questions)
    }

    override fun onItemSelected(triviaQuestion: TriviaQuestion) {
        startActivity(QnADetailsActivity.newIntent(this, triviaQuestion))
    }
}
