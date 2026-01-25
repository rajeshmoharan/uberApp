package org.spring.demo.uberapp.util;

import java.util.Random;
import java.util.function.Supplier;

public class Utils {

    public static String generateOtp(){
        Supplier<String> generateRideOtp = () -> String.valueOf(new Random().nextInt(1000,10000));
        return generateRideOtp.get();
    }

}
