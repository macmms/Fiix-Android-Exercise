package fiix.challenge.fiixexercise.javasample;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

class MainViewModel extends ViewModel {

    private final MockRepo2 repository;
    private MutableLiveData<List<TriviaQuestion2>> mutableQAList;

    public MainViewModel() {
        repository = new MockRepo2();
    }

    LiveData<List<TriviaQuestion2>> getQa() {
        if (mutableQAList == null) {
            mutableQAList = new MutableLiveData<>();
            mutableQAList.setValue(repository.getTriviaQuestions());
        }
        return mutableQAList;

    }

    void update(TriviaQuestion2 qa) {
        if (qa != null) {
            List<TriviaQuestion2> qaList = mutableQAList.getValue();
            if(qaList != null){
                if(qaList.indexOf(qa)!=-1){
                    qaList.set(qaList.indexOf(qa), qa);
                    mutableQAList.setValue(qaList);
                }
            }

        }
    }

}
