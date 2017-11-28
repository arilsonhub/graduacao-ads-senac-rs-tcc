package br.com.keystone.robo.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.com.keystone.robo.core.exceptions.IntranetException;

public class DateHelper {
	
	public static Integer DateAfter   = 2;
	public static Integer DateBefore  = 1;
	public static Integer DateEquals  = 0;

	public static String formatDateIsoToBR(Date date){
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		StringBuilder dataParaFormatar = new StringBuilder();
		
		String day   = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
		String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
		
		dataParaFormatar.append((calendar.get(Calendar.DAY_OF_MONTH) < 10 ? "0"+day : day))
						.append("/")
						.append((calendar.get(Calendar.MONTH) < 10 ? "0"+month : month))
						.append("/")
						.append(calendar.get(Calendar.YEAR));
		return dataParaFormatar.toString();
	}
	
	public static Integer getDateMonth(Date date){
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		
		return (calendar.get(Calendar.MONTH) + 1);
	}
	
	public static String formatDateToISO(Date date){
		
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		StringBuilder dataParaFormatar = new StringBuilder();
		
		String day   = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
		String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
		
		dataParaFormatar.append(calendar.get(Calendar.YEAR))
						.append("-")
						.append((calendar.get(Calendar.MONTH) < 10 ? "0"+month : month))
						.append("-")
						.append((calendar.get(Calendar.DAY_OF_MONTH) < 10 ? "0"+day : day));
		return dataParaFormatar.toString();		
	}
	
	public static Integer compareDates(Date x, Date y) throws IntranetException {
		
		try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            x = sdf.parse(formatDateToISO(x));
            y = sdf.parse(formatDateToISO(y));
            
            if(x.after(y))  return DateAfter;
            if(x.before(y)) return DateBefore;
            if(x.equals(y)) return DateEquals;            
            
            return null;

        }catch(ParseException ex){
            ex.printStackTrace();
            throw new IntranetException("Não foi possível formatar as datas para validação");
        }
	}
}