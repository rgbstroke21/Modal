import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        String dateString = "27Feb2024";
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyyyy");
        try {
            Date date = sdf.parse(dateString);
            long epochTime = date.getTime();
            System.out.println("Epoch time for " + dateString + " is: " + epochTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
