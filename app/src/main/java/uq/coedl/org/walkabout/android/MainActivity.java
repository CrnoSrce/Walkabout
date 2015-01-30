package uq.coedl.org.walkabout.android;

import android.content.Context;
import android.content.res.Resources;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.TimerTask;

import uq.coedl.org.walkabout.GameManager;
import uq.coedl.org.walkabout.GoalSet;
import uq.coedl.org.walkabout.android.SampleDataProviderAndroid.SampleGoals;
import uq.coedl.org.walkabout.R;


public class MainActivity extends ActionBarActivity {
    private static GameManager gameManager = new GameManager();
    private static LocationHelper locationHelper = null;

    private TimerTask gameLoopTask = null;
    //private SoundHandle[] directionSoundHandles = new SoundHandle[DirectionCalculatorAndroid.getNumDirections()];

    private TextView textStatusReady;
    private TextView textStatusSeeking;
    private TextView textStatusSuccess;
    private TextView textContentGoalname;
    private TextView textContentDirection;
    private TextView textContentFeedback;
    private ImageView imageContentSuccess;

    private Button buttonGo;
    private Button buttonNext;

    private class GameTimerTask extends TimerTask
    {
        @Override
        public void run()
        {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialiseUIReferences();

        if (!gameManager.isInitialised())
        {
            if(locationHelper == null)
            {
                locationHelper = new LocationHelper((LocationManager) this.getSystemService(Context.LOCATION_SERVICE));
            }
            //get sample Goal data
            final GoalSet sampleGoalSet =
                    new GoalSet(SampleDataProviderAndroid.getGoals(SampleGoals.DOUBLE));
            final DirectionCalculatorAndroid directionCalculator = new DirectionCalculatorAndroid();
            gameManager.initialise(sampleGoalSet, directionCalculator);
        }
        gameLoopTask = new GameTimerTask();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onGameReady(String goalName) {
        //turn off elements not required
        textStatusSeeking.setVisibility(View.INVISIBLE);
        textStatusSuccess.setVisibility(View.INVISIBLE);
        textContentDirection.setVisibility(View.INVISIBLE);
        textContentFeedback.setVisibility(View.INVISIBLE);
        imageContentSuccess.setVisibility(View.INVISIBLE);
        buttonNext.setVisibility(View.INVISIBLE);

        //turn on elements required
        textStatusReady.setVisibility(View.VISIBLE);
        textContentGoalname.setVisibility(View.VISIBLE);
        buttonGo.setVisibility(View.VISIBLE);

        //set content of items which are on
        //@TODO replace constant with resource id
        textStatusReady.setText("Ready to find goal");
        textContentGoalname.setText(goalName);
    }

    public void onGameStart(String goalName) {
        //turn off elements not required
        textStatusReady.setVisibility(View.INVISIBLE);
        textStatusSuccess.setVisibility(View.INVISIBLE);
        textContentGoalname.setVisibility(View.INVISIBLE);
        imageContentSuccess.setVisibility(View.INVISIBLE);
        buttonGo.setVisibility(View.INVISIBLE);
        buttonNext.setVisibility(View.INVISIBLE);

        //turn on elements required
        textStatusSeeking.setVisibility(View.VISIBLE);
        textContentDirection.setVisibility(View.INVISIBLE);
        textContentFeedback.setVisibility(View.INVISIBLE);

        //set content of items which are on
        //@TODO replace constant with resource id
        textStatusSeeking.setText("Seeking " + goalName);
        textContentDirection.setText("");
        textContentFeedback.setText("");

        //commence periodic update loop
        periodicUpdate();
    }

    public void onGameUpdate(String direction, String directionAudio, String feedback, String feedbackAudio) {

    }

    //@TODO fix these
    public void onSuccess(String successMessage, String successImage, String successAudio) {
        //turn off elements not required
        textStatusReady.setVisibility(View.INVISIBLE);
        textStatusSuccess.setVisibility(View.INVISIBLE);
        textContentGoalname.setVisibility(View.INVISIBLE);
        imageContentSuccess.setVisibility(View.INVISIBLE);
        buttonGo.setVisibility(View.INVISIBLE);
        buttonNext.setVisibility(View.INVISIBLE);

        //turn on elements required
        textStatusSeeking.setVisibility(View.VISIBLE);
        textContentDirection.setVisibility(View.INVISIBLE);
        textContentFeedback.setVisibility(View.INVISIBLE);

        //set content of items which are on
        //@TODO replace constant with resource id
        final String goalName = "TEMP GOAL";
        textStatusSeeking.setText("Seeking " + goalName);
        textContentDirection.setText("");
        textContentFeedback.setText("");
    }

    public void onFailure() {

    }

    public void periodicUpdate() {

    }

    public void endPeriodicUpdate() {

    }

    private void initialiseUIReferences() {
        textStatusReady = (TextView) findViewById(R.id.text_status_ready);
        textStatusSeeking = (TextView) findViewById(R.id.text_status_seeking);
        textStatusSuccess = (TextView) findViewById(R.id.text_status_success);
        textContentGoalname = (TextView) findViewById(R.id.text_content_goalname);
        textContentDirection = (TextView) findViewById(R.id.text_content_direction);
        textContentFeedback = (TextView) findViewById(R.id.text_content_feedback);
        imageContentSuccess = (ImageView) findViewById(R.id.image_content_success);

        buttonGo = (Button) findViewById(R.id.buttonGo);
        buttonNext = (Button) findViewById(R.id.buttonNext);
    }

//    private void setupSounds()
//    {
//        final Resources res = getResources();
//        for(int i=0; i<DirectionCalculatorAndroid.getNumDirections(); ++i)
//        {
//            directionSoundHandles[i] =
//                    res.getIdentifier(DirectionCalculatorAndroid.CardinalDirectionReference.values()[i].getFilename(),
//                                      "raw", "uq.coedl.org.walkabout")
//        }
//
//        headwordAudioResId = res.getIdentifier((String)dictionaryEntry.getHeadword(), "raw", "com.sassysoft.bininjgunwokdictionary");
//        new LoadSoundTask().execute(this);
//        final ImageButton playSoundButton = (ImageButton)findViewById(R.id.fullEntryPlaySoundImageButton);
//        if(headwordAudioResId != 0)
//        {
//            playSoundButton.setClickable(true);
//        }
//        else
//        {
//            playSoundButton.setClickable(false);
//        }
//        playSoundButton.setOnClickListener(new View.OnClickListener()
//        {
//            public void onClick(View view)
//            {
//                if(headwordAudioHandle != null)
//                {
//                    soundPool.play(headwordAudioHandle.getHandle(), 1.0f, 1.0f, 0, 0, 1.0f);
//                }
//            }
//        });
//    }



}
