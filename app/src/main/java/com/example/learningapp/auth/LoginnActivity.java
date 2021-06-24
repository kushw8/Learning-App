package com.example.learningapp.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.learningapp.MainActivity;
import com.example.learningapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginnActivity extends AppCompatActivity {


    EditText lEmail, lPassword;
    FirebaseFirestore lStore;
    FirebaseAuth lAuth;
    Button lLogin,lRegister, lForgotPassword;
    Boolean valid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginn);

        lEmail = findViewById(R.id.Login_Email);
        lPassword = findViewById(R.id.Login_Password);
        lLogin = findViewById(R.id.Login_BTN);
        lRegister = findViewById(R.id.Login_register);
        lForgotPassword = findViewById(R.id.Login_forgot_password);

        lStore = FirebaseFirestore.getInstance();
        lAuth = FirebaseAuth.getInstance();

    }




    private Boolean checkField(EditText TextField) {

        if(TextField.getText().toString().isEmpty()){
            TextField.setError("Enter Text");
            valid = false;
        }else{
            valid = true;
        }
        return valid;

    }

    public void MoveToRegister(View view) {
        startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
        finish();
    }

    public void MoveToHomePage() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    public void loginUser(View view) {

        lLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = lPassword.getText().toString().trim();
                String email = lEmail.getText().toString().trim();

                if(email.isEmpty()){

                    lEmail.setError("Email is required");
                    lEmail.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    lEmail.setError("Provide valid email");
                    lEmail.requestFocus();
                    return;
                }
                if(password.isEmpty()){

                    lPassword.setError("Password is required");
                    lPassword.requestFocus();
                    return;
                }
                if(password.length() < 6 ){
                    lPassword.setError("Minimum length should be 6 characters");
                    lPassword.requestFocus();
                    return;
                }

                lAuth.signInWithEmailAndPassword(lEmail.getText().toString(), lPassword.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(LoginnActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                MoveToHomePage();

                            }

                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("TAG", "Error Login " + e.getMessage());
                        Toast.makeText(LoginnActivity.this, "fail login", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });

    }

    public void forgotPassword(View view) {

        EditText editText =  new EditText(view.getContext());
        AlertDialog.Builder passwordRest = new AlertDialog.Builder(view.getContext());
        passwordRest.setTitle("Reset Password ");
        passwordRest.setMessage("Enter e-mail here:- ");
        passwordRest.setView(editText);

        passwordRest.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 String mail = editText.getText().toString();

                 lAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                     @Override
                     public void onSuccess(Void aVoid) {

                         Toast.makeText(LoginnActivity.this, "Email sent", Toast.LENGTH_SHORT).show();
                     }
                 }).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         Toast.makeText(LoginnActivity.this, "Email error", Toast.LENGTH_SHORT).show();
                         Log.d("Tag","Erroorrrrrr" + e.getMessage());
                     }
                 });
            }
        });

        passwordRest.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        passwordRest.create().show();



    }
}