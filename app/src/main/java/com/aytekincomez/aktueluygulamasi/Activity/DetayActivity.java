package com.aytekincomez.aktueluygulamasi.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.aytekincomez.aktueluygulamasi.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DetayActivity extends AppCompatActivity {
    ImageView ivDetayResim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);
        ivDetayResim = (ImageView)findViewById(R.id.ivDetayResim);

        new ResimYukleyici().execute(getIntent().getStringExtra("resim"));
    }

    private class ResimYukleyici extends AsyncTask<String, Void, Bitmap>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(bitmap != null){
                ivDetayResim.setImageBitmap(bitmap);
            }else{
                Log.d("LOG","bitmap boş dönüyor.");
            }
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                return getImage(strings[0]);
            }catch (IOException e){

            }
            return null;
        }

        public Bitmap getImage(String url) throws IOException{
            URL baglanti = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)baglanti.openConnection();

            int uzunluk = connection.getContentLength();

            InputStream is = (InputStream) baglanti.getContent();
            byte[] imageData = new byte[uzunluk];
            int bufferSize = (int)Math.ceil(uzunluk/(double)100);
            int downloaded = 0;
            int read;

            while (downloaded < uzunluk){
                if(uzunluk < bufferSize){
                    read = is.read(imageData, downloaded, uzunluk);
                }else if((uzunluk - downloaded) <= bufferSize){
                    read = is.read(imageData, downloaded, uzunluk - downloaded);
                }else{
                    read = is.read(imageData, downloaded, bufferSize);
                }
                downloaded += read;
            }
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, uzunluk);
            if(bitmap != null){
                Log.d("LOG","Bitmap Olusutuldu.");
            }else{
                Log.d("LOG","Bitmap Olusuturulamadı.");
            }
            is.close();
            return bitmap;
        }
    }
}
