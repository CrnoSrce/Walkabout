package uq.coedl.org.walkabout;

/**
 * Created by tp992853 on 29/01/2015.
 */
public interface DataSource
{
    public int getNumWaypoints();
    public Location getWaypoint(final int waypointIndex);
}
