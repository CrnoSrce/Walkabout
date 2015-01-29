package uq.coedl.org.walkabout;

import java.util.ArrayList;
import java.util.List;

/**
 * Set of geographic locations (with associated attributes) which will be used as waypoints in
 * the game
 * Created by Mark on 29/01/2015.
 */
public class GoalSet implements DataSource {

    private ArrayList<Goal> goalList;

    public GoalSet() {
        goalList = new ArrayList<Goal>();
    }

    public GoalSet(List<Goal> goals) {
        goalList = new ArrayList<Goal>();
        //add all non-null Goals passed in to the goal list
        if (goals != null) {
            for (Goal g : goals) {
                if (g != null) {
                    goalList.add(g);
                }
            }
        }
    }

    @Override
    public int getNumWaypoints() {
        return goalList.size();
    }

    @Override
    public LocationInterface getWaypoint(final int waypointIndex) {
        LocationInterface waypoint = null;

        try {
            waypoint = goalList.get(waypointIndex).getLocation();
        } catch (IndexOutOfBoundsException e) {
            //Invalid index - return null
        }

        return waypoint;
    }

    @Override
    public Goal getGoal(final int goalIndex) {
        Goal goal = null;

        try {
            goal = goalList.get(goalIndex);
        } catch (IndexOutOfBoundsException e) {
            //Invalid index - return null
        }

        return goal;
    }

    @Override
    public List<String> getGoalNames() {
        List<String> goalNames = new ArrayList<String>();

        //iterate over goal list and extract names
        for (int i = 0; i < goalList.size(); ++i) {
            String name = goalList.get(i).getName();
            //if no name is supplied, provide a default goal name e.g. Goal 1, Goal 2 etc.
            //@TODO consider whether default names should be supplied by some other class e.g. in UI
            if (name == null || name.isEmpty()) {
                name = "Goal " + i;
            }
            goalNames.add(name);
        }

        return goalNames;
    }
}
