package com.example.fcm;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    EditText titlee;
    EditText messagee;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titlee = findViewById(R.id.titleEditTxt);
        messagee = findViewById(R.id.messageEditTxt);
        send =findViewById(R.id.sendBtn);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  title = titlee.getText().toString().trim();
                String message = messagee.getText().toString().trim();
                if(!title.equals("") && !message.equals("")){
                    FcmSend.pushNotification(
                            MainActivity.this,
                            "fX0zCmesTOGWcZTO_8JWId:APA91bFRfr6sQ-cPm0U1BwZDDSt9Uo1bKYiv-KWmJEt-gi9lky9ZF0PPEzFkK3ErgDzi6yLb668qu4ErgkX-p0D_1jgooFm5jUXUmB6jUH0OMwpPC6Jke27dxhU1hwvzMBAork1ISzFp",
                            title,
                            message
                    );
                    titlee.setText("");
                    messagee.setText("");
                    Toast.makeText(MainActivity.this, "MESSAGE SENT", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }
}