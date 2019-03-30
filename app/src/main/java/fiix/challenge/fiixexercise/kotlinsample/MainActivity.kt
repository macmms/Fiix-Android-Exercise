package fiix.challenge.fiixexercise.kotlinsample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import fiix.challenge.fiixexercise.R
import kotlinx.android.synthetic.main.activity_main.*




class MainActivity : AppCompatActivity() {
    private var adapter: TriviaQuestionAdapter? = null
    lateinit var questionsList: ArrayList<TriviaQuestion>
    private val requestCode = 9
    private var position:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()
        loadDataInList()
    }

    /**
     * This function will get data from the repository
     */
    private fun getData(){
        questionsList = MockRepo().triviaQuestions
    }

    /**
     * This function will create the adapter object and load data
     * into the adapter.
     */
    private fun loadDataInList(){
        val layoutManager = LinearLayoutManager(this@MainActivity)
        recycler_view.layoutManager = layoutManager
        adapter = TriviaQuestionAdapter(questionsList){
            openDetailActivity(it)
        }
        recycler_view.adapter = adapter
    }

    /**
     * Function to open the detail activity as soon as user taps
     * on any of the question in the list. It will send the selected
     * position and item data to detail screen. DetailActivity will
     * be started for result.
     */
    private fun openDetailActivity(position:Int){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(getString(R.string.data),questionsList[position])
        intent.putExtra(getString(R.string.position),position)
        startActivityForResult(intent,requestCode)
    }


    /**
     * onActivityResult function will be called when user presses the back button
     * from detail screen. Here we are checking is requestCode and resultCode is matching.
     * It will call the updateListItem method to update the list item.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == this.requestCode && resultCode == RESULT_OK) {
            data?.let {
                position=data.getIntExtra(getString(R.string.position),position)
                updateListItem(position)
            }
        }
    }

    /**
     * This function will update the list item for the selected position.
     * @param position
     */
    private fun updateListItem(position: Int) {
        val triviaQuestion=questionsList[position]
        triviaQuestion.seen=true
        adapter.let {
            adapter?.notifyItemChanged(position)
        }
    }
}
