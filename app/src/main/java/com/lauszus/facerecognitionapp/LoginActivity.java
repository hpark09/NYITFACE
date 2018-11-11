package com.lauszus.facerecognitionapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @BindView(R.id.IDnumber) EditText _IDnumber;
    @BindView(R.id.btn_login) Button _loginButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(_IDnumber.getText().toString().equals("1234") ) {
                    Toast.makeText(getApplicationContext(),
                            "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, FaceRecognitionAppActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();

                    }
                }

        });
                }

            public void login() {
                Log.d(TAG, "Login");

                if (!validate()) {
                    onLoginFailed();
                    return;
                }

                _loginButton.setEnabled(false);

                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Authenticating...");
                progressDialog.show();

                String email = _IDnumber.getText().toString();



                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                // On complete call either onLoginSuccess or onLoginFailed
                                onLoginSuccess();
                                // onLoginFailed();
                                progressDialog.dismiss();
                            }
                        }, 3000);
            }


            public void onBackPressed() {
                // Disable going back to the MainActivity
                moveTaskToBack(true);
            }

            public void onLoginSuccess() {
                _loginButton.setEnabled(true);


            }

            public void onLoginFailed() {
                Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

                _loginButton.setEnabled(true);
            }

            public boolean validate() {
                boolean valid = true;

                String email = _IDnumber.getText().toString();

                if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    _IDnumber.setError("enter a valid ID number");
                    valid = false;
                } else {
                    _IDnumber.setError(null);
                }


                return valid;
            }
        }