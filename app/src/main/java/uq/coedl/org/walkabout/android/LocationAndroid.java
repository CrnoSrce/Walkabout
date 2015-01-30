package uq.coedl.org.walkabout.android;

import android.location.Location;

import uq.coedl.org.walkabout.LocationInterface;

/**
 * Class to wrap a pair of geographic X and Y coordinates - Android version
 */
public class LocationAndroid implements LocationInterface {
    private Location location;

    public LocationAndroid(Location location) {
        this.location = location;
    }

    @Override
    public Double getX() {
        if (location == null) {
            return null;
        } else {
            return location.getLongitude();
        }
    }

    @Override
    public Double getY() {
        if (location == null) {
            return null;
        } else {
            return location.getLongitude();
        }
    }

    public Location getLocation()
    {
        return location;
    }
}
