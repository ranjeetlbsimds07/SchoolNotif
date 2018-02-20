package board.notif.school.com.schoolnotifboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;

import board.notif.school.com.schoolnotifboard.adapter.NotificationListAdapter;

public class NotificationListActivity extends AppCompatActivity {
    private ListView lvNotification;
    private NotificationListAdapter notificationListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);
        lvNotification = findViewById(R.id.lvNotification);

        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.notification_header,lvNotification,false);
        lvNotification.addHeaderView(header);
        notificationListAdapter = new NotificationListAdapter(this);
        lvNotification.setAdapter(notificationListAdapter);
    }
}
