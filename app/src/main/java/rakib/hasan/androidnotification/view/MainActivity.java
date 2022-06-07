package rakib.hasan.androidnotification.view;

import static rakib.hasan.androidnotification.utils.ConstantValues.DEFAULT_CLICK_TIMEOUT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

import java.util.Objects;

import rakib.hasan.androidnotification.App;
import rakib.hasan.androidnotification.R;
import rakib.hasan.androidnotification.databinding.ActivityMainBinding;
import rakib.hasan.androidnotification.utils.ConstantValues;
import rakib.hasan.androidnotification.utils.NotificationBroadcastReceiver;

public class MainActivity extends AppCompatActivity {

    public static final String TITLE_KEY = "title_key", DESCRIPTION_KEY = "description_key";
    public static final int NOTIFICATION_ONE_ID = 1, NOTIFICATION_TWO_ID = 2;

    private ActivityMainBinding binding;
    private NotificationManager notificationManager;

    private String title, massage;
    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();

        binding.acMainNotificationOneBtn.setOnClickListener(v -> {
            //preventMultipleClicks();

            title = Objects.requireNonNull(binding.acMainNotificationTitleEt.getText()).toString();
            massage = Objects.requireNonNull(binding.acMainNotificationDescEt.getText()).toString();

            //notification on click works
            Intent activityIntent = new Intent(this, MainActivity.class);
            PendingIntent contentPendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ONE_ID, activityIntent,
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE : PendingIntent.FLAG_UPDATE_CURRENT);

            Intent actionActivityIntent = new Intent(this, NotificationActivity.class);
            actionActivityIntent.putExtra(TITLE_KEY, title).putExtra(DESCRIPTION_KEY, massage);
            PendingIntent actionPendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ONE_ID, actionActivityIntent,
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE : PendingIntent.FLAG_UPDATE_CURRENT);

            if (title != null && !title.isEmpty() && massage != null && !massage.isEmpty()){
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, App.CHANNEL_ONE_ID)
                        .setSmallIcon(R.drawable.ic_one)
                        .setContentTitle(title)
                        .setContentText(massage)
                        .setContentIntent(contentPendingIntent)
                        .addAction(R.mipmap.ic_launcher, "Action 1", actionPendingIntent)
                        .addAction(R.mipmap.ic_launcher, "Action 2", null)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .setColor(Color.BLUE)
                        .setLocalOnly(true)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setAutoCancel(true);
                notificationManager.notify(NOTIFICATION_ONE_ID, builder.build());
            }else {
                Toast.makeText(this, R.string.required_field, Toast.LENGTH_SHORT).show();
            }

        });

        binding.acMainNotificationTwoBtn.setOnClickListener(v -> {
            //preventMultipleClicks();

            title = Objects.requireNonNull(binding.acMainNotificationTitleEt.getText()).toString();
            massage = Objects.requireNonNull(binding.acMainNotificationDescEt.getText()).toString();

            Intent broadcastIntent = new Intent(this, NotificationBroadcastReceiver.class);
            broadcastIntent.putExtra(TITLE_KEY, title).putExtra(DESCRIPTION_KEY, massage);
            broadcastIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent broadcastPendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_TWO_ID, broadcastIntent,
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE : PendingIntent.FLAG_UPDATE_CURRENT);

            if (title != null && !title.isEmpty() && massage != null && !massage.isEmpty()){
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, App.CHANNEL_TWO_ID)
                        .setSmallIcon(R.drawable.ic_two)
                        .setContentTitle(title)
                        .setContentText(massage)
                        .setPriority(NotificationCompat.PRIORITY_LOW)
                        .addAction(R.mipmap.ic_launcher, "Show Broadcast", broadcastPendingIntent);
                notificationManager.notify(NOTIFICATION_TWO_ID, builder.build());
            }else {
                Toast.makeText(this, R.string.required_field, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void preventMultipleClicks() {
        if (SystemClock.elapsedRealtime() - mLastClickTime < DEFAULT_CLICK_TIMEOUT) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
    }

    private void initViews() {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

}