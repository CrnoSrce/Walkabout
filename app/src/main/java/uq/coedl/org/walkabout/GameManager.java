package uq.coedl.org.walkabout;

/**
 * Created by tp992853 on 29/01/2015.
 */
public class GameManager
{
    public enum State { UNINITIALISED, READY, SEEKING, SUCCESS }

    private DataSource dataSource = null;
    private State state = State.UNINITIALISED;
    private int currentWaypointIndex = 0;
    private DirectionCalculator directionCalculator;

    public GameManager()
    {
    }

    public void initialise(DataSource dataSource)
    {
        this.dataSource = dataSource;
        state = State.READY;
    }

    public DirectionalReference updateDirectionalReference(final Location currentLocation)
    {
        final Location currentWaypoint = dataSource.getWaypoint(currentWaypointIndex);
        return directionCalculator.directionBetween(currentLocation, currentWaypoint);
    }

    public void gotoNextWaypoint()
    {
        if(ready())
        {
            currentWaypointIndex = Math.min(currentWaypointIndex + 1, dataSource.getNumWaypoints());
        }
    }

    private boolean ready()
    {
        return state == State.READY;
    }

    public State getState()
    {
        return state;
    }
}
