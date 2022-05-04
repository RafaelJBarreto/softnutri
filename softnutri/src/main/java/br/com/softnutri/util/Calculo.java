package br.com.softnutri.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculo {
	
	public static BigDecimal getImc(BigDecimal peso, BigDecimal altura) {
		 return peso.divide(altura.pow(2), 2, RoundingMode.HALF_UP);
	}

	public static BigDecimal getRelacaoCinturaQuadril(BigDecimal cintura, BigDecimal quadril) {
		 return cintura.divide(quadril, 2, RoundingMode.HALF_UP);
	}
}
