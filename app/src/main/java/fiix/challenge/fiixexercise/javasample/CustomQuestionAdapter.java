package fiix.challenge.fiixexercise.javasample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fiix.challenge.fiixexercise.R;

/**
 * Adapter for the list view displaying the questions.
 */
public class CustomQuestionAdapter extends ArrayAdapter {

    /**
     * The list to track of the list item positions that have been clicked.
     */
    private List<Integer> mPositionsClicked;

    CustomQuestionAdapter(Context context, List<TriviaQuestion2> questions){
        super(context, R.layout.custom_question_answer_layout, questions);
        mPositionsClicked = new ArrayList<>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater adapterInflater = LayoutInflater.from(getContext());
        View customQuestionView = adapterInflater.inflate
                (R.layout.custom_question_answer_layout, parent, false);

        TriviaQuestion2 question = (TriviaQuestion2)getItem(position);
        String questionText = question.question;

        TextView questionTextView = customQuestionView.findViewById(R.id.questionTextView);
        questionTextView.setText(questionText);

        // Checking with the tracker if the question in this position was already clicked.
        // If yes, then a text is displayed in the main list view notifying the user that the
        // answer was viewed.
        if (mPositionsClicked.contains(position)){
            TextView answerSeenTextView = customQuestionView.findViewById(R.id.answerTextView);
            answerSeenTextView.setText(R.string.answer_viewed);
        }

        return customQuestionView;
    }

    /**
     * Updates the clicked list items' positions tracker.
     * @param position the position of the list item clicked.
     */
    void updatePositionsClicked(int position){
        mPositionsClicked.add(position);
    }
}
