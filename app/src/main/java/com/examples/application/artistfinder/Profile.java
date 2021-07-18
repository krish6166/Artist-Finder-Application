package com.examples.application.artistfinder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;

public class Profile extends AppCompatActivity {

    ImageView img1;
    Button Browse, Upload;
    Uri filepath;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Going to Upload Images
        Button btn1 = findViewById(R.id.upload_images);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this,UploadArtwork.class);
                startActivity(intent);
            }
        });

        // Profile Image
        img1 = findViewById(R.id.profile_image);
        Browse = findViewById(R.id.browse);
        Upload = findViewById(R.id.upload);

        Browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withContext(Profile.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                Intent i = new Intent(Intent.ACTION_PICK);
                                i.setType("image/*");
                                startActivityForResult(i,101);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();

            }
        });
        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadtoFirebase();
            }
        });
    }

    private void uploadtoFirebase() {

        final ProgressDialog dialog = new ProgressDialog(Profile.this);
        dialog.setTitle("File Uploader");
        dialog.show();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference uploader = storage.getReference().child("Image1");
        uploader.putFile(filepath)
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                        float percent = (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                        dialog.setMessage("Uploaded" +"  " + (int)percent  + "%");

                    }
                })
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "File Uploaded", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode==101 && resultCode==RESULT_OK){

            filepath = data.getData();
            try{
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                img1.setImageBitmap(bitmap);
            }catch(Exception ex){

            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }


}
