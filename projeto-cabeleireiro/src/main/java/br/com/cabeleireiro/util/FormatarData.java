package br.com.cabeleireiro.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class FormatarData {


	public static Date formataData() {
		// formatar data atual com horas
		String dataAtual = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");
		TimeZone.setDefault(tz);
		formato.setTimeZone(tz);

		Date data = null;

		try {
			data = formato.parse(dataAtual);

			System.err.println(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	
	
}
