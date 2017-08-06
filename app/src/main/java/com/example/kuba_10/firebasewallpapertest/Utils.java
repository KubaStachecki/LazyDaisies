package com.example.kuba_10.firebasewallpapertest;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Kuba-10 on 14.07.2017.
 */

public class Utils {


    private Context context;
//    private PreferenceManager pref;



    public Utils(Context context) {
        this.context = context;
    }

    public int getScreenWidth() {

        int columnWidth;

        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        final Point point = new Point();
        try {
            display.getSize(point);
        } catch (java.lang.NoSuchMethodError ignore) {
            // Older device
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
        columnWidth = point.x;

        return columnWidth;
    }

    public File saveImageToSDCard(Bitmap bitmap, String imageName) {


        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                context.getString(R.string.gallery_name));
        if (!mediaStorageDir.exists()) {
            Log.d(MainActivity.TAAAAG, "directory was NOT present at check - created");
            if (!mediaStorageDir.mkdirs()) {
                Log.d(MainActivity.TAAAAG, "failed to create directory");}

        }

        mediaStorageDir.mkdirs();

        File file = new File(mediaStorageDir, imageName + ".jpg");

        if (file.exists())
            file.delete();
//            Toast.makeText(context, "plik istnieje", Toast.LENGTH_SHORT).show();
        Log.d(MainActivity.TAAAAG, "there was a file with this name - deleted and saved again" + file.getAbsolutePath());


        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();

            Log.d(MainActivity.TAAAAG, "Wallpaper saved to: " + file.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();

            Log.d(MainActivity.TAAAAG, "Wallpaper not saved" + e);


        }

        return file;
    }


    public void setAsWallpaper(Bitmap bitmap, String imagename) {

        Uri uri = Uri.fromFile(saveImageToSDCard(bitmap, imagename));

        Intent intent = new Intent(Intent.ACTION_ATTACH_DATA);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setDataAndType(uri, "image/jpeg");
        intent.putExtra("mimeType", "image/jpeg");
        context.startActivity(Intent.createChooser(intent, "Set as:"));


    }

    public void shareWallpaperUrl(Bitmap bitmap, String imagename){


        Uri uri = Uri.fromFile(saveImageToSDCard(bitmap, imagename));


        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpg");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        context.startActivity(Intent.createChooser(shareIntent, "Share image using"));



    }







}
