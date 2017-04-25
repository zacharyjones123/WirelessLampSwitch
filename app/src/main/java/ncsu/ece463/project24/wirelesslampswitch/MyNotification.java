package ncsu.ece463.project24.wirelesslampswitch;

/**
 * Created by Zachary Jones on 4/24/2017.
 */


import android.app.Notification;

import android.app.NotificationManager;

import android.app.PendingIntent;

import android.content.Context;

import android.content.Intent;

import android.net.Uri;

import android.os.Bundle;

import android.provider.Settings;

import android.util.Log;

import android.view.View;

import android.view.View.OnClickListener;

import android.widget.Button;

import android.widget.RemoteViews;



/**

 * @author little

 *

 */

public class MyNotification extends Notification {



    private Context ctx;

    private NotificationManager mNotificationManager;



    public MyNotification(Context ctx){

        super();

        this.ctx=ctx;

        String ns = Context.NOTIFICATION_SERVICE;

        mNotificationManager = (NotificationManager) ctx.getSystemService(ns);

        CharSequence tickerText = "Shortcuts";

        long when = System.currentTimeMillis();

        Notification.Builder builder = new Notification.Builder(ctx);

        Notification notification=builder.getNotification();

        notification.when=when;

        notification.tickerText=tickerText;

        notification.icon=R.mipmap.ic_launcher;



        RemoteViews contentView=new RemoteViews(ctx.getPackageName(), R.layout.notification_layout);



        //set the button listeners

        setListeners(contentView);



        notification.contentView = contentView;

        notification.flags |= Notification.FLAG_ONGOING_EVENT;

        CharSequence contentTitle = "From Shortcuts";

        mNotificationManager.notify(548853, notification);

    }

    public void setListeners(RemoteViews view){

        //Button btn = (Button) view.findViewById(R.id.button);
       // Button btn2 = (Button) MainActivity.findViewById(R.id.button2);

      //  btn.setOnClickListener(new View.OnClickListener() {
       //     @Override
      //      public void onClick(View v) {
       //         //Cut the lamp on
       //     }
      //  });

      //  btn2.setOnClickListener(new View.OnClickListener() {
      //      @Override
      //      public void onClick(View v) {
      //          //Cut the lamp off
      //      }
      //  });

        //TODO screencapture listener

        //adb shell /system/bin/screencap -p storage/sdcard0/SimpleAndroidTest/test.png

        Intent radio=new Intent(ctx, HelperActivity.class);

        radio.putExtra("DO", "radio");

        PendingIntent pRadio = PendingIntent.getActivity(ctx, 0, radio, 0);

        view.setOnClickPendingIntent(R.id.radio, pRadio);



        //TODO screen size listener

        Intent volume=new Intent(ctx, HelperActivity.class);

        volume.putExtra("DO", "volume");

        PendingIntent pVolume = PendingIntent.getActivity(ctx, 1, volume, 0);

        view.setOnClickPendingIntent(R.id.volume, pVolume);

    }



}