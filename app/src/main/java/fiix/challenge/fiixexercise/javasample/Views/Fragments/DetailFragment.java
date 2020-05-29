package fiix.challenge.fiixexercise.javasample.Views.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;





import java.util.ArrayList;
import java.util.List;

import fiix.challenge.fiixexercise.R;
import fiix.challenge.fiixexercise.javasample.Model.TriviaQuestion;

import static fiix.challenge.fiixexercise.javasample.Util.Common.triviaQuestionList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    TextView mSaveTextView;
    EditText mQuestionTextview, mAnswerTextview;
    String index;
    List<TriviaQuestion> questionList = new ArrayList<>();

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mFragmentView = inflater.inflate(R.layout.fragment_detail, container, false);
        mainfunction(mFragmentView);
        return mFragmentView;
    }

    public void mainfunction(View mFragmentView) {
        mQuestionTextview = mFragmentView.findViewById(R.id.question);
        mAnswerTextview = mFragmentView.findViewById(R.id.answer);
        mSaveTextView = mFragmentView.findViewById(R.id.savebutton);
//getting argumens from previous page
        Bundle b = getArguments();
        String question = b.getString("question");
        String answer = b.getString("answer");
        index = b.getString("index");


        mQuestionTextview.setText(question);
        mAnswerTextview.setText(answer);
        mSaveTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//copying data to another arraylist
                questionList = triviaQuestionList;
                //removing old data
                questionList.remove(Integer.parseInt(index));
                TriviaQuestion question1 = new TriviaQuestion(mQuestionTextview.getText().toString(), mAnswerTextview.getText().toString());
                questionList.add(Integer.parseInt(index), question1);
           //adding the changed data to common list
                for (int i = 0; i < questionList.size(); i++) {
                    triviaQuestionList.set(i, questionList.get(i));
                }

                Toast.makeText(getActivity(), R.string.updated, Toast.LENGTH_LONG).show();

            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Details");
    }
}
