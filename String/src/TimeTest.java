
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: String->TimeTest
 * @author: gakki
 * @create: 2019-07-17 13:24
 **/
public class TimeTest {
    public static void main(String[] args) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = new Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        System.out.println(sqlDate);
        java.sql.Time sTime = new java.sql.Time(utilDate.getTime());
        java.sql.Timestamp stp = new java.sql.Timestamp(utilDate.getTime());
        System.out.println(utilDate.getYear());
    }
}
