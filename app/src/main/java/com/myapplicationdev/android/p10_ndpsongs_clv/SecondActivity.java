package com.myapplicationdev.android.p10_ndpsongs_clv;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

	ListView lv;
    ArrayList<Song> songList;
    CustomAdapter adapter;
	String moduleCode;
	int requestCode = 9;
    Button btn5Stars;
    Spinner spnYear;
    ArrayList<String> spnAl;
    ArrayAdapter<String> spnAa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

        setTitle(getTitle().toString() + " ~ " +  getResources().getText(R.string.title_activity_second));

		lv = (ListView) this.findViewById(R.id.lv);
        btn5Stars = (Button) this.findViewById(R.id.btnShow5Stars);
        spnYear = (Spinner) this.findViewById(R.id.spnYear);

		DBHelper dbh = new DBHelper(this);
        songList = dbh.getAllSongs();
        dbh.close();

		adapter = new CustomAdapter(this, R.layout.row, songList);
		lv.setAdapter(adapter);

        String [] array = getResources().getStringArray(R.array.spinnerYear);
        spnAl = new ArrayList<String>();
        spnAa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, spnAl);
        spnYear.setAdapter(spnAa);
        spnAa.add("Filter Years");
        for (int i = 0; i < songList.size(); i++) {
            spnAa.add(String.valueOf(songList.get(i).getYearReleased()));
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                i.putExtra("song", songList.get(position));
                startActivityForResult(i, requestCode);
            }
        });

        btn5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SecondActivity.this);
                songList.clear();
                songList.addAll(dbh.getAllSongsByStars(5));
                adapter.notifyDataSetChanged();
            }
        });

        spnYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<Song> alFilt;
                alFilt = new ArrayList<Song>();
                int yearFind = 0;

                if(spnYear.getSelectedItemPosition() == 0) {
                    lv.setAdapter(adapter);
                } else {
                    yearFind = Integer.parseInt(spnYear.getSelectedItem().toString());
                    for (int i = 0; i < songList.size(); i++) {
                        if (songList.get(i).getYearReleased() == yearFind) {
                            alFilt.add(songList.get(i));
                        }
                    }
                    CustomAdapter aaFilt = new CustomAdapter(SecondActivity.this, R.layout.row, alFilt);
                    lv.setAdapter(aaFilt);


                    aaFilt.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

    }


//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		if(requestCode == this.requestCode && resultCode == RESULT_OK){
//			DBHelper dbh = new DBHelper(this);
//            songList.clear();
//            songList.addAll(dbh.getAllSongs());
//            dbh.close();
//            adapter.notifyDataSetChanged();
//		}
//		super.onActivityResult(requestCode, resultCode, data);
//	}

}
