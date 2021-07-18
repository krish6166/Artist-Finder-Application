package com.examples.application.artistfinder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import androidx.annotation.NonNull;
import android.webkit.MimeTypeMap;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;


public class UserProfile extends AppCompatActivity {

    TextInputEditText aName, aEmail, aArttype, aLocation, aPhone;
    String artistartworkUl,email, fullname, arttype, location, phone;
    Button submit, browse, upload;
    Uri imageUri;
    ImageView imageView;

    DatabaseReference root = FirebaseDatabase.getInstance().getReference("Location");
    StorageReference reference = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        imageView = findViewById(R.id.artist_artworkimg);
        submit = findViewById(R.id.artist_submit);
        browse = findViewById(R.id.artist_browse);
        upload = findViewById(R.id.artist_upload);
        aName = findViewById(R.id.artist_fname);
        aPhone = findViewById(R.id.artist_phone);
        aLocation = findViewById(R.id.artist_location);
        aArttype = findViewById(R.id.artist_art_type);
        aEmail = findViewById(R.id.artist_email);

        email = aName.getText().toString().trim();
        fullname = aEmail.getText().toString();
        arttype = aArttype.getText().toString();
        location = aLocation.getText().toString();
        phone = aPhone.getText().toString();

        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent , 2);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUri != null){
                    uploadToFirebase(imageUri);
                }else{
                    Toast.makeText(UserProfile.this, "Please Select Image", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode ==2 && resultCode == RESULT_OK && data != null){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }

    private void uploadToFirebase(Uri uri){

        final StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        artistartworkUl = uri.toString();
                        model addnewArtist = new model(arttype, email, fullname, location, artistartworkUl);
                        root.child(fullname).setValue(addnewArtist).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(UserProfile.this, "Uploaded Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                        Toast.makeText(UserProfile.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UserProfile.this, "Uploading Failed !!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private String getFileExtension(Uri mUri){

        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();

        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }

}