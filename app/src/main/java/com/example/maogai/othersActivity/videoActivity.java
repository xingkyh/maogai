package com.example.maogai.othersActivity;

import android.os.Bundle;
import android.view.View;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.example.maogai.R;
import androidx.appcompat.app.AppCompatActivity;

public class videoActivity extends AppCompatActivity implements View.OnClickListener {
    private StyledPlayerView s1;
    private SimpleExoPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MediaItem mediaItem = null;
        player = new SimpleExoPlayer.Builder(this).build();
        setContentView(R.layout.activity_video);
        s1 = (StyledPlayerView) findViewById(R.id.svideo);

        s1.setPlayer(player);
        String var = getIntent().getStringExtra("name");
        if (var.equals("1"))
            mediaItem = MediaItem.fromUri("https://vkceyugu.cdn.bspapp.com/VKCEYUGU-imgbed/4089da93-fa9d-4bd0-aaf2-afa9868251df.mp4");
        if(var.equals("2"))
            mediaItem = MediaItem.fromUri("https://vkceyugu.cdn.bspapp.com/VKCEYUGU-imgbed/3ad36d2b-23aa-48fd-b874-4ea4a6c2348c.mp4");
        if(var.equals("3"))
            mediaItem = MediaItem.fromUri("https://vkceyugu.cdn.bspapp.com/VKCEYUGU-imgbed/8dddd85e-25f0-4ef1-9395-ad207f030b68.mp4");

        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();

    }
    @Override
    public void onClick(View v) {

    }

    public void onDestroy() {
        super.onDestroy();
        player.release();
    }
}
