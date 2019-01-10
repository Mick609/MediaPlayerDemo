package com.example.mick.mediaplayer;

import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private float speed;
    private float pitch;
    private TextView playingInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.test);
        playingInfo = (TextView) findViewById(R.id.playingInfoText);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_play:
                play();
                break;
            case R.id.button_pause:
                pause();
                break;
            default:
        }
    }

    public void play() {
        playingInfo.setText("Play");
        Log.i("TAG", "AudioPlayer.play");
        mediaPlayer.start();
    }

    public void pause() {
        playingInfo.setText("Pause");
        Log.i("TAG", "AudioPlayer.pause");
        mediaPlayer.pause();
    }

    public float getSpeed() {
        PlaybackParams mediaPlayerPlaybackParams = mediaPlayer.getPlaybackParams();
        speed = mediaPlayerPlaybackParams.getSpeed();
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
        PlaybackParams mediaPlayerPlaybackParams = mediaPlayer.getPlaybackParams();
        mediaPlayerPlaybackParams.setSpeed(this.speed);
        mediaPlayer.setPlaybackParams(mediaPlayerPlaybackParams);
    }

    public float getPitch() {
        PlaybackParams mediaPlayerPlaybackParams = mediaPlayer.getPlaybackParams();
        pitch = mediaPlayerPlaybackParams.getPitch();
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
        PlaybackParams mediaPlayerPlaybackParams = mediaPlayer.getPlaybackParams();
        mediaPlayerPlaybackParams.setPitch(this.pitch);
        mediaPlayer.setPlaybackParams(mediaPlayerPlaybackParams);
    }

    public String getDuration() {
        int mills = mediaPlayer.getDuration();
        int second = mills / 1000;
        int min = second / 60;
        int sec = second % 60;
        if (sec < 10) {
            return min + ":0" + sec;
        } else {
            return min + ":" + sec;
        }
    }

    public String getPosition() {
        long micro = mediaPlayer.getTimestamp().getAnchorMediaTimeUs();
        int mills = (int) micro / 1000;
        int second = mills / 1000;
        int min = second / 60;
        int sec = second % 60;

        if (sec < 10) {
            return min + ":0" + sec;
        } else {
            return min + ":" + sec;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        play();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }
}
