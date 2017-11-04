package com.example.allen.hp_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class MainActivity extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }

        /** Called when the user taps the Send button */
        /** This is only for test*/
        public void sendMessage(View view) {
            Intent intent = new Intent(this, DisplayMessageActivity.class);
            EditText editText = (EditText) findViewById(R.id.editText);
            String message = editText.getText().toString();
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }


         /** This is the function that link main activity and conversation page , can set get chapter later*/
        public void getChapter(View view) {
            Intent intent = new Intent(this, ConversationActivity.class);
            startActivity(intent);
    }
}