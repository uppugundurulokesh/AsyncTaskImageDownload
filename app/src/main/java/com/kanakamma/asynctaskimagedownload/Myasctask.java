package com.kanakamma.asynctaskimagedownload;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.Settings;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Kanakamma on 2/7/2018.
 */

public class Myasctask extends AsyncTask<String,Integer,Bitmap> {
    ProgressDialog progressDialog;
    MainActivity context;

    public Myasctask(MainActivity mainActivity) {
        context=mainActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog=new ProgressDialog(context);
        progressDialog.setTitle("don");
        progressDialog.setMessage("please wait...");
        progressDialog.setButton(ProgressDialog.BUTTON_NEGATIVE, "cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //progressDialog.dismiss();
                System.exit(0);
            }
        });
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }
    @Override
    protected Bitmap doInBackground(String... strings) {

        try {
            URL url=new URL("https://static.pexels.com/photos/64152/tiger-cub-tiger-cub-big-cat-64152.jpeg");
            HttpURLConnection c= (HttpURLConnection) url.openConnection();
            c.connect();

            InputStream inputStream=c.getInputStream();
            Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
            return  bitmap;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        progressDialog.dismiss();
       context.imageView.setImageBitmap(bitmap);
    }
}