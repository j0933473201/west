import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;

public class Java8_Now {

	public static void main(String[] args) {
		
		System.out.println("��e����M�ɶ��� java.time.LocalDateTime = " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		System.out.println("��e����M�ɶ��� java.time.LocalDate     = " + LocalDate.now()    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		System.out.println("��e����M�ɶ��� java.time.LocalTime     = " + LocalTime.now()    .format(DateTimeFormatter.ofPattern("HH:mm:ss")));
		
		
		System.out.println();
		long long_now =  new java.util.Date().getTime();
		System.out.println("��e����M�ɶ����@��� = " + long_now);
		System.out.println();
		
		System.out.println("��e����M�ɶ��� java.sql.Timestamp = " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.sql.Timestamp(long_now)));
		System.out.println("��e����M�ɶ��� java.sql.Date      = " + new SimpleDateFormat("yyyy-MM-dd").format(new java.sql.Date(long_now)));
		System.out.println("��e����M�ɶ��� java.sql.Time      = " + new SimpleDateFormat("HH:mm:ss").format(new java.sql.Time(long_now)));
    System.out.println("��e����M�ɶ��� java.util.Date     = " + new java.util.Date());

	}

}
