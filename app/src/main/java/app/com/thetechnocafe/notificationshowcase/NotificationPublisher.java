package app.com.thetechnocafe.notificationshowcase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationPublisher extends BroadcastReceiver {
    public static final String NOTIFICATION_ID = "natification-id";
    public static final String NOTIFICATION = "notification";

    public NotificationPublisher() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

    }
}
