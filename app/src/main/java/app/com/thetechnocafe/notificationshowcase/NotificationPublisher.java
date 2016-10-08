package app.com.thetechnocafe.notificationshowcase;

import android.app.Notification;
import android.app.NotificationManager;
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
        //Get notification manager
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //Get notification
        Notification notification = intent.getParcelableExtra(NOTIFICATION);

        //Get notification id
        int id = intent.getParcelableExtra(NOTIFICATION_ID);

        //Send the notification
        if(notification != null) {
            notificationManager.notify(id, notification);
        }
    }
}
