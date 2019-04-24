package fiix.challenge.fiixexercise.javasample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import fiix.challenge.fiixexercise.R;

/**
 * The activity that displays the question and answer of the selected list item.
 */
public class QuestionAnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.custom_question_answer_layout);

        TextView questionTextView = this.findViewById(R.id.questionTextView);
        TextView answerTextView = this.findViewById(R.id.answerTextView);

        questionTextView.setText(getIntent().getStringExtra("question"));
        answerTextView.setText(getIntent().getStringExtra("answer"));
    }
}
