package com.example.parstagramjs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {

    public static final String TAG = "SignupActivity";
    private EditText etSignupEmail;
    private EditText etSignupUsername;
    private EditText etSignupPass1;
    private EditText etSignupPass2;
    private Button btnCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etSignupEmail = findViewById(R.id.etSignupEmail);
        etSignupUsername = findViewById(R.id.etSignupUsername);
        etSignupPass1 = findViewById(R.id.etSignupPass1);
        etSignupPass2 = findViewById(R.id.etSignupPass2);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick Create Account button");
                String email = etSignupEmail.getText().toString();
                String user = etSignupUsername.getText().toString();
                String pass1 = etSignupPass1.getText().toString();
                String pass2 = etSignupPass2.getText().toString();

                if (user.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (email.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pass1.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Please Enter a password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (pass2.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Please confirm your password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!(pass1.equals(pass2))) {
                    Toast.makeText(SignupActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                ParseUser parseuser = new ParseUser();

                parseuser.setUsername(user);
                parseuser.setEmail(email);
                parseuser.setPassword(pass1);

                parseuser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){
                            //Let the user use the app now
                            Log.i(TAG, "User was created!");
                            goMainActivity();
                        }
                        else {
                            //Sign up didn't succeed
                            Log.e(TAG, "User was not created!", e);
                            Toast.makeText(SignupActivity.this, "An account already exists with these credentials!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}