package fiix.challenge.fiixexercise.kotlinsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import fiix.challenge.fiixexercise.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TriviaQuestionsAdapter.ItemClickListener{

    private lateinit var triviaViewModel: TriviaViewModel
    private lateinit var adapter: TriviaQuestionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = TriviaQuestionsAdapter(this)
        val recyclerView = triviaRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        triviaViewModel = ViewModelProvider(this).get(TriviaViewModel::class.java)
        triviaViewModel.retrieveQuestions()
        triviaViewModel.retrieveAnswers()

        triviaViewModel.triviaQuestionsLiveData.observe(this, Observer<ArrayList<TriviaQuestion>> {
            triviaQuestions -> adapter.setQuestionsData(triviaQuestions)
        })
        triviaViewModel.triviaAnswersLiveData.observe(this, Observer<List<String>> {
            answers -> adapter.setAnswersData(answers)
        })
    }

    override fun onItemClick(position: Int) {
        val trivia = adapter.getTriviaAtPosition(position)
        val bundle = Bundle()
        bundle.putSerializable(getString(R.string.TRIVIA_BUNDLE_KEY), trivia)
        bundle.putInt(getString(R.string.POSITION_BUNDLE_KEY), position)
        val fragment = DetailScreenFragment()
        fragment.arguments = bundle
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(fragment::class.java.name)
        fragmentTransaction.add(R.id.fragment_holder, fragment).commit()
    }

    override fun onAnswerButtonClick(position: Int) {
        adapter.getTriviaAtPosition(position).isVisible = true
        adapter.notifyItemChanged(position)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStackImmediate()
        } else super.onBackPressed()
    }

    fun editTrivia(trivia : TriviaQuestion, position: Int) {
        adapter.updateItem(trivia, position)
    }
}
