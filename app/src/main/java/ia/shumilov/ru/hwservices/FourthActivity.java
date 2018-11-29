package ia.shumilov.ru.hwservices;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FourthActivity extends AppCompatActivity {

    private BindUtil bindUtil = new BindUtil();
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        Button clearBtn = findViewById(R.id.clearBtnAct4);
        Button getDateFromBind = findViewById(R.id.getBindDataAct4);
        textView2 = findViewById(R.id.textView2Act4);
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2.setText("");
            }
        });

        getDateFromBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bindUtil.isBound()){
                    String msg = bindUtil.getBindMess();
                    textView2.setText(msg);
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
