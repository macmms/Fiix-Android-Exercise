package fiix.challenge.fiixexercise.javasample;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import fiix.challenge.fiixexercise.R;
import fiix.challenge.fiixexercise.dp.DataProcessor;

public class MainActivity2 extends AppCompatActivity {

    private DataProcessor dp = new DataProcessor(new LocalDataSource());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> answers = dp.getAnswers();
    }
}
