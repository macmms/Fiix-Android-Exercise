package fiix.challenge.fiixexercise.javasample;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fiix.challenge.fiixexercise.R;

public class MainFragment extends Fragment {
    private MainViewModel mViewModel;
    private RecyclerView recyclerViewView;
    //private Context context;
    private RecyclerAdapter recyclerAdapter;
    private List<TriviaQuestion2> qaList;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerViewView = v.findViewById(R.id.my_recycler_view);
        qaList = new ArrayList<>();
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mViewModel.getQa().observe(getActivity(), new Observer<List<TriviaQuestion2>>() {
            @Override
            public void onChanged(@Nullable List<TriviaQuestion2> questionAndAnswers) {
                qaList = questionAndAnswers;
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerViewView.setLayoutManager(layoutManager);
                recyclerAdapter = new RecyclerAdapter(qaList);
                recyclerViewView.setHasFixedSize(true);
                recyclerViewView.setAdapter(recyclerAdapter);
                recyclerViewView.setVisibility(View.VISIBLE);

            }
        });


    }

}
