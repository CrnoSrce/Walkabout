package uq.coedl.org.walkabout.android;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import uq.coedl.org.walkabout.GameManager;
import uq.coedl.org.walkabout.GoalSet;
import uq.coedl.org.walkabout.LocationInterface;
import uq.coedl.org.walkabout.android.SampleDataProviderAndroid.SampleGoals;
import uq.coedl.org.walkabout.R;


public class MainActivity extends ActionBarActivity
{
    private static GameManager gameManager = new GameManager();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!gameManager.isInitialised())
        {
            //get sample Goal data
            final GoalSet sampleGoalSet =
                    new GoalSet(SampleDataProviderAndroid.getGoals(SampleGoals.SINGLE));
            final DirectionCalculatorAndroid directionCalculator = new DirectionCalculatorAndroid();
            gameManager.initialise(sampleGoalSet, directionCalculator);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
