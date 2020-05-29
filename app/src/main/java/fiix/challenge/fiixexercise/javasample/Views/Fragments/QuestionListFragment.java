package fiix.challenge.fiixexercise.javasample.Views.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.navigation.Navigation;


import com.example.triviaquizapp.Util.DataProcessor;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import fiix.challenge.fiixexercise.R;
import fiix.challenge.fiixexercise.javasample.Adapters.QuestionFragmentAdapter;
import fiix.challenge.fiixexercise.javasample.Listners.DataSource;
import fiix.challenge.fiixexercise.javasample.Model.TriviaQuestion;

import static fiix.challenge.fiixexercise.javasample.Util.Common.triviaQuestionList;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionListFragment extends Fragment implements DataSource {
   RecyclerView mQuestionList;
    List<TriviaQuestion> triviaQuestions = new ArrayList<>();
    List<TriviaQuestion> answers;
    private QuestionFragmentAdapter questionFragmentAdapter;
    DataProcessor dp = new DataProcessor(QuestionListFragment.this);
    public QuestionListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mFragmentView = inflater.inflate(R.layout.fragment_question, container, false);

        mainfunction(mFragmentView);
        return mFragmentView;
    }

    public void mainfunction(View mFragmentView) {
        mQuestionList = mFragmentView.findViewById(R.id.question_reclerview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mQuestionList.setLayoutManager(mLayoutManager);
        mQuestionList.setItemAnimator(new DefaultItemAnimator());

        //check if there is any data in common list;if not fetch it
        if((triviaQuestionList==null)||(triviaQuestionList.size()==0)){
            answers = dp.getQustionAnswers();
            questionFragmentAdapter=new QuestionFragmentAdapter(answers,QuestionListFragment.this);
        }
        else {
            questionFragmentAdapter=new QuestionFragmentAdapter(triviaQuestionList,QuestionListFragment.this);
        }

        //setting recyclerviewbadapter
        mQuestionList.setAdapter(questionFragmentAdapter);

    }

    @NotNull
    @Override
    public List<TriviaQuestion> getData() {
        //adding data to arraylist
        triviaQuestions.add(new TriviaQuestion("How many books are in the Chronicles of Narnia series?", "7"));
        triviaQuestions.add(new TriviaQuestion("Green Eggs and Ham is a book by which author?", "Dr. Seuss"));
        triviaQuestions.add(new TriviaQuestion("What is the title of the first Sherlock Holmes book by Arthur Conan Doyle?", "A Study in Scarlet"));
        triviaQuestions.add(new TriviaQuestion("Typically, how many keys are on a piano?", "88"));
        triviaQuestions.add(new TriviaQuestion("In what year was the first Transformers movie released?", "1986"));
        triviaQuestions.add(new TriviaQuestion("Which movie includes a giant bunny-like spirit who has magic powers including growing trees?", "My Neighbor Totoro"));
        triviaQuestions.add(new TriviaQuestion("In the original Star Wars trilogy, David Prowse was the actor who physically portrayed Darth Vader", "True"));
        triviaQuestions.add(new TriviaQuestion("In Big Hero 6, what fictional city is the Big Hero 6 from?", "San Fransokyo"));
        triviaQuestions.add(new TriviaQuestion("Where does the original Friday The 13th movie take place?", "Camp Crystal Lake"));
        triviaQuestions.add(new TriviaQuestion("How many pieces are there on the board at the start of a game of chess?", "32"));
        triviaQuestions.add(new TriviaQuestion("How many points is the Z tile worth in Scrabble?", "10"));
        triviaQuestions.add(new TriviaQuestion("Talos, the mythical giant bronze man, was the protector of which island?", "Crete"));
        triviaQuestionList=triviaQuestions;
        return triviaQuestions;


    }

    @Override
    public void go_to_detailpage(View v, String question, String answer, String index) {

        //put arguments to pass to details page
        Bundle bundle = new Bundle();
        bundle.putString("question", question);
        bundle.putString("answer", answer);
        bundle.putString("index", index);

        Navigation.findNavController(v).navigate(R.id.action_questionFragment_to_detailFragment2,bundle);
    }
    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.app_name);
        //refreshing list if any change occur

        if((triviaQuestionList==null)||(triviaQuestionList.size()==0)){
            answers = dp.getQustionAnswers();
            questionFragmentAdapter=new QuestionFragmentAdapter(answers,QuestionListFragment.this);
        }
        else {
            questionFragmentAdapter=new QuestionFragmentAdapter(triviaQuestionList,QuestionListFragment.this);
        }

        mQuestionList.setAdapter(questionFragmentAdapter);


    }
}
