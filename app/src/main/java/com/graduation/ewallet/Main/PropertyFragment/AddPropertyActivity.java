package com.graduation.ewallet.Main.PropertyFragment;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.graduation.ewallet.R;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;

public class AddPropertyActivity extends AppCompatActivity {

    static final int PICK_IMAGE_REQUEST = 1234;
    Bitmap bitmap;
    MultipartBody.Part multipartBody;
    boolean selected_imge = false;

    TextView tvAddImage;
    ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property);

        tvAddImage = findViewById(R.id.tvAddImage);
        ivImage = findViewById(R.id.ivImage);
        tvAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

    }

    void openGallery() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    String getRealPathFromUri(Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && null != data) {
            selected_imge = true;
            tvAddImage.setVisibility(View.GONE);
            File file = new File(getRealPathFromUri(data.getData()));
            final Uri imageUri = data.getData();
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            multipartBody = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
            RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
            Log.e("multipart", multipartBody.body() + "l");
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                ivImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
