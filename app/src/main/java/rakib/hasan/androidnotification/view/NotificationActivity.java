package rakib.hasan.androidnotification.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import rakib.hasan.androidnotification.R;
import rakib.hasan.androidnotification.databinding.ActivityNotificationBinding;

public class NotificationActivity extends AppCompatActivity {

    private ActivityNotificationBinding binding;
    private String title, massage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getIntent().hasExtra(MainActivity.TITLE_KEY) && getIntent().hasExtra(MainActivity.DESCRIPTION_KEY)){
            title = getIntent().getStringExtra(MainActivity.TITLE_KEY);
            massage = getIntent().getStringExtra(MainActivity.DESCRIPTION_KEY);
            binding.acNotificationTitleTv.setText(title);
            binding.acNotificationDescriptionTv.setText(massage);
        }
    }
}