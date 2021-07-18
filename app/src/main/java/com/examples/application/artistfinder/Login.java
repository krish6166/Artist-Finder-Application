package com.examples.application.artistfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText mEmail , mPassword;
    Button mCreateBtn,mNewAccount;
    TextView forgotTextLink;
    ProgressBar progressBar;
    FirebaseAuth fAuth1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mEmail = findViewById(R.id.email2);
        mPassword = findViewById(R.id.password2);
        mCreateBtn = findViewById(R.id.Login3);
        progressBar = findViewById(R.id.progressBar5);
        mNewAccount = findViewById(R.id.Login5);

        fAuth1 = FirebaseAuth.getInstance();
        forgotTextLink = findViewById(R.id.forgotpassword);

        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmail.getText().toString().trim();
                String Password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required.");
                    return;
                }
                if(TextUtils.isEmpty(Password)){
                    mPassword.setError("Password is Required");
                    return;
                }
                if(Password.length() < 6){
                    mPassword.setError("Password Must Be greater than 6 Characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth1.signInWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Login.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else{
                            Toast.makeText(Login.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,Register.class);
                startActivity(i);
            }
        });


        forgotTextLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText resetmail = new EditText(v.getContext());
                AlertDialog.Builder passwordRestDialog = new AlertDialog.Builder(v.getContext());
                passwordRestDialog.setTitle("Reset Password ?");
                passwordRestDialog.setMessage("Enter Your Email To receive reset link");
                passwordRestDialog.setView(resetmail);

                passwordRestDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //extract the mail and send the link
                        String mail = resetmail.getText().toString();
                        fAuth1.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Login.this, "Reset link is send To your mail", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this, "Error ! Reset link is not sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
                passwordRestDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //close the dialog
                    }
                });

                passwordRestDialog.create().show();
            }
        });

    }
}
