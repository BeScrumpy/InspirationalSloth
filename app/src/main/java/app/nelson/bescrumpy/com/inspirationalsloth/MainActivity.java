package app.nelson.bescrumpy.com.inspirationalsloth;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;


public class MainActivity extends ActionBarActivity{
    //Removed the background noise for now, because it was annoying.
    //BackgroundSound mBackgroundSound = new BackgroundSound();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View mainView = findViewById(R.id.container);
        View root = mainView.getRootView();
        root.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_settings:
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void onResume() {
        super.onResume();
        //mBackgroundSound.execute();
    }

    public void onPause() {
        super.onPause();
        //mBackgroundSound.cancel(true);
    }

    /**
     * http://stackoverflow.com/questions/7928803/background-music-android
     * The background sound was really annoying turned into an unused method.
     */
    public class BackgroundSound extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            MediaPlayer player = MediaPlayer.create(MainActivity.this, R.raw.calm_backgroundmusic);

            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setLooping(true); // Set looping
            player.setVolume(100,100);
            player.start();

            return null;
        }
    }
}
