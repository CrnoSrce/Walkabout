package uq.coedl.org.walkabout.android;

import android.location.Location;

import java.util.ArrayList;
import java.util.List;

import uq.coedl.org.walkabout.Goal;

/**
 * Created by Mark on 29/01/2015.
 */
public abstract class SampleDataProviderAndroid {

    //@TODO add more goal data sets later
    public enum SampleGoals {
        SINGLE, DOUBLE
    }

    public static List<Goal> getGoals(SampleGoals sample) {
        switch (sample) {
            case DOUBLE:
                return goalDouble();
            case SINGLE:
            default:
                return goalSingle();
        }
    }

    private static List<Goal> goalSingle() {

        List<Goal> goalList = new ArrayList<Goal>();

        Location loc1 = new Location("SampleDataProviderAndroid");
        loc1.setLatitude(-27.498901);
        loc1.setLongitude(153.015651);
        goalList.add(new Goal(new LocationAndroid(loc1), "Lakepaths",
                "Intersection of two paths on St Lucia campus near lake",
                "You found the intersection!", "success_intersection.3ga", "success_intersection.jpg"));

        return goalList;
    }

    private static List<Goal> goalDouble() {
        //add to the goals returned by goalSingle
        List<Goal> goalList = goalSingle();

        Location loc1 = new Location("SampleDataProviderAndroid");
        loc1.setLatitude(-27.500474);
        loc1.setLongitude(153.014561);
        goalList.add(new Goal(new LocationAndroid(loc1), "Cafe",
                "Mr Bean's cafe near Sir James Foots Building",
                "Help yourself to a coffee", "success_mrbean.3ga", "success_mrbean.jpg"));

        return goalList;
    }

}
