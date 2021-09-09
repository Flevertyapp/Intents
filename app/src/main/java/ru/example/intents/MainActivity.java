package ru.example.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        Button btnGreeting = findViewById(R.id.btnGreetings);
        final EditText txtName = findViewById(R.id.textName);
        final TextView txtGreetings = findViewById(R.id.textHello);

        btnGreeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =txtName.getText().toString();
                String sayHello = getString(R.string.say_hello)+ name;
                txtGreetings.setText(sayHello);
            }
        });
    }
}