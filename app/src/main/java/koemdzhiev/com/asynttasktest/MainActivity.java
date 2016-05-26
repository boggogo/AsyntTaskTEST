package koemdzhiev.com.asynttasktest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private Button mStartTaskButton;
    private EditText mEditText;
    private ProgressBar mProgressmar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = (EditText)findViewById(R.id.editText);
        mProgressmar = (ProgressBar)findViewById(R.id.progressBar);
        mStartTaskButton = (Button)findViewById(R.id.button);
        mStartTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SimpleTask().execute(mEditText.getText().toString());
            }
        });
    }

    private class SimpleTask extends AsyncTask<String,Integer,String>{
        String output;
        @Override
        protected void onPreExecute() {
            output = "";
            mProgressmar.setProgress(0);
        }

        @Override
        protected String doInBackground(String... params) {

            try {

                // remove 'r' characters
                for(int i = 0; i < params[0].length();i++){
                    if(params[0].charAt(i) != 'r'){
                        output += params[0].charAt(i);
                    }

                    publishProgress((i*100)/params[0].length());
                    Thread.sleep(400);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return output;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.d("MainActivity","Progress" + values);
            mProgressmar.incrementProgressBy(values[0]);

            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            mEditText.setText(result);
            //mProgressmar.setProgress(100);
        }
    }
}
