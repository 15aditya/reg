package com.example.aaditya.registration;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText Email,Password;
    public String EmailAdd, Pass;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        Email = (EditText)findViewById(R.id.email);
        Password = (EditText)findViewById(R.id.pass);

    }

    public void submit(View v){

        EmailAdd = Email.getText().toString().trim();
        Pass = Password.getText().toString().trim();

        progressDialog.setMessage("Please Wait.......");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(EmailAdd,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Registered",Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
                else{
                    Toast.makeText(MainActivity.this,"oops.......",Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            }
        });

    }
}
