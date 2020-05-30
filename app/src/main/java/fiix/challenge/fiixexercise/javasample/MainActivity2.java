package fiix.challenge.fiixexercise.javasample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import fiix.challenge.fiixexercise.R;
import fiix.challenge.fiixexercise.dp.DataProcessor;

public class MainActivity2 extends AppCompatActivity {

    private DataProcessor dp = new DataProcessor(new LocalDataSource());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
