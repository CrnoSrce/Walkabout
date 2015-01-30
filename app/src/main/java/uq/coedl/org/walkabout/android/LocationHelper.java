package uq.coedl.org.walkabout.android;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import uq.coedl.org.walkabout.LocationInterface;

/**
 * Created by Mark on 30/01/2015.
 */
public class LocationHelper {

    private LocationManager locationManager;

    public LocationHelper(LocationManager locationManager)
    {
        this.locationManager = locationManager;
    }

    /**
     * Get coordinates of the last known GPS location of the device
     * @return object containing coordinates
     */
    public LocationInterface getLastKnownLocation() {
        //now poll it to get GPS coordinates
        Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        //wrap in LocationAndroid object and return
        return new LocationAndroid(lastKnownLocation);
    }

}
