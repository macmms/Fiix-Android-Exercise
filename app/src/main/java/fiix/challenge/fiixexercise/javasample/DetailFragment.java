package fiix.challenge.fiixexercise.javasample;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import fiix.challenge.fiixexercise.R;

public class DetailFragment extends Fragment {

    private MainViewModel mViewModel;
    private TextView queTextView;
    private TextView ansTextView;
    private TriviaQuestion2 qaOneItem;
    private boolean alreadyAnswered = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            qaOneItem = bundle.getParcelable(RecyclerAdapter.QAPARCELABLE);


            if (qaOneItem != null) {

                if (!qaOneItem.isAnswered()) {
                    qaOneItem.setAnswered(true);
                } else {
                    alreadyAnswered = true;
                }

                queTextView = v.findViewById(R.id.txtDetailQuestion);
                ansTextView = v.findViewById(R.id.txtDetailAnswer);
                queTextView.setText(getResources().getString(R.string.Questiontext)+ ": \n" + qaOneItem.question);
                ansTextView.setText(getResources().getString(R.string.Answertext)+": \n" + qaOneItem.answer);
            }
        }

        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        if (qaOneItem != null && !alreadyAnswered) {
            mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
            mViewModel.update(qaOneItem);
        }

        super.onActivityCreated(savedInstanceState);
    }


}