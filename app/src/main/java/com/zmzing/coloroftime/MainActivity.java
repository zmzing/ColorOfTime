package com.zmzing.coloroftime;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends ActionBarActivity {

    private LinearLayout ll_root;
    private TextView     tv_time, tv_color;

    private SimpleDateFormat sdf_time, sdf_color;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Date date = Calendar.getInstance().getTime();
                    String timeStr = sdf_time.format(date);
                    tv_time.setText(timeStr);
                    String colorStr = "#" + sdf_color.format(date);
                    tv_color.setText(colorStr);
                    ll_root.setBackgroundColor(Color.parseColor(colorStr));
                    handler.sendEmptyMessageDelayed(0, 1000);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        ll_root = (LinearLayout) findViewById(R.id.main_ll_root);
        tv_time = (TextView) findViewById(R.id.main_tv_time);
        tv_color = (TextView) findViewById(R.id.main_tv_color);

        sdf_time = new SimpleDateFormat();
        sdf_time.applyPattern("HH:mm:ss");
        sdf_color = new SimpleDateFormat();
        sdf_color.applyPattern("HHmmss");

        handler.sendEmptyMessage(0);
    }

}
