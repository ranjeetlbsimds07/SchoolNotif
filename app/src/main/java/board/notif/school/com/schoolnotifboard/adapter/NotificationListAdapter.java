package board.notif.school.com.schoolnotifboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import board.notif.school.com.schoolnotifboard.R;


/**
 * Created by Guest User on 2/19/2018.
 */

public class NotificationListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;

    public NotificationListAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private static class ViewHolderItem {
        public TextView txtNotification;
        /*public TextView tViewQuantityCount;
        public TextView tvRegNo;
        public TextView tvbatch;*/
    }
    @Override
    public View getView(int position, View v, ViewGroup parent) {
        ViewHolderItem holder;
        if (v == null) {
            holder = new ViewHolderItem();
            v = inflater.inflate(R.layout.notification_row_list, null);
            holder.txtNotification =v.findViewById(R.id.txtNotification);
            v.setTag(holder);
        } else {
            holder = (ViewHolderItem) v.getTag();
        }

        return v;
    }
}
