package com.arqui.ufps.freelancer.utils;

import org.springframework.data.util.Pair;

public class Defines {

    //RESPONSE MESSAGES AND CODES
    public static final Pair<Integer, String> SUCCESS                       = Pair.of(0, "success");
    public static final Pair<Integer, String> FAILED                        = Pair.of(1, "failed");
    public static final Pair<Integer, String> MISSING_DATA                  = Pair.of(2, "Faltan datos");
}
