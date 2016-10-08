package app.com.thetechnocafe.notificationshowcase;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private Button mSendNotificationButton;
    private Spinner mTimeIntervalSpinner;
    private RadioGroup mNotificationTypeRadioGroup;

    private int mTimeInterval = 0;
    private ArrayList<String> mTimeIntervalList = new ArrayList<>(Arrays.asList(new String[]{"5", "10", "15", "20", "25", "30"}));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSendNotificationButton = (Button) findViewById(R.id.sendNotificationButton);
        mTimeIntervalSpinner = (Spinner) findViewById(R.id.timeIntervalSpinner);
        mNotificationTypeRadioGroup = (RadioGroup) findViewById(R.id.notificationTypeRadioGroup);

        //Send notification
        mSendNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mNotificationTypeRadioGroup.getCheckedRadioButtonId()) {
                    case R.id.simpleNotificationRadioButton: {
                        scheduleNotification(getSimpleNotification(), mTimeInterval);
                        break;
                    }
                    case R.id.bigPictureNotificationRadioButton: {
                        scheduleNotification(getBigPictureNotification(), mTimeInterval);
                        break;
                    }
                }
            }
        });

        setUpSpinner();
        setUpRadioGroup();
    }

    //Set up spinner :
    private void setUpSpinner() {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, mTimeIntervalList);
        mTimeIntervalSpinner.setAdapter(adapter);

        //Change time interval
        mTimeIntervalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Select the time interval
                mTimeInterval = Integer.parseInt(mTimeIntervalList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Set to 0 is noting selected
                mTimeInterval = 0;
            }
        });
    }

    //Set up radio group
    private void setUpRadioGroup() {
        //Check initial button
        mNotificationTypeRadioGroup.check(R.id.simpleNotificationRadioButton);

        mNotificationTypeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });
    }

    //Schedule the notification : Create a pending intent(from a intent that activates the broadcast receiver) and set an alarm via alarm manager
    private void scheduleNotification(Notification notification, int interval) {
        Intent intent = new Intent(this, NotificationPublisher.class);

        //Add notification and id to the intent
        intent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        intent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);

        //Set up pending intent
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        //Get alarm manager
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        long wakeupAlarmTime = SystemClock.elapsedRealtime() + (interval * 1000);

        //Set alarm
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, wakeupAlarmTime, pendingIntent);
    }

    /**
     * Different Types of Notifications
     * - Simple Notification
     * - Big Picture Notification
     */

    private Notification getSimpleNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Simple Notification")
                .setContentTitle("This is a simple notification from Notification Showcase")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setVibrate(new long[]{250, 500, 250, 500})
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        return builder.build();
    }

    private Notification getBigPictureNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Simple Notification")
                .setContentTitle("This is a big picture notification from Notification Showcase")
                .setVibrate(new long[]{250, 500, 250, 500})
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        //Set big picture style
        android.support.v4.app.NotificationCompat.BigPictureStyle style = new android.support.v4.app.NotificationCompat.BigPictureStyle();
        style.bigPicture(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        builder.setStyle(style);

        return builder.build();
    }
}