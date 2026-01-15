package org.spring.demo.uberapp.servies;

import org.locationtech.jts.geom.Point;

public interface DistanceService {

    /**
     * it wil take source and destination to
     * calculate the distance
     * @param src
     * @param dest
     * @return
     */
    double calculateDistance(Point src,Point dest);

}
