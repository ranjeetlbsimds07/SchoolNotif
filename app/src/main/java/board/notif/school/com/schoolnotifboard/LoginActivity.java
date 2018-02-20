package board.notif.school.com.schoolnotifboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSign = findViewById(R.id.btnSign);

        btnSign.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSign: {
                Intent intent = new Intent(this, NotificationListActivity.class);
                startActivity(intent);
            }
            break;
            default:
                break;
        }
    }
}
