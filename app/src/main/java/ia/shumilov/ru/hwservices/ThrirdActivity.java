package ia.shumilov.ru.hwservices;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import static ia.shumilov.ru.hwservices.MainActivity.MY_FILTER;
import static ia.shumilov.ru.hwservices.MainActivity.mIntentFilter;

public class ThrirdActivity extends AppCompatActivity {

    private BroadcastReceiver mReciver;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private List<TextView> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thrird);
        textView1 = findViewById(R.id.textView1Act3);
        textView2 = findViewById(R.id.textView2Act3);
        textView3 = findViewById(R.id.textView3Act3);
        list = Arrays.asList(textView1, textView2, textView3);

        mReciver = new BroadcastReceiver() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onReceive(Context context, Intent intent) {
                String msg =  ("Получено сообщение для формы 3 по broadcast: " +
                        intent.getStringExtra(MY_FILTER));
                setTextAllTextView(msg);
            }
        };
    }

    // регистрируем широковещательный приёмник
    public void registerBroadcastReceiver() {
        this.registerReceiver(mReciver, mIntentFilter);
        setTextAllTextView("приемник включен");
    }


    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReciver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerBroadcastReceiver();
    }

    private void setTextAllTextView(String msg){
        for(TextView view : list){
            view.setText(msg);
        }

    }
}
