package uq.coedl.org.walkabout;

/**
 * Created by tp992853 on 29/01/2015.
 */
public class GameManager
{
    public enum State { UNINITIALISED, READY, SEEKING, SUCCESS }

    private DataSource dataSource = null;
    private State state = State.UNINITIALISED;
    private int current;

    public GameManager()
    {
    }

    public void initialise(DataSource dataSource)
    {
        this.dataSource = dataSource;
        state = State.READY;
    }

    public State getState()
    {
        return state;
    }
}
