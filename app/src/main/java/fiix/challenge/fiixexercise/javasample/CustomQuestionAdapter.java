package fiix.challenge.fiixexercise.javasample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fiix.challenge.fiixexercise.R;

/**
 * Adapter for the list view displaying the questions.
 */
public class CustomQuestionAdapter extends ArrayAdapter {

    CustomQuestionAdapter(Context context, List<TriviaQuestion2> questions){
        super(context, R.layout.custom_question_answer_layout, questions);
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

        if (question.mIsViewed){
            TextView answerSeenTextView = customQuestionView.findViewById(R.id.answerTextView);
            answerSeenTextView.setText(R.string.answer_viewed);
        }

        return customQuestionView;
    }
}
