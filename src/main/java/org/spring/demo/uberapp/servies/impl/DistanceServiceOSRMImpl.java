package org.spring.demo.uberapp.servies.impl;

import org.locationtech.jts.geom.Point;
import org.spring.demo.uberapp.servies.DistanceService;
import org.springframework.stereotype.Service;

@Service
public class DistanceServiceOSRMImpl implements DistanceService {

    @Override
    public double calculateDistance(Point src, Point dest) {
        //call the third party api here to calculate the distance
        return 0;
    }
}
