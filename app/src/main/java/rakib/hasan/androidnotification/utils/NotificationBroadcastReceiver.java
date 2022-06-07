package rakib.hasan.androidnotification.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import rakib.hasan.androidnotification.view.MainActivity;

public class NotificationBroadcastReceiver extends BroadcastReceiver {

    private String title, description;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null){
            if(intent.hasExtra(MainActivity.TITLE_KEY) && intent.hasExtra(MainActivity.DESCRIPTION_KEY)){
                title = intent.getStringExtra(MainActivity.TITLE_KEY);
                description = intent.getStringExtra(MainActivity.DESCRIPTION_KEY);
                Toast.makeText(context, title + "\n" + description, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
