package ia.shumilov.ru.hwservices;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class FourthActivity extends AppCompatActivity {

    private BindUtil bindUtil = new BindUtil();
    private TextView textView2;
    private TextView textView1;
    private TextView textView3;
    private List<TextView> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        Button clearBtn = findViewById(R.id.clearBtnAct4);
        Button getDateFromBind = findViewById(R.id.getBindDataAct4);
        textView2 = findViewById(R.id.textView2Act4);
        textView1 = findViewById(R.id.textView1Act4);
        textView3 = findViewById(R.id.textView3Act4);
        list = Arrays.asList(textView1, textView2, textView3);
        clearBtn.setOnClickListener(new View.OnClickListener() {
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
