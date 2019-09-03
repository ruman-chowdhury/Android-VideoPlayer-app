package com.example.user.videoplayer;


import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    String[] items;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //all mp4 files in the mySongs var
        final ArrayList<File> mySongs = findSongs(Environment.getExternalStorageDirectory());



        //to make a list taking all the songs name
        items = new String[mySongs.size()];

        for(int i=0;i<mySongs.size();i++){

           // customToast(mySongs.get(i).getName().toString());
            items[i] = mySongs.get(i).getName().toString(); //we are getting name

        }



      //  ArrayAdapter<String> adp = new ArrayAdapter<String>(getApplicationContext(),R.layout.video_list,R.id.idCustomTextView,items);

     // int[] img =new int[]{R.id.idCustomImageView};

      customAdapter adp = new customAdapter(getBaseContext(),R.layout.video_list,mySongs);
        listView = (ListView) findViewById(R.id.idListView);
        listView.setAdapter(adp);



       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                startActivity(new Intent(getApplicationContext(),Player.class).putExtra("pos",i).putExtra("playList",mySongs));
                //Log.d("test","clicked");
            }
        });

    }

    public ArrayList<File> findSongs(File root){

        ArrayList<File> al = new ArrayList<>(); //Arraylist is return type. create obj al

        File[] files = root.listFiles(); //all files including txt,mp3,mp4,jpg.but we need only video

        for(File singleFile : files){

            if(singleFile.isDirectory() && !singleFile.isHidden()){

                al.addAll(findSongs(singleFile)); //recursive call for sub directory.that means directory inside a directory

            }
            else{

                if(singleFile.getName().endsWith(".mp4") || singleFile.getName().endsWith(".3gp") || singleFile.getName().endsWith(".wmv") || singleFile.getName().endsWith(".avi") || singleFile.getName().endsWith(".flv")){

                    al.add(singleFile); //single file is file,not directory/folder

                }

            }


        }

        return al;
    }

//    //custom toast method to show name when song is added
//    public void customToast(String text){
//        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
//    }



}
