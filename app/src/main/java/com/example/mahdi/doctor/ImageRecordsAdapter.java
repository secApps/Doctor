package com.example.mahdi.doctor;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by mahdi on 1/31/2015.
 */
public class ImageRecordsAdapter extends ArrayAdapter<ImageRecord>{
    private ImageLoader mImageLoader;
    private Activity activity;
    public ImageRecordsAdapter(FragmentActivity context) {

        super(context, R.layout.image_list_item);
        this.activity=context;
        mImageLoader = new ImageLoader(ImageApplication.getInstance().getRequestQueue(), new BitmapLruCache());
    }

    public void swapImageRecords(List<ImageRecord> objects) {
        clear();

        for(ImageRecord object : objects) {
            add(object);
        }

        notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.image_list_item, parent, false);
        }

        // NOTE: You would normally use the ViewHolder pattern here
        NetworkImageView imageView = (NetworkImageView) convertView.findViewById(R.id.image1);
        TextView textView = (TextView) convertView.findViewById(R.id.text1);

        final ImageRecord imageRecord = getItem(position);


        imageView.setImageUrl(imageRecord.getUrl(), mImageLoader);
        textView.setText(imageRecord.getTitle());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ImageRecord imageRecord1 = getItem(position);
                Intent i = new Intent(activity, DoctorsInfo.class);
                //i.putExtra("title",  imageRecord1.getTitle());
                activity.startActivity(i);
            }
        });

        return convertView;
    }
}
