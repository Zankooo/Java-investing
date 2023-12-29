import java.math.BigDecimal;
import java.text.NumberFormat;

public class Proba {
    public static void main(String[] args) {
        double znesek = 1000000.444;
        BigDecimal bd = new BigDecimal(znesek);

        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        String rezultatt = numberFormat.format(znesek);
        System.out.println(rezultatt);
    }
}
