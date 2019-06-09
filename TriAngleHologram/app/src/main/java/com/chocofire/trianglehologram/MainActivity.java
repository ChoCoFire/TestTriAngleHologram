package com.chocofire.trianglehologram;

import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button_main_select;
    private TextureView textureView_top;
    private final int REQUEST_TAKE_GALLERY_VIDEO = 1001;
    private Surface mSurface;
    private Uri myUri;
    private Button button_main_play;
    private TextureView textureView_left;
    private TextureView textureView_right;
    private TextureView textureView_bottom;
    private MediaPlayer mediaPlayer1;
    private MediaPlayer mediaPlayer2;
    private MediaPlayer mediaPlayer3;
    private MediaPlayer mediaPlayer4;
    private Surface mSurface4;
    private Surface mSurface3;
    private Surface mSurface2;
    private Surface mSurface1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setView();

        setListener();

        init();
    }

    private void init() {
        mediaPlayer1 = new MediaPlayer();
        mediaPlayer2 = new MediaPlayer();
        mediaPlayer3 = new MediaPlayer();
        mediaPlayer4 = new MediaPlayer();
    }

    private void setListener() {
        button_main_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findVideo();
            }
        });

        button_main_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myUri==null){
                    Toast.makeText(MainActivity.this,"ผิดพลาดบางอย่าง",Toast.LENGTH_SHORT).show();
                }else{
                    loadMedia(myUri,mediaPlayer1,mSurface1);
                    loadMedia(myUri,mediaPlayer2,mSurface2);
                    loadMedia(myUri,mediaPlayer3,mSurface3);
                    loadMedia(myUri,mediaPlayer4,mSurface4);
                }
            }
        });

        textureView_top.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                mSurface1 = new Surface(surface);
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });
        textureView_left.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                mSurface2 = new Surface(surface);
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });
        textureView_right.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                mSurface3 = new Surface(surface);
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });
        textureView_bottom.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                mSurface4 = new Surface(surface);
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });
    }

    private void setView() {
        button_main_select = findViewById(R.id.button_main_select);
        button_main_play = findViewById(R.id.button_main_play);
        textureView_top = findViewById(R.id.textureView_top);
        textureView_left = findViewById(R.id.textureView_left);
        textureView_right = findViewById(R.id.textureView_right);
        textureView_bottom = findViewById(R.id.textureView_bottom);
    }

    private void findVideo(){
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Video"),REQUEST_TAKE_GALLERY_VIDEO);
    }

    private void loadMedia(Uri url , MediaPlayer mediaPlayer , Surface surface) {
        try {
            mediaPlayer.setSurface(surface);
            mediaPlayer.setDataSource(MainActivity.this, url);
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
                @Override
                public void onPrepared(MediaPlayer playerM){
                    playerM.start();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_TAKE_GALLERY_VIDEO){
            if (data != null) {
                myUri = data.getData();
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
