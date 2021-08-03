package com.myapplicationdev.android.p10_ndpsongs_clv;

import android.content.Context;
import android.media.Rating;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Song> songList;

    public CustomAdapter(Context context, int resource, ArrayList<Song> objects){
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        songList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvTitle = rowView.findViewById(R.id.textViewTitle);
        TextView tvDate = rowView.findViewById(R.id.textViewDate);
//        TextView tvStars = rowView.findViewById(R.id.textViewStars);
        TextView tvSingers = rowView.findViewById(R.id.textViewSinger);
        ImageView ivNew = rowView.findViewById(R.id.imageViewNew);
        RatingBar rtBarList = rowView.findViewById(R.id.ratingBarList);

        Song currentSong = songList.get(position);

        tvTitle.setText(currentSong.getTitle());
        tvDate.setText(String.valueOf(currentSong.getYearReleased()));

        if (currentSong.getYearReleased() >= 2019) {
            ivNew.setImageResource(R.drawable.newimg);
        } else {
            ivNew.setVisibility(View.INVISIBLE);
        }

//        if (currentSong.getStars() == 1){
//            String stars = "*";
//            tvStars.setText(stars);
//        } else if (currentSong.getStars() == 2){
//            String stars = "* *";
//            tvStars.setText(stars);
//        } else if (currentSong.getStars() == 3){
//            String stars = "* * *";
//            tvStars.setText(stars);
//        } else if (currentSong.getStars() == 4){
//            String stars = "* * * *";
//            tvStars.setText(stars);
//        } else if (currentSong.getStars() == 5){
//            String stars = "* * * * *";
//            tvStars.setText(stars);
//        }

        rtBarList.setClickable(false);
        rtBarList.setRating(currentSong.getStars());
        tvSingers.setText(currentSong.getSingers());
        return rowView;
    }
}
