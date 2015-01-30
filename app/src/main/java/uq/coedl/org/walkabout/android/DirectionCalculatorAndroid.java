package uq.coedl.org.walkabout.android;

import android.location.Location;

import uq.coedl.org.walkabout.DirectionCalculator;
import uq.coedl.org.walkabout.DirectionalReference;
import uq.coedl.org.walkabout.LocationInterface;

/**
 * Created by tp992853 on 30/01/2015.
 */
public class DirectionCalculatorAndroid implements DirectionCalculator
{
    public enum CardinalDirectionReference implements DirectionalReference
    { NORTH, NOR_EAST, EAST, SOU_EAST, SOUTH, SOU_WEST, WEST, NOR_WEST, FAILED};

    @Override
    public DirectionalReference directionBetween(LocationInterface fromLocationInterface, LocationInterface toLocationInterface)
    {
        CardinalDirectionReference result = CardinalDirectionReference.FAILED;

        final LocationAndroid fromLocation = (LocationAndroid) fromLocationInterface;
        final LocationAndroid toLocation = (LocationAndroid) toLocationInterface;
        if((fromLocation != null) && (toLocation != null))
        {
            final Location fromLocationAndroid = fromLocation.getLocation();
            final Location toLocationAndroid = toLocation.getLocation();
            if((fromLocationAndroid != null) && (toLocationAndroid != null))
            {
                final float bearing = fromLocationAndroid.bearingTo(toLocationAndroid);
                final float degreesBetweenBearings = (360.0f/getNumDirections()); // = 45 degrees
                // we want to convert the range [-22.5, 22.5] degrees to be North, with all the other points
                // being the same but with their ranges shifted by +45 degrees. To do this, we take the bearing
                // and add degreesBetweenBearings/2 (= 22.5) to the bearing which gives a value that
                // can easily be converted into a sector from 0 to (getNumDirections())
                // which maps to the values themselves.
                final float bearingForDeterminingCompassPoint = bearing + (degreesBetweenBearings/2.0f);
                final int compassBearing = (int)(bearingForDeterminingCompassPoint/degreesBetweenBearings);
                if(compassBearing >= 0 && compassBearing < getNumDirections())
                {
                    result = CardinalDirectionReference.values()[compassBearing];
                }
            }
        }
        return result;
    }

    public int getNumDirections() { return CardinalDirectionReference.values().length-1; }
}
