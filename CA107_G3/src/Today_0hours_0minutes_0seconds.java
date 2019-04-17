import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

public class Today_0hours_0minutes_0seconds {

	public static void main(String[] args) throws ParseException {

		/* Java8 */
		LocalDate localDate = LocalDateTime.now().toLocalDate(); // localDate = 2017-03-10 (��e���)
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = df.parse(String.valueOf(localDate));

		/* Java7 */
		// Calendar cal = Calendar.getInstance(); //��e����M�ɶ�
		// cal.set(Calendar.HOUR_OF_DAY, 0 );     //��e�p�ɳ]����
		// cal.set(Calendar.MINUTE, 0 );          //��e�����]����
		// cal.set(Calendar.SECOND, 0 );          //��e��Ƴ]����
		// cal.set(Calendar.MILLISECOND, 0 );     //��e�@��]����
		// java.util.Date date = cal.getTime();

		System.out.println("��� 0�� 0�� 0�� �� java.util.Date = " + date);
		System.out.println("��� 0�� 0�� 0�� �� �@��� = " + date.getTime());
		System.out.println("��� 0�� 0�� 0�� �� java.sql.Date = " + new java.sql.Date(date.getTime()));
		
		System.out.println("��� 0�� 0�� 0�� �� �@��� = " + java.sql.Date.valueOf(LocalDate.now()).getTime());

	}

}
