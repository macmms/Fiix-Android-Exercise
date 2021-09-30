package fiix.challenge.fiixexercise.javasample;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fiix.challenge.fiixexercise.R;
import fiix.challenge.fiixexercise.dp.DataProcessor;

//TODO: Remove this class cause the kotlin version is actually implemented
public class MainActivity2 extends AppCompatActivity {

    //The warnings were bugging me. Seeing as how this class will be deleted in the future I have
    // chosen to suppress them.
    @SuppressWarnings({"rawtypes", "unchecked", "FieldMayBeFinal", "unused"})
    private DataProcessor dp = new DataProcessor(new LocalDataSource());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
