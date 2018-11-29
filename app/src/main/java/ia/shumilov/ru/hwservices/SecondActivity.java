package ia.shumilov.ru.hwservices;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SecondActivity extends Activity {
    private TextView textView1;
    private BindUtil bindUtil = new BindUtil();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button getDateFromBind = findViewById(R.id.getDateBind);
        Button clear = findViewById(R.id.clearBtnAct2);
        textView1 = findViewById(R.id.textView1Act2);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView1.setText("");
            }
        });
        getDateFromBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bindUtil.isBound()) {
                    String msg = bindUtil.getBindMess();
                    textView1.setText(msg);
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
        if(bindUtil.getConnection() == null){
            bindUtil.upConnect();
        }
    }
}