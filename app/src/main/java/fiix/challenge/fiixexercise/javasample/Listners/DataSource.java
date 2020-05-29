package fiix.challenge.fiixexercise.javasample.Listners;

import android.view.View;



import java.util.List;

import fiix.challenge.fiixexercise.javasample.Model.TriviaQuestion;

public interface DataSource {
    List<TriviaQuestion> getData();
void go_to_detailpage(View view,String question, String answer,String index);
}
