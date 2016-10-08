package app.com.thetechnocafe.notificationshowcase;

import android.app.Notification;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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
    private ArrayList<String> mTimeIntervalList = new ArrayList<>(Arrays.asList(new String[]{"0", "5", "10", "15", "20", "25", "30"}));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSendNotificationButton = (Button) findViewById(R.id.sendNotificationButton);
        mTimeIntervalSpinner = (Spinner) findViewById(R.id.timeIntervalSpinner);
        mNotificationTypeRadioGroup = (RadioGroup) findViewById(R.id.notificationTypeRadioGroup);

        setUpSpinner();
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
        mNotificationTypeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });
    }

    private Notification getSimpleNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Simple Notification")
                .setContentTitle("This is a simple notification from Notification Showcase")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        return builder.build();
    }

    private Notification getBigPictureNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Simple Notification")
                .setContentTitle("This is a big picture notification from Notification Showcase")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        NotificationCompat.BigPictureStyle style = new android.support.v4.app.NotificationCompat.BigPictureStyle();
        style.bigPicture(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        builder.setStyle(style);

        return builder.build();
    }
}