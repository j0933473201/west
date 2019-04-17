import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

public class Today_0hours_0minutes_0seconds {

	public static void main(String[] args) throws ParseException {

		/* Java8 */
		LocalDate localDate = LocalDateTime.now().toLocalDate(); // localDate = 2017-03-10 (當前日期)
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = df.parse(String.valueOf(localDate));

		/* Java7 */
		// Calendar cal = Calendar.getInstance(); //當前日期和時間
		// cal.set(Calendar.HOUR_OF_DAY, 0 );     //當前小時設成０
		// cal.set(Calendar.MINUTE, 0 );          //當前分鐘設成０
		// cal.set(Calendar.SECOND, 0 );          //當前秒數設成０
		// cal.set(Calendar.MILLISECOND, 0 );     //當前毫秒設成０
		// java.util.Date date = cal.getTime();

		System.out.println("當天 0時 0分 0秒 的 java.util.Date = " + date);
		System.out.println("當天 0時 0分 0秒 的 毫秒數 = " + date.getTime());
		System.out.println("當天 0時 0分 0秒 的 java.sql.Date = " + new java.sql.Date(date.getTime()));
		
		System.out.println("當天 0時 0分 0秒 的 毫秒數 = " + java.sql.Date.valueOf(LocalDate.now()).getTime());

	}

}
