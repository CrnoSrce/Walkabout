package uq.coedl.org.walkabout;

/**
 * Created by tp992853 on 29/01/2015.
 */
public interface LocationInterface
{
    /**
     * @return null if location not available, else double representing X coord e.g. longitude
     */
    public Double getX();

    /**
     * @return null if location not available, else double representing Y coord e.g. latitude
     */
    public Double getY();
}
