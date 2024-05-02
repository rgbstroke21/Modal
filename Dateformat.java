import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class StringToEpochTime {
    public static void main(String[] args) throws Exception {
        String dateString = "2024-05-02T12:30:45:123Z";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSSZ");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = dateFormat.parse(dateString.replaceAll("(\\.\\d+)Z", "Z")); // Correcting milliseconds format
        long epochTime = date.getTime();
        System.out.println("Epoch Time: " + epochTime);
    }
}
