package ia.shumilov.ru.hwservices;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import static ia.shumilov.ru.hwservices.MainActivity.MY_FILTER;
import static ia.shumilov.ru.hwservices.MainActivity.mIntentFilter;

public class FirstActivity extends Activity {

    private BroadcastReceiver mReceiver;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private List<TextView> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_first);


        textView1 = findViewById(R.id.textView1Act1);
        textView2 = findViewById(R.id.textView2Act1);
        textView3 = findViewById(R.id.textView3Act1);
        list = Arrays.asList(textView1, textView2, textView3);
        mReceiver = new BroadcastReceiver() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onReceive(Context context, Intent intent) {
                String text = "Получено сообщение для формы 1 по broadcast: " +
                        intent.getStringExtra(MY_FILTER);
                setTextAllTextView(text);
            }
        };
    }

    // регистрируем широковещательный приёмник
    public void registerBroadcastReceiver() {
        this.registerReceiver(mReceiver, mIntentFilter);
        setTextAllTextView("приемник включен");
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerBroadcastReceiver();
    }

    private void setTextAllTextView(String msg) {
        for (TextView view : list) {
            view.setText(msg);
        }

    }
}
