package fiix.challenge.fiixexercise.javasample;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import fiix.challenge.fiixexercise.R;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RowViewHolder> {
    static final String QAPARCELABLE = "qaparcelable";
    private static List<TriviaQuestion2> listItems;

    RecyclerAdapter(List<TriviaQuestion2> listItems) {
        RecyclerAdapter.listItems = listItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view;
        if (listItems.get(i).isAnswered()) {
            view = layoutInflater.inflate(R.layout.item_row_answered, viewGroup, false);
        } else {
            view = layoutInflater.inflate(R.layout.item_row, viewGroup, false);
        }

        return new RowViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RowViewHolder viewHolder, int i) {
        TriviaQuestion2 oneItem = listItems.get(i);
        viewHolder.txtQuestion.setText(oneItem.question);
    }

    @Override
    public int getItemCount() {
        if (listItems == null) {
            return 0;
        }
        return listItems.size();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    static class RowViewHolder extends RecyclerView.ViewHolder {
        final TextView txtQuestion;
        final RelativeLayout relativeLayout;

        RowViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtQuestion = itemView.findViewById(R.id.txtQuestion);
            relativeLayout = itemView.findViewById(R.id.rltOut);

            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    AppCompatActivity activity = (AppCompatActivity) itemView.getContext();
                    DetailFragment detailFragment = new DetailFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(RecyclerAdapter.QAPARCELABLE, listItems.get(pos));
                    detailFragment.setArguments(bundle);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, detailFragment).addToBackStack("Detailfragment").commit();
                }
            });

        }

    }
}
