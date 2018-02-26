package com.example.sayan.understandingmemoryleak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView)findViewById(R.id.text);
        mButton = (Button)findViewById(R.id.button);
        mButton.setOnClickListener(new MyOnClickListener(mTextView));

        //Memoryleak of context
        Toast.makeText(this, mTextView.getText(), Toast.LENGTH_SHORT).show();
    }

    private static class MyOnClickListener implements View.OnClickListener{

        private WeakReference<TextView> mTextView;

        public MyOnClickListener(TextView mTextView) {
            this.mTextView = new WeakReference<TextView>(mTextView);
        }

        @Override
        public void onClick(View view) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mTextView.get().setText("Clicked");
        }
    }
}
