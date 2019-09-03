package com.example.user.videoplayer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.VideoView;
import android.widget.MediaController; //need to import mediaController class
import java.io.File;
import java.util.ArrayList;


public class Player extends AppCompatActivity implements View.OnClickListener{

    ArrayList<File> mySongs;
    int position;
    Uri uri;

   static VideoView videoView;

    ImageButton btnImg;
    Button btnPrev,btnNext;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        videoView = (VideoView) findViewById(R.id.idVideoViewPlay);

        btnImg = (ImageButton) findViewById(R.id.buttonPlayPause);
        btnPrev = (Button) findViewById(R.id.buttonPrev);
        btnNext = (Button) findViewById(R.id.buttonNext);

       // seekBar = (SeekBar) findViewById(R.id.seekBar);

        btnImg.setOnClickListener(this);
        btnPrev.setOnClickListener(this);
        btnNext.setOnClickListener(this);








//        //Creating MediaController
//        MediaController mc= new MediaController(this);
//        mc.setAnchorView(videoView);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        mySongs =(ArrayList) b.getParcelableArrayList("playList");
        position = b.getInt("pos",0);

        uri = Uri.parse(mySongs.get(position).toString());
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();




//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
//
//                videoView.seekTo(seekBar.getProgress());
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//                if(!videoView.isPlaying()){
//                    videoView.start();
//                }
//                videoView.seekTo(seekBar.getProgress());
//            }
//        });
//

    } //end onCreate

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id){

            case R.id.buttonPlayPause:
                if(videoView.isPlaying()){

                    videoView.pause();
                    btnImg.setImageResource(R.drawable.pause);
                }
                else {
                    videoView.start();
                    btnImg.setImageResource(R.drawable.play);
                }
                break;

            case R.id.buttonPrev:

                videoView.stopPlayback();

                position =(position-1<0)? mySongs.size()-1 : position-1;
                uri = Uri.parse(mySongs.get(position).toString());
                videoView.setVideoURI(uri);
                videoView.start();
              //  seekBar.setMax(videoView.getDuration());
                break;


            case R.id.buttonNext:
                videoView.stopPlayback();

                position =(position+1)% mySongs.size();
                uri = Uri.parse(mySongs.get(position).toString());
                videoView.setVideoURI(uri);
                videoView.start();
               // seekBar.setMax(videoView.getDuration());
                break;




        }
    }
}
