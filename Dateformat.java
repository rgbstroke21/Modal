import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) {
        String timestamp = "2023-10-03T14:30Z";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        
        try {
            Date date = sdf.parse(timestamp);
            long epochTime = date.getTime() / 1000; // Convert milliseconds to seconds
            System.out.println("Epoch time: " + epochTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
