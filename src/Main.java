import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Vpišite zacetno investicijo v kešu? ");
            double znesek = sc.nextDouble();

            System.out.println("Koliko procentualno donosa boste recimo naredili? (povprečje ameriškega indeksa sp&500 je 10%)");
            int obrestna_mera = sc.nextInt();
            float realna_ob_mera = ((float) obrestna_mera / 100) + 1;

            System.out.println("Koliko let boste držali keš notri? ");
            int leta = sc.nextInt();

            System.out.println("Preko aktivno vodenih skladov; koliko vam zaračunajo vseh letnih stroškov skupaj? (ponavadi je 2% lahko je tudi več)");
            float letna_provizija = sc.nextFloat();

            System.out.println("------------------------------------------------");
            System.out.println("------------------------------------------------");

            //zalaufamo funkciji in se to, kar returnata shranimo v variable, da uporabimo v novi funkciji.
            double navadno = navadno_investiranje(znesek, realna_ob_mera, leta, obrestna_mera);
            double skladi = precudoviti_skladi(znesek, realna_ob_mera, leta, obrestna_mera, letna_provizija);
            System.out.println("------------------------------------------------");
            razlika(skladi,navadno,letna_provizija);
            System.out.println("------------------------------------------------");

        } catch (Exception e) {
            System.out.println("Napaka pri vnosu podatkov. Preverite vnos in poskusite znova.");
        }
    }
    //-------------------------------------
    public static double navadno_investiranje(double znesek, float realna_ob_mera, int leta, int obrestna_mera) {

        double zacetna_investicijaa = znesek;
        for (int i = 0; i < leta; i++) {
            znesek = znesek * realna_ob_mera;
        }
        //ta koda loci po pikah in vecijah tisocice.
        //rezultat
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        String rezultatt = numberFormat.format(znesek);
        //zacetno investicijo

        String zacetna_investicijaaa = numberFormat.format(zacetna_investicijaa);

        System.out.println("Navadno investiranje --> Po " + leta + " letih imate z začetno investicijo " + zacetna_investicijaaa + "eur in donosom " + obrestna_mera + "% toliko keša: " + rezultatt + " eur");
        return znesek;
    }
    //-------------------------------------

    public static double precudoviti_skladi(double znesek, float realna_ob_mera, int leta, int obrestna_mera, float letne_provizije) {
        double zacetna_investicijaa = znesek;
        float provizija_v_cifri = letne_provizije / 100; // ce je input 2% je pol 0.02
        for (int i = 0; i < leta; i++) {
            znesek = (znesek * realna_ob_mera) * (1 - provizija_v_cifri); // 10 000 * 1.1 = 11 000 * 0.98 =
        }

        //ta koda loci po pikah in vecijah tisocice
        //rezultat
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        String rezultatt = numberFormat.format(znesek);
        //zacetno investicijo
        String zacetna_investicijaaa = numberFormat.format(zacetna_investicijaa);

        System.out.println("Investiranje v precudovite aktivno vodene sklade --> Po " + leta + " letih imate z začetno investicijo " + zacetna_investicijaaa + "eur in donosom " + obrestna_mera + "% ter letnimi provizijami " + letne_provizije + "% toliko keša: " + rezultatt + " eur");
        return znesek;
    }

    //-------------------------------------

    public static void razlika(double skladi, double navadno, float provizije){
        double razlika = (navadno - skladi);
        //ta koda loci po pikah in vecijah tisocice
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        String rezultatt = numberFormat.format(razlika);

        //9.611921763427507
        double procentualno = ((razlika / navadno) * 100);

        //ta koda: Kaj; zaokrozi na dve decimalki cifri. Kako: nevem
        BigDecimal bd = new BigDecimal(procentualno);
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        double roundedNumber = bd.doubleValue();

        System.out.println("In razlika med navadnim investiranjem in precudovitimi skladi je --> " + rezultatt + " eur, kar procentualno ni " + provizije + "% kot vam oni to prikazejo, ampak je " + roundedNumber + "% !");
        System.out.println("Pa v izračun sploh nismo upoštevali še vstopnih in izstopnih stroškov in še vseh drugih prevar.");
    }

    //-------------------------------------

}
