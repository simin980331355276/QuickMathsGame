package com.example.quickmathsgame.AppUI;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quickmathsgame.Model.LeaderboardModel;
import com.example.quickmathsgame.R;

import java.util.List;

public class LeaderboardListAdapter extends ArrayAdapter<LeaderboardModel> {
    private static final String TAG= "LeaderboardListAdapter";

    private Context mContext;
    int mResource;

    public LeaderboardListAdapter(@NonNull Context context, int resource, @NonNull List<LeaderboardModel> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String username = getItem(position).getUsername();
        Integer score = getItem(position).getScore();

        LeaderboardModel model = new LeaderboardModel(username,score);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvusername = convertView.findViewById(R.id.ldrboardName);
        TextView tvscore = convertView.findViewById(R.id.ldrboardScore);

        tvusername.setText(username);
        tvscore.setText(String.valueOf(score));

        return convertView;
    }
}
