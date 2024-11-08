package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class UtilEstadistica {

    public static BigDecimal media(double[] x, int escala, RoundingMode redondeo) {
        if (x == null || x.length == 0) {
            throw new IllegalArgumentException("El array no puede estar vacío");
        }

        double suma = 0.0;
        for (double num : x) {
            suma += num;
        }

        double media = suma / x.length;
        return BigDecimal.valueOf(media).setScale(escala, redondeo);
    }
}
