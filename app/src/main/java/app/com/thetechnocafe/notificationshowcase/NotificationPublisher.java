package app.com.thetechnocafe.notificationshowcase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationPublisher extends BroadcastReceiver {
    public String NOTIFICATION_ID = "natification-id";
    public String NOTIFICATION = "notification";

    public NotificationPublisher() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

    }
}
