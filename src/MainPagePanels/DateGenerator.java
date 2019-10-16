package MainPagePanels;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateGenerator {
    /**
     * Only has a static method which returns current date
     * in desired format!
     * @return current date
     */
    public static String generate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return formatter.format(date);
    }
}
