package com.dvlcube.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author wonka
 * @since 28/02/2013
 */
public class CubeDate {
	public static Calendar gerarDataNoIntervalo(Calendar cInicio, Calendar cFim) {
		long inicio = cInicio.getTime().getTime();
		long fim = cFim.getTime().getTime();
		return gerarDataNoIntervalo(inicio, fim);
	}

	public static Calendar gerarDataNoIntervalo(Date dataInicial, Date dataFinal) {
		Calendar start = Calendar.getInstance();
		start.setTime(dataInicial);

		Calendar end = Calendar.getInstance();
		end.setTime(dataFinal);

		return gerarDataNoIntervalo(start, end);
	}

	public static Calendar gerarDataNoIntervalo(long inicio, long fim) {
		long random = inicio + (long) (Math.random() * (fim - inicio + 1));
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(random));
		return cal;
	}

	public static Calendar gerarDataNoIntervalo(Range<Calendar> intervalo) {
		Calendar cInicio = Calendar.getInstance();
		cInicio.set(Calendar.HOUR_OF_DAY, intervalo.getStart().get(Calendar.HOUR_OF_DAY));
		cInicio.set(Calendar.MINUTE, intervalo.getStart().get(Calendar.MINUTE));
		long inicio = cInicio.getTime().getTime();

		Calendar cFim = Calendar.getInstance();
		cFim.set(Calendar.HOUR_OF_DAY, intervalo.getEnd().get(Calendar.HOUR_OF_DAY));
		cFim.set(Calendar.MINUTE, intervalo.getEnd().get(Calendar.HOUR_OF_DAY));
		long fim = cFim.getTime().getTime();

		return gerarDataNoIntervalo(inicio, fim);
	}

	public Calendar date;
}
