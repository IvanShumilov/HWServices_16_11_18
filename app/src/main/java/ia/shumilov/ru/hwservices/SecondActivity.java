package ia.shumilov.ru.hwservices;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;


public class SecondActivity extends Activity {
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private List<TextView> list;
    private BindUtil bindUtil = new BindUtil();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button getDateFromBind = findViewById(R.id.getDateBind);
        Button clear = findViewById(R.id.clearBtnAct2);
        textView1 = findViewById(R.id.textView1Act2);
        textView2 = findViewById(R.id.textView2Act2);
        textView3 = findViewById(R.id.textView3Act2);
        list = Arrays.asList(textView1, textView2, textView3);


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextAllTextView("");
            }
        });
        getDateFromBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bindUtil.isBound()) {
                    String msg = bindUtil.getBindMess();
                    setTextAllTextView(msg);
                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, ServiceForActivity.class);
        bindService(intent, bindUtil.getConnection(), Context.BIND_AUTO_CREATE);

    }


    @Override
    protected void onPause() {
        super.onPause();
        unbindService(bindUtil.getConnection());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (bindUtil.getConnection() == null) {
            bindUtil.upConnect();
        }
    }

    private void setTextAllTextView(String msg) {
        if (msg.equalsIgnoreCase("")) {
            for (TextView view : list) {
                view.setText(msg);
            }
        } else {
            for (TextView view : list) {
                String text = view.getText() + "\n" + msg;
                view.setText(text);
            }
        }

    }
}