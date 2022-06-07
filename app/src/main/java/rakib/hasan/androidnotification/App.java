package rakib.hasan.androidnotification;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    public static final String CHANNEL_ONE_ID = "channel_one_id", CHANNEL_TWO_ID = "channel_two_id";

    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel channelOne = new NotificationChannel(CHANNEL_ONE_ID, "Channel One", NotificationManager.IMPORTANCE_HIGH);
            channelOne.setDescription("This is channel one.");

            NotificationChannel channelTwo = new NotificationChannel(CHANNEL_TWO_ID, "Channel Two", NotificationManager.IMPORTANCE_DEFAULT);
            channelOne.setDescription("This is channel Two.");

            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            List<NotificationChannel> channels = new ArrayList<>();
            channels.add(channelOne);
            channels.add(channelTwo);

            //createNotificationChannels() - takes list of NotificationChannel as parameter. As we've multiple notification channel,
            //first we create a list of NotificationChannel and pass through createNotificationChannels() method
            manager.createNotificationChannels(channels);

        }

    }
}
