package uq.coedl.org.walkabout.android;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

    private TextView textStatusReady;
    private TextView textStatusSeeking;
    private TextView textStatusSuccess;
    private TextView textContentGoalname;
    private TextView textContentDirection;
    private TextView textContentFeedback;
    private ImageView imageContentSuccess;

    private Button buttonGo;
    private Button buttonNext;

    private class GameTimerTask extends TimerTask {
        @Override
        public void run() {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialiseUIReferences();

        if (!gameManager.isInitialised()) {
            if (locationHelper == null) {
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
        textContentDirection.setVisibility(View.VISIBLE);
        textContentFeedback.setVisibility(View.VISIBLE);

        //set content of items which are on
        //@TODO replace constant with resource id
        textStatusSeeking.setText("Seeking " + goalName);
        textContentDirection.setText("");
        textContentFeedback.setText("");

        //commence periodic update loop
        periodicUpdate();
    }

    public void onGameUpdate(String direction, String directionAudio, String feedback, String feedbackAudio) {
        if (direction != null) {
            textContentDirection.setText(direction);
        }
        if (directionAudio != null) {
            try {
                //@TODO
            } catch (Exception e) {
                //unable to play the direction audio
                Log.e("onGameUpdate", "Unable to play direction audio", e);
            }
        }

        if (feedback != null) {
            textContentDirection.setText(feedback);
        }
        if (feedbackAudio != null) {
            try {
                //@TODO
            } catch (Exception e) {
                //unable to play the feedback audio
                Log.e("onGameUpdate", "Unable to play feedback audio", e);
            }
        }
    }

    public void onSuccess(String successMessage, String successImage, String successAudio) {
        //stop the periodic update
        //@TODO maybe this should go in another method
        endPeriodicUpdate();

        //turn off elements not required
        textStatusReady.setVisibility(View.INVISIBLE);
        textStatusSeeking.setVisibility(View.INVISIBLE);
        textContentGoalname.setVisibility(View.INVISIBLE);
        textContentDirection.setVisibility(View.INVISIBLE);
        textContentFeedback.setVisibility(View.INVISIBLE);
        buttonGo.setVisibility(View.INVISIBLE);

        //turn on elements required
        textStatusSuccess.setVisibility(View.VISIBLE);
        imageContentSuccess.setVisibility(View.VISIBLE);
        buttonNext.setVisibility(View.VISIBLE);

        //set content of items which are on
        textStatusSuccess.setText(successMessage);
        try {
            int resourceID = getResources().getIdentifier(successImage, "drawable", getPackageName());
            imageContentSuccess.setImageResource(resourceID);
        } catch (Exception e) {
            //unable to set the success image
            Log.e("onSuccess", "Unable to set success image", e);
        }

        //play the success audio if any
        try {
            //@TODO
        } catch (Exception e) {
            //unable to play the success audio
            Log.e("onSuccess", "Unable to play success audio", e);
        }
    }

    /**
     * @TODO
     */
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

}
