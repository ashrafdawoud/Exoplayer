package com.example.exoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoRendererEventListener;

import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;


public class MainActivity extends AppCompatActivity {
    SimpleExoPlayer mExoPlayer;
    private boolean mPlayWhenReady = true;
    private long mCurrentPosition = 0;
    String downloadUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);


      /*  SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(this);
        PlayerView playerView=findViewById (R.id.exo);
        playerView.setPlayer(player);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory (this,
                Util.getUserAgent(this, "yourApplicationName"));
// This is the MediaSource representing the media to be played.
         Uri mp4VideoUri =Uri.parse("https://ia801309.us.archive.org/27/items/hb2015-11-06/hb2015-11-0616bitmatrix_t05.flac"); //random 720p source
        MediaSource videoSource = new ProgressiveMediaSource .Factory(dataSourceFactory)
                .createMediaSource(mp4VideoUri);
// Prepare the player with the source.
        player.prepare(videoSource);*/
        // Uri mp4VideoUri =Uri.parse("http://81.7.13.162/hls/ss1/index.m3u8"); //random 720p source
        //Uri mp4VideoUri =Uri.parse("https://www.youtube.com/watch?v=hYN7DOkQRcE"); //Radnom 540p indian channel
//        Uri mp4VideoUri =Uri.parse("http://cbsnewshd-lh.akamaihd.net/i/CBSNHD_7@199302/index_700_av-p.m3u8"); //CNBC
       // Uri mp4VideoUri =Uri.parse("http://live.field59.com/wwsb/ngrp:wwsb1_all/playlist.m3u8"); //ABC NEWS
        Uri mp4VideoUri =Uri.parse(" https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4"); //random 720p source
initializePlayer (mp4VideoUri);
//AIzaSyBWBBjQLPRDECrO0xSPjJE2vlH7kF5KHl0
        String youtubeLink = "https://www.youtube.com/watch?v=TrbDxaZsZj8";

    /*    new YouTubeExtractor (this) {
            @Override
            public void onExtractionComplete(SparseArray <YtFile> ytFiles, VideoMeta vMeta) {
                if (ytFiles != null) {
                    int itag = 22;
                     downloadUrl = ytFiles.get(itag).getUrl();
                    Log.e("_______",downloadUrl);
                }
            }
        }.extract(youtubeLink, true, true);
        Log.e("_______",downloadUrl);*/


    }
    private void initializePlayer(Uri mediaUri) {
        if (mExoPlayer == null) {
            // Create a default TrackSelector
         /*   DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
            TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);*/

            // Create the player
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(MainActivity.this);
            PlayerView mExoPlayerView=findViewById (R.id.exo);
            // Bind the player to the view.
            mExoPlayerView.setPlayer(mExoPlayer);
            // Measures bandwidth during playback. Can be null if not required.
            // Produces DataSource instances through which media data is loaded.
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(MainActivity.this, Util.getUserAgent(MainActivity.this, getString(R.string.app_name)));
            // This is the MediaSource representing the media to be played.
            MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(mediaUri);
            // Prepare the player with the source.
            mExoPlayer.prepare(videoSource);

            // onRestore
            if (mCurrentPosition != 0)
                mExoPlayer.seekTo(mCurrentPosition);

            mExoPlayer.setPlayWhenReady(mPlayWhenReady);
            mExoPlayerView.setVisibility(View.VISIBLE);




        }

        ///////////////////////

        }


    /**
     * Release ExoPlayer.
     */
    @Override
    public void onPause() {
        super.onPause();
        mExoPlayer.release();

    }
    private void releasePlayer() {
        if (mExoPlayer != null) {
            mPlayWhenReady = mExoPlayer.getPlayWhenReady();
            mCurrentPosition = mExoPlayer.getCurrentPosition();

            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }
    public interface CallBacks {

        void callbackObserver(Object obj);

        public interface playerCallBack {
            void onItemClickOnItem(Integer albumId);

            void onPlayingEnd();
        }
    }

    /////////////////////////////////////


}
