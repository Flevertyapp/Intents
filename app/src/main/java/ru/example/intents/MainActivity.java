package ru.example.intents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity implements Constants {

    private EditText txtName;
    private Account account;
    private static final int REQUEST_CODE_SETTINGS_ACTIVITY = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        account = new Account();
        initView();
    }

    private void initView() {
        Button btnGreeting = findViewById(R.id.btnGreetings);
        //final EditText txtName = findViewById(R.id.textName);
        txtName = findViewById(R.id.textName);
        final TextView txtGreetings = findViewById(R.id.textHello);

        btnGreeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtName.getText().toString();
                String sayHello = getString(R.string.say_hello) + name;
                txtGreetings.setText(sayHello);
            }
        });

        Button btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //запуск второй активити через явный интент
                Intent runSetting = new Intent(MainActivity.this, SettingsActivity.class);
                populateAccount();
                //передача данных через интент
                //runSetting.putExtra(YOUR_NAME, txtName.getText().toString());
                runSetting.putExtra(YOUR_ACCOUNT, account);
                //старт активити, указанной в интенте

                //startActivity(runSetting);
                startActivityForResult(runSetting,REQUEST_CODE_SETTINGS_ACTIVITY);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode!=REQUEST_CODE_SETTINGS_ACTIVITY) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (resultCode==RESULT_OK){
            account=data.getParcelableExtra(YOUR_ACCOUNT);
            populateView();
        }
    }

    private void populateView() {
        txtName.setText(account.getName());
    }

    private void populateAccount() {
        account.setName(txtName.getText().toString());
    }
}