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
    private DirectionCalculator directionCalculator = null;

    public GameManager()
    {
    }

    public void initialise(final DataSource dataSource, final DirectionCalculator directionCalculator)
    {
        this.dataSource = dataSource;
        this.directionCalculator = directionCalculator;
        state = State.READY;
    }

    public DirectionalReference updateDirectionalReference(final LocationInterface currentLocationInterface)
    {
        final LocationInterface currentWaypoint = dataSource.getWaypoint(currentWaypointIndex);
        return directionCalculator.directionBetween(currentLocationInterface, currentWaypoint);
    }

    public void gotoNextWaypoint()
    {
        if(isReady())
        {
            currentWaypointIndex = Math.min(currentWaypointIndex + 1, dataSource.getNumWaypoints());
        }
    }

    public boolean isInitialised()
    {
        return state != State.UNINITIALISED;
    }

    private boolean isReady()
    {
        return state == State.READY;
    }

    public State getState()
    {
        return state;
    }
}
