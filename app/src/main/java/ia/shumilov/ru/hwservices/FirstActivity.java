package ia.shumilov.ru.hwservices;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static ia.shumilov.ru.hwservices.MainActivity.MY_FILTER;
import static ia.shumilov.ru.hwservices.MainActivity.mIntentFilter;

public class FirstActivity extends Activity {

    private BroadcastReceiver mReciver;
    private TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        textView1 = findViewById(R.id.textView1Act1);
        mReciver = new BroadcastReceiver() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onReceive(Context context, Intent intent) {
                textView1.setText("Получено сообщение для формы 1 по broadcast: " +
                        intent.getStringExtra(MY_FILTER));
            }
        };
        registerBroadcastReceiver();

    }
    // регистрируем широковещательный приёмник
    public void registerBroadcastReceiver() {
        this.registerReceiver(mReciver, mIntentFilter);
        textView1.setText("приемник включен");
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
}
