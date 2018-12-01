package ia.shumilov.ru.hwservices;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

import static ia.shumilov.ru.hwservices.MainActivity.MY_FILTER;

public class ServiceForActivity extends Service {
    public static final String MY_ACTION = "ia.shumilov.ru.my.action";
    private Intent mIntent = new Intent();
    private IBinder mBinder = new LocalBinder();
    private volatile AtomicInteger i = new AtomicInteger();

    public ServiceForActivity() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }

    class LocalBinder extends Binder{
        ServiceForActivity getService(){
            return ServiceForActivity.this;
        }
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (i.set(0); i.get() < 1_000_000; i.addAndGet(1)) {
                    sendBroadCast("message from broadcast number " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stopSelf();
            }
        }).start();
        return START_NOT_STICKY;
    }

    private void sendBroadCast(String mess) {
        mIntent.setAction(MY_ACTION);
        mIntent.putExtra(MY_FILTER, mess);
        mIntent.addFlags(Intent.FLAG_FROM_BACKGROUND);
        sendBroadcast(mIntent);
    }

    @SuppressLint("SimpleDateFormat")
    public String getMessage(){
        return new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").format(Calendar.getInstance().getTime());
    }
}
