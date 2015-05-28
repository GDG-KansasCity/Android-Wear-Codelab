package com.jaywhitsitt.codelabandroidwear;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;


public class MainActivity extends ActionBarActivity {

    private EditText mCustomTitle, mCustomMessage; // Edit text boxes for the custom notification
    private RadioGroup mCustomIconGroup, showHideIconGroup; // Radiogroups with the Icon and settings for the custom notification
    private int mCustomIcon; // The variable that will hold the ID of the custom icon to show
    private boolean showIcon = false; // boolean that will tell if wear should show the app icon or not
    private String LOG_TAG = "WEAR"; // Our LogCat tag for debugging purposes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Custom Title and Message for custom Notification
        mCustomTitle = (EditText) findViewById(R.id.notificationTitle);
        mCustomMessage = (EditText) findViewById(R.id.notificationMessage);

        // RadioGroup for the customIcon
        mCustomIconGroup = (RadioGroup) findViewById(R.id.iconGroup);
        mCustomIconGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            // The name of the ICONS will change based on how you named it....
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (group.getCheckedRadioButtonId()) {
                    case R.id.icon1:
                        mCustomIcon = R.drawable.ic_wear_notification;
                        break;
                    case R.id.icon2:
                        mCustomIcon = R.drawable.ic_notification_2;
                        break;
                    case R.id.icon3:
                        mCustomIcon = R.drawable.ic_notification3;
                        break;
                }
            }
        });

        // RadioGroup to determine if App Icons should be shown or not.
        showHideIconGroup = (RadioGroup) findViewById(R.id.hideIconGroup);
        showHideIconGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (group.getCheckedRadioButtonId()) {
                    case R.id.showIcon:
                        showIcon = true;
                        break;
                    case R.id.hideIcon:
                        showIcon = false;
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Define the method to send the notifications with the same name from the Android onClick from the XML Layout
    public void sendNotification(View view) {
        // Common elements for all our notifications
        int notificationId = 001; // id- An identifier for this notification unique within your application.
        String eventTitle = "Sample Notification"; // Title for the notificaiton
        String eventText = "Text for the notification."; // Text for the notificaiton
        String intentExtra = "This is an extra String!"; // Extra String to be passed to a intent
        // A large String to be used by the BigStyle
        String eventDescription = "This is supposed to be a content that will not fit the normal content screen"
                + " usually a bigger text, by example a long text message or email.";

        // Build intent for notification content - This will take us to our MainActivity
        Intent viewIntent = new Intent(this, MainActivity.class);
        PendingIntent viewPendingIntent = PendingIntent.getActivity(this, 0, viewIntent, 0);

        // Specify the 'big view' content to display the long
        // event description that may not fit the normal content text.
        NotificationCompat.BigTextStyle bigStyle = new NotificationCompat.BigTextStyle();
        // We instantiate as null because they will be changing based on which button is pressed
        NotificationCompat.Builder mBuilder = null;
        Notification mNotification = null;

        // Get an instance of the NotificationManager service
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        //Now let's add a switch to catch the button that has been clicked
        // We also add a case for each of the buttons.
        switch(view.getId()) {
            case R.id.simpleNotification:
                mBuilder = new NotificationCompat.Builder(this) // Instantiate the builder with our context.
                        .setSmallIcon(R.drawable.ic_wear_notification) // set the icon
                        .setContentTitle(eventTitle) // set the title
                        .setContentText(eventText) // set the text
                        .setAutoCancel(true)  // This flag makes the notification disappear when the user clicks on it!
                        .setContentIntent(viewPendingIntent); // and finally the intent to be used
                break;

            case R.id.bigNotification:
                bigStyle.bigText(eventDescription); // bigText will override setContentText
                //bigStyle.setBigContentTitle("Override Title"); // bigContentTitle Override the contentTitle
                Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.face);
                mBuilder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_wear_notification)
                        .setLargeIcon(background)
                        .setContentTitle(eventTitle) // This is unnecessary for the big notification if you use bigText
                        .setContentText(eventText) // Unnecessary if setBigContentTitle is Overriden
                        .setContentIntent(viewPendingIntent)
                        .setAutoCancel(true)
                        .setStyle(bigStyle);
                break;

            case R.id.bigNotificationWithAction:
                break;

            case R.id.sendCustomNotification:
                break;
        }

        // This code goes after the switch because is common to all our notifications.
        // Build the notification and issues it with notification manager.
        notificationManager.notify(notificationId, mBuilder.build());
        Log.d(LOG_TAG, "Normal Notification");
    }
}
