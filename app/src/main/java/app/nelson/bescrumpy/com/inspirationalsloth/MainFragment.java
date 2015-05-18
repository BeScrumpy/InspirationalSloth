package app.nelson.bescrumpy.com.inspirationalsloth;

/**
 * Created by Nelson on 4/16/2015.
 */

import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Random;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment implements View.OnClickListener {
    boolean buttonIsBlue;
    boolean speakerIsOn;
    ImageButton randomQuoteButton;
    ImageButton speakerButton;
    private String[] quotes;
    private static final Random rgenerator = new Random();
    private String prevQuote;
    MediaPlayer slothsqueek = null;
    MediaPlayer backgroundMusic = null;
    boolean soundOn = true;

    public MainFragment() {
        //Has to be empty
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        randomQuoteButton = (ImageButton) rootView.findViewById(R.id.randomQuoteButton);
        speakerButton = (ImageButton) rootView.findViewById(R.id.speakerButton);
        speakerButton.setOnClickListener(this);
        randomQuoteButton.setOnClickListener(this);



        //The first picture of the random Quote button is blue.
        buttonIsBlue = true;
        //The speaker will be on when the application first started
        speakerIsOn = true;

        return rootView;
    }

    @Override
    /**
     * This onClick method, handles everything of the main fragment.
     */
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.speakerButton:
                ImageButton speakerButton = (ImageButton) v;

                if(speakerIsOn){
                    speakerButton.setImageResource(R.drawable.sound_icon_off_100);
                    soundOn = false;
                    speakerIsOn = false;
                    if (slothsqueek != null){
                        slothsqueek.stop();
                    }
                }
                else{
                    speakerButton.setImageResource(R.drawable.sound_icon_100);
                    soundOn = true;
                    speakerIsOn = true;
                }
                break;

            case R.id.randomQuoteButton:
                ImageButton randomQuoteButton = (ImageButton) v;
                getQuote();
                changeRandomQuoteButton();
                playSlothSqueek();
                break;
        }
    }

    //RandomQuote Button Functions
    //----------------------------------------------------------------------------------------------

    /**
     * This method getQuote(), retrieves the quote from the quoteArray, and sets the text of
     * the theRandomQuote Textview, to the quote.
     */
    public void getQuote(){
        Resources res = getResources();
        quotes = res.getStringArray(R.array.quoteArray);
        String oneQuote = quotes[rgenerator.nextInt(quotes.length)];
        //Ensures that the quote you are getting isn't the one given to you previously
        while (oneQuote.equals(prevQuote)){
            oneQuote = quotes[rgenerator.nextInt(quotes.length)];
        }
        prevQuote = oneQuote;
        TextView text = (TextView) getActivity().findViewById(R.id.theRandomQuote);
        text.setText(oneQuote);
    }

    /**
     * This method changeRandomQuoteButton(), changes the randomQuoteButton to give a response to
     * the user when they click on the RandomQuote Button.
     */
    public void changeRandomQuoteButton(){
        if(buttonIsBlue){
            randomQuoteButton.setImageResource(R.drawable.pink_banner);
            buttonIsBlue = false;
        }
        else{
            randomQuoteButton.setImageResource(R.drawable.blue_banner);
            buttonIsBlue = true;
        }
    }

    public void playSlothSqueek(){
        if (soundOn) {
            if (slothsqueek != null){
                slothsqueek.reset();
                slothsqueek.release();
            }
            slothsqueek = MediaPlayer.create(getView().getContext(), R.raw.slothsqueek);


//        slothsqueek.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        slothsqueek.setLooping(true);
            slothsqueek.start();

        }
        }
    //--------------------------------------------------------------------------------------------
}