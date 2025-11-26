package cliente;

import calcular.Tarea;
import java.io.Serializable;
import java.math.BigDecimal;


public class Pi implements Tarea<BigDecimal>, Serializable {

    private static final long serialVersionUID = 227L;

    private static final BigDecimal CUATRO = BigDecimal.valueOf(4);

    private static final int modoRedondeo = BigDecimal.ROUND_HALF_EVEN;

    private final int digitos;

    public Pi(int digitos) {
        this.digitos = digitos;
    }

    public BigDecimal ejecutar() {
        return calcularPi(digitos);
    }

    public static BigDecimal calcularPi(int digitos) {
        int escala = digitos + 5;
        BigDecimal arctan1_5 = arctan(5, escala);
        BigDecimal arctan1_239 = arctan(239, escala);
        BigDecimal pi = arctan1_5.multiply(CUATRO).subtract(
                arctan1_239).multiply(CUATRO);
        return pi.setScale(digitos, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal arctan(int inversoX, int escala) {
        BigDecimal resultado, numerador, termino;
        BigDecimal invX = BigDecimal.valueOf(inversoX);
        BigDecimal invX2 = BigDecimal.valueOf(inversoX * inversoX);

        numerador = BigDecimal.ONE.divide(invX, escala, modoRedondeo);
        resultado = numerador;
        int i = 1;
        do {
            numerador = numerador.divide(invX2, escala, modoRedondeo);
            int denominador = 2 * i + 1;
            termino = numerador.divide(BigDecimal.valueOf(denominador),
                    escala, modoRedondeo);
            if ((i % 2) != 0) {
                resultado = resultado.subtract(termino);
            } else {
                resultado = resultado.add(termino);
            }
            i++;
        } while (termino.compareTo(BigDecimal.ZERO) != 0);
        return resultado;
    }
}
