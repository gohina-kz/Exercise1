package ru.androidacademy.msk.exercise1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static final String KEY_TEXT = "KEY_TEXT";
    private TextView typedText;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final String message = getIntent().getStringExtra(KEY_TEXT);
        typedText = findViewById(R.id.typedText);
        typedText.setText(message);

        btn = findViewById(R.id.email_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uriText =
                        "mailto:zhumabek.gauhar@gmail.com" +
                                "?subject=" + Uri.encode("My first sent email:") +
                                "&body=" + Uri.encode(message);
                Uri uri = Uri.parse(uriText);
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(uri);
                startActivity(Intent.createChooser(intent, "Send email"));
            }
        });

    }

    public static void start(Activity activity, String message) {
        Intent intent = new Intent(activity, SecondActivity.class);
        intent.putExtra(KEY_TEXT, message);
        activity.startActivity(intent);
    }
}
