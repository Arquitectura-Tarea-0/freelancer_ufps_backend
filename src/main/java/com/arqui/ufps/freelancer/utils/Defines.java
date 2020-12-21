package com.arqui.ufps.freelancer.utils;

import org.springframework.data.util.Pair;

public class Defines {

    //RESPONSE MESSAGES AND CODES
    public static final Pair<Integer, String> SUCCESS                       = Pair.of(0, "success");
    public static final Pair<Integer, String> FAILED                        = Pair.of(1, "failed");
    public static final Pair<Integer, String> MISSING_DATA                  = Pair.of(2, "Faltan datos");
    public static final Pair<Integer, String> USER_NOT_FOUND                = Pair.of(3, "Usuario no encontrado");
    public static final Pair<Integer, String> CATEGORY_NOT_FOUND            = Pair.of(4, "Categoria no encontrada");
    public static final Pair<Integer, String> SERVICE_OFFER_NOT_FOUND       = Pair.of(5, "Oferta de servicio no encontrada");
    public static final Pair<Integer, String> CONTRACTOR_FOUND              = Pair.of(6, "El usuario es un contratante");
}
