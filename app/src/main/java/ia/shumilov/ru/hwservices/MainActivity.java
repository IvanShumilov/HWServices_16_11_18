package ia.shumilov.ru.hwservices;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static ia.shumilov.ru.hwservices.ServiceForActivity.MY_ACTION;

public class MainActivity extends AppCompatActivity {

    private Button mStartServiceBtn;
    private Button mNextActivityBtn;
    private Button mFirstActBtn;
    private Button mThridActBtn;
    private TextView mTextView1;
    private Button mFourthActBtn;
    private BroadcastReceiver mReciverBroad;
    public static final IntentFilter mIntentFilter = new IntentFilter(MY_ACTION);
    public static final String MY_FILTER = "ru.shumilov.broadcast.Message.Activity_1_3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStartServiceBtn = findViewById(R.id.startSrvBtn);
        mNextActivityBtn = findViewById(R.id.secondActBtn);
        mTextView1 = findViewById(R.id.mainActTextView);
        mFirstActBtn = findViewById(R.id.firstActBtn);
        mThridActBtn = findViewById(R.id.thirdActBtn);
        mFourthActBtn = findViewById(R.id.fourthActBtn);

        mReciverBroad = new BroadcastReceiver() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onReceive(Context context, Intent intent) {
                mTextView1.setText("Получено сообщение для формы 1 и 3: " +
                        intent.getStringExtra(MY_FILTER));
            }
        };

        mNextActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        mStartServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStartServiceBtn.setEnabled(false);
                startService(new Intent(getApplicationContext(), ServiceForActivity.class));
            }
        });

        mFirstActBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(intent);
            }
        });

        mThridActBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThrirdActivity.class);
                startActivity(intent);
            }
        });
        mFourthActBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FourthActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    // регистрируем широковещательный приёмник
    public void registerBroadcastReceiver() {
        this.registerReceiver(mReciverBroad, mIntentFilter);
        mTextView1.setText("приемник включен");
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReciverBroad);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerBroadcastReceiver();
    }
}
