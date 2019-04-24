package fiix.challenge.fiixexercise.javasample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import fiix.challenge.fiixexercise.R;

public class MainActivity2 extends AppCompatActivity {

    /**
     * The adapter for the list view.
     */
    CustomQuestionAdapter mTriviaQuestionsAdapter;

    /**
     * The list view for the questions to be displayed.
     */
    ListView mTriviaListView;

    /**
     * This list of questions and answers.
     */
    List<TriviaQuestion2> mTriviaQuestions;

    /**
     * The mock repo containing the TriviaQuestion objects.
     */
    MockRepo2 mMockRepo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mTriviaListView = findViewById(R.id.triviaListView);

        mMockRepo = new MockRepo2();
        mTriviaQuestions = mMockRepo.getTriviaQuestions();
        mTriviaQuestionsAdapter = new CustomQuestionAdapter(this, mTriviaQuestions);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTriviaListView.setAdapter(mTriviaQuestionsAdapter);

        mTriviaListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        updatePositionsClicked(position);

                        Intent questionAnswerActivity =
                                new Intent("android.intent.action.QUESTIONANSWER");
                        questionAnswerActivity.putExtra("question", mTriviaQuestions.get(position).question);
                        questionAnswerActivity.putExtra("answer", mTriviaQuestions.get(position).answer);

                        startActivity(questionAnswerActivity);
                    }
                }
        );
    }

    /**
     * Update the tracker in the adapter.
     * @param position the position of the list item clicked.
     */
    private void updatePositionsClicked(int position){
        mTriviaQuestionsAdapter.updatePositionsClicked(position);
    }
}
