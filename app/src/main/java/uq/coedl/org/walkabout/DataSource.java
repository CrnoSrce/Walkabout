package uq.coedl.org.walkabout;

import java.util.List;

/**
 * Created by tp992853 on 29/01/2015.
 */
public interface DataSource
{
    /**
     * Get number of waypoints in the current goal list
     * @return 0 or greater
     */
    public int getNumWaypoints();

    /**
     * Get geographic coordinates of a goal given its index
     * @param waypointIndex the index of the goal from which to get the waypoint. Index of first
     *                      goal is 0
     * @return the geographic coordinates of the goal, or if the index is negative or does not
     * correspond to a goal in the list, return null
     */
    public LocationInterface getWaypoint(final int waypointIndex);

    /**
     * Get a goal given its index
     * @param goalIndex the index of the goal to get
     * @return the goal object, or if the index is negative or does not correspond to a goal in the
     * list, return null
     */
    public Goal getGoal(final int goalIndex);

    /**
     * Get an ordered list of the names of the goals in the goal list - e.g. for display in a list
     * of goals from which users can select
     * @return List of goal names. Should not be null but may be empty list
     */
    public List<String> getGoalNames();
}
