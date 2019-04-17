import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Java8_LocalDate_To_UtilDate {

	public static void main(String[] args) {
		
		java.util.Date date = null;
		LocalDate localDate  = LocalDateTime.now().toLocalDate();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = (java.util.Date) df.parse(String.valueOf(localDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("當天 0時 0分 0秒 的 java.util.Date = "+date);
		System.out.println("當天 0時 0分 0秒 的 毫秒數 = "+date.getTime());
		System.out.println("當天 0時 0分 0秒 的 java.sql.Date = "+new java.sql.Date(date.getTime()));
		
		System.out.println("當天 0時 0分 0秒 的 毫秒數 = " + java.sql.Date.valueOf(LocalDate.now()).getTime());

	}

}
