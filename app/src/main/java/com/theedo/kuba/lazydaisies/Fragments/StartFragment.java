package com.theedo.kuba.lazydaisies.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.theedo.kuba.lazydaisies.MainActivity;
import com.theedo.kuba.lazydaisies.R;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StartFragment extends Fragment {
    public static final String URI_STRING = "https://firebasestorage.googleapis.com/v0/b/lazydaisies-248f1.appspot.com/o/LD_for_app3.mp4?alt=media&token=0de3024d-b4bc-4c54-a2a0-fc5706f9d776";
    @BindView(R.id.SimpleExoPlayer)
    SimpleExoPlayerView simpleExoPlayerView;

    @BindView(R.id.error_start)
    ImageView errorImage;

    SimpleExoPlayer player;
    FragmentUtils mainActivity;



    // TODO: Rename and change types and number of parameters
    public static StartFragment newInstance() {
        StartFragment fragment = new StartFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity) getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        errorImage.setVisibility(View.GONE);


        Uri uriLink = Uri.parse(URI_STRING);


        Handler mainHandler = new Handler();
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

        player =
                ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);

        player.setPlayWhenReady(true);
        player.setVolume(0f);
        simpleExoPlayerView.setPlayer(player);
        simpleExoPlayerView.hideController();


        // Measures bandwidth during playback. Can be null if not required.
// Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                Util.getUserAgent(getContext(), "yourApplicationName"), (TransferListener<? super DataSource>) bandwidthMeter);
// Produces Extractor instances for parsing the media data.
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

//        ExtractorMediaSource.EventListener eventListener = new ExtractorMediaSource.EventListener() {
//            @Override
//            public void onLoadError(IOException error) {
//
//                Toast.makeText(getContext(), "Blad ladowania wideo", Toast.LENGTH_SHORT).show();
//
//                errorImage.setVisibility(View.GONE);
//
//            }
//        };



// This is the MediaSource representing the media to be played.
        MediaSource videoSource = new ExtractorMediaSource(uriLink,
                dataSourceFactory, extractorsFactory, null, null);
        LoopingMediaSource loopingSource = new LoopingMediaSource(videoSource);
// Prepare the player with the source.
        player.prepare(loopingSource);

        ExoPlayer.EventListener eventList2 = new ExoPlayer.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {
                mainActivity.showSnackbar("Błąd ładowania wideo - sprawdź połączenie");

                errorImage.setVisibility(View.VISIBLE);

            }

            @Override
            public void onPositionDiscontinuity() {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }
        };


        player.addListener(eventList2);



    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        player.release();
    }


}
