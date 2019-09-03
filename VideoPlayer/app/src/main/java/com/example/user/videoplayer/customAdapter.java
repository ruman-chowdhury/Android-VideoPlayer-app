package com.example.user.videoplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.provider.MediaStore.Video.Thumbnails; //needed to import
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by USER-PC on 5/11/2018.
 */

public class customAdapter extends BaseAdapter{

    Context context;
    ArrayList<File> customList;
    int layout;

    private LayoutInflater inflater; //to view a xml file

    public customAdapter(Context context,int layout,ArrayList<File> customList){

        this.context = context;
        this.layout = layout;
        this.customList = customList;

//         this.customImage = customImage;
//        super(context,layout,customList);

    }



    @Override
    public int getCount() {
        return customList.size(); //if it would string then,List.length
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        if(convertView == null){

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.video_list,viewGroup,false); //we need to pass viewgroup
        }

        ImageView imageView =(ImageView) convertView.findViewById(R.id.idCustomImageView);
        TextView textView =(TextView) convertView.findViewById(R.id.idCustomTextView); //you may do type cating

       // imageView.setImageResource(customImage[i]);
        textView.setText(customList.get(i).getName().toString()); //getting name

        Bitmap bmThumbnail;
        String filepath = customList.get(i).toString();
        bmThumbnail = ThumbnailUtils.createVideoThumbnail(filepath,Thumbnails.MINI_KIND);
        imageView.setImageBitmap(bmThumbnail);

        return convertView;
    }
}
