import org.example.UtilEstadistica;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilEstadisticaTest {

    @Test
    public void testMediaConRedondeoHaciaArriba() {
        double[] numeros = {1.5, 2.5, 3.5, 4.5};
        BigDecimal resultado = UtilEstadistica.media(numeros, 2, RoundingMode.UP);
        assertEquals(new BigDecimal("3.00"), resultado);
    }

    @Test
    public void testMediaConRedondeoHaciaAbajo() {
        double[] numeros = {1.5, 2.5, 3.5, 4.5};
        BigDecimal resultado = UtilEstadistica.media(numeros, 1, RoundingMode.DOWN);
        assertEquals(new BigDecimal("3.0"), resultado);
    }
}

