package ia.shumilov.ru.hwservices;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

class BindUtil {
    private ServiceForActivity mService;
    boolean mBound = false;
    private ServiceConnection mConnection;

    ServiceConnection getConnection() {
        return mConnection;
    }

    boolean isBound() {
        return mBound;
    }

    public BindUtil() {
        upConnect();
    }

    void upConnect() {
        mConnection = new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName className,
                                           IBinder service) {
                // We've bound to ServiceForActivity, cast the IBinder and get ServiceForActivity instance
                ServiceForActivity.LocalBinder binder = (ServiceForActivity.LocalBinder) service;
                mService = binder.getService();
                mBound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName arg0) {
                mBound = false;
            }
        };
    }

    String getBindMess() {
        if (mBound) {
            return mService.getMessage();
        }
        return "Error in work";
    }
}
