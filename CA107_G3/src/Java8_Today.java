import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Java8_Today {
	
public static void main(String[] args) {
		
		System.out.println("當天0時0分0秒的 java.time.LocalDateTime = " + LocalDate.now().atTime(0, 0, 0, 0).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		System.out.println("當天0時0分0秒的 java.time.LocalDate     = " + LocalDate.now()                   .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		System.out.println("當天0時0分0秒的 java.time.LocalTime     = " + LocalTime      .of(0,0,0,0)       .format(DateTimeFormatter.ofPattern("HH:mm:ss")));
		
		
		System.out.println();
		long long_today =  java.sql.Date.valueOf(LocalDate.now()).getTime();
		System.out.println("當天0時0分0秒的毫秒數 = " + long_today);
		System.out.println();
		
		System.out.println("當天0時0分0秒的 java.sql.Timestamp = " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.sql.Timestamp(long_today)));
		System.out.println("當天0時0分0秒的 java.sql.Date      = " + new SimpleDateFormat("yyyy-MM-dd").format(new java.sql.Date(long_today)));
		System.out.println("當天0時0分0秒的 java.sql.Time      = " + new SimpleDateFormat("HH:mm:ss").format(new java.sql.Time(long_today)));
    System.out.println("當天0時0分0秒的 java.util.Date     = " + new java.util.Date(long_today));

	}

}
