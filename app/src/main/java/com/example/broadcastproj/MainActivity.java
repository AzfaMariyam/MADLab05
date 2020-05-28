package com.example.broadcastproj;

import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    String txtViewMsg = "Broadcast recievers";

    public static final String BROADCAST_ACTION = null;
    private Receiver localListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onStart() {
        super.onStart();
        localListener = new Receiver();
        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        this.registerReceiver(localListener);

    }


    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(localListener);
    }


    public void onClick(View view){
        if (view.getId() == R.id.button){

            BackgroundService.startAction(this.getApplicationContext());
        }
    }



    private void registerReceiver(Receiver localListener) {
    }


    public class Receiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String currentText = txtViewMsg.getText().toString();
            String message = intent.getStringExtra("value");
            currentText += "\nReceived " + message;
            txtViewMsg.setText(currentText);
        }
    }
}
