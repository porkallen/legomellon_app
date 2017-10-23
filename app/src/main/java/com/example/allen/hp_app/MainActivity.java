package com.example.allen.hp_app;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.TextView.BufferType;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation_main);
        // Get the widgets reference from XML layout
        final EditText dumpText = (EditText) findViewById(R.id.input_text);
        /*
            This will provide Scroll option for EditText,
            but it will not show the scroll bar on EditText
        */
        dumpText.setText("Test 1",TextView.BufferType.EDITABLE);
        dumpText.setScroller(new Scroller(getApplicationContext()));
        dumpText.setVerticalScrollBarEnabled(true);
        /*
            setMinLines (int minlines)
                Makes the TextView at least this many lines tall.
                Setting this value overrides any other (minimum) height
                setting. A single line TextView will set this value to 1.
         */
        // Set the minimum lines to display on EditText
        dumpText.setMinLines(2);

    }
}
