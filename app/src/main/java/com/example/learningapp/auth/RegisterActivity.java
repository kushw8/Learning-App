package com.example.learningapp.auth;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.learningapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.ContentValues.TAG;

public class RegisterActivity extends AppCompatActivity {

    EditText mFirstName;
    EditText mSecondName;
    EditText mPhone;
    EditText mEmail;
    EditText mPassword;
    FirebaseFirestore db;
    DatabaseReference reference;

    private FirebaseAuth mAuth;
    CircleImageView imageView;
    static int RequestCode = 1;
    Uri imageurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirstName = findViewById(R.id.register_firstName);
        mSecondName = findViewById(R.id.register_secondName);
        mPhone = findViewById(R.id.register_phone);
        mEmail = findViewById(R.id.register_Email);
        mPassword = findViewById(R.id.register_password);
        imageView = findViewById(R.id.register_image);
        reference = FirebaseDatabase.getInstance().getReference().child("Score");

        db = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallary();
            }
        });
    }


    ActivityResultLauncher<Intent> mGetContent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();

                        imageurl = data.getData();
                        // imageList.add(imageurl);
                        imageView.setImageURI(imageurl);


                    }else {
                        Log.d("TAG","fail. ........................................");
                    }
                }
            });


    public void openGallary() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mGetContent.launch(intent);
    }




    public void registerUser(View view) {

        String password = mPassword.getText().toString().trim();
        String email = mEmail.getText().toString().trim();
        String phoneNumber = mPhone.getText().toString().trim();
        String firstName = mFirstName.getText().toString().trim();
        String secondName = mSecondName.getText().toString().trim();
        String image = imageurl.toString();

        if(firstName.isEmpty()){
            mFirstName.setError("First name is required");
            mFirstName.requestFocus();
            return;
        }
        if(secondName.isEmpty()){
            mSecondName.setError("Second name is required");
            mSecondName.requestFocus();
            return;
        }
        if(phoneNumber.isEmpty()){

            mPhone.setError("Number is required");
            mPhone.requestFocus();
            return;
        }
        if(email.isEmpty()){

            mEmail.setError("Email is required");
            mEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("Provide valid email");
            mEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){

            mPassword.setError("Password is required");
            mPassword.requestFocus();
            return;
        }
        if(password.length() < 6 ){
            mPassword.setError("Minimum length should be 6 characters");
            mPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(RegisterActivity.this, "Register Successful.",
                                    Toast.LENGTH_SHORT).show();

                            Map<String, Object> userInfo = new HashMap<>();
                            userInfo.put("first", firstName);
                            userInfo.put("last", secondName);
                            userInfo.put("phone", phoneNumber);
                            userInfo.put("email", email);
                            userInfo.put("password", password);
                            userInfo.put("UserId", currentUser);
                            userInfo.put("ImageUri",image);
                            DocumentReference documentReferences = db.collection("users").document(currentUser);
                            documentReferences.set(userInfo);

                            HashMap map = new HashMap();
                            map.put("name",firstName);
                            map.put("score",0);

                            reference.child(currentUser).setValue(map);

                       //    updateUI(firstName,imageurl,user);

                            startActivity(new Intent(getApplicationContext(), LoginnActivity.class));
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }

//    private void updateUI(String firstName, Uri uri, FirebaseUser user) {
//        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("user_image");
//        final StorageReference imageFilePath = storageReference.child(uri.getLastPathSegment());
//        imageFilePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                    @Override
//                    public void onSuccess(Uri uri) {
//
//                        UserProfileChangeRequest changeRequest = new UserProfileChangeRequest.Builder()
//                                .setDisplayName(firstName).setPhotoUri(uri).build();
//                        user.updateProfile(changeRequest);
//
//                    }
//                });
//
//            }
//        });
//    }


    public void MoveToLogin(View view) {
        startActivity(new Intent(getApplicationContext(),LoginnActivity.class));
        finish();
    }
}