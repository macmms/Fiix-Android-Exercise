package fiix.challenge.fiixexercise.javasample.Adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;




import java.util.List;

import fiix.challenge.fiixexercise.R;
import fiix.challenge.fiixexercise.javasample.Listners.DataSource;
import fiix.challenge.fiixexercise.javasample.Model.TriviaQuestion;

public class QuestionFragmentAdapter extends RecyclerView.Adapter<QuestionFragmentAdapter.MyViewHolder> {
private DataSource dataSource_listener;
    private List<TriviaQuestion> questionList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView question, answer, answerbutton;
public RelativeLayout item_card;
        public MyViewHolder(View view) {
            super(view);
            question = (TextView) view.findViewById(R.id.question);
            answerbutton = (TextView) view.findViewById(R.id.answerbutton);
            answer = (TextView) view.findViewById(R.id.answer);
            item_card = (RelativeLayout) view.findViewById(R.id.item_card);

        }
    }

    public void updateList(List<TriviaQuestion> list) {
        this.  questionList = list;

        notifyDataSetChanged();
    }

    public QuestionFragmentAdapter(List<TriviaQuestion> questionList, DataSource dataSourcelistener) {
        this.questionList = questionList;
        this.dataSource_listener = dataSourcelistener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_question, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final TriviaQuestion triviaQuestion = questionList.get(position);
        holder.question.setText(triviaQuestion.getQuestion());
        holder.answer.setText(triviaQuestion.getAnswer());
        holder.answer.setVisibility(View.GONE);
        holder.answerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.answer.setVisibility(View.VISIBLE);
                holder.answerbutton.setVisibility(View.GONE);
            }
        });
holder.item_card.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        dataSource_listener.go_to_detailpage(v,triviaQuestion.getQuestion(),triviaQuestion.getAnswer(), String.valueOf(position));
    }
});
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

}
