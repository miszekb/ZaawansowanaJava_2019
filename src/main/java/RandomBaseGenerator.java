import java.math.BigDecimal;
import java.util.*;

enum Categories {               //number equivalent
    Ubrania_Obuwie,             //1
    Rachunki,                   //2
    Zywnosc,                    //3
    Uzywki,                     //4
    Chemia_SrodkiCzystosci,     //5
    Transport,                  //6
    Rozrywka,                   //7
    Sprzet,                     //8
    Kosmetyki                   //9
}

public class RandomBaseGenerator {

    protected Categories[] categories = Categories.values();
    protected Random random = new Random();


    public Categories getRandomCategory()
    {
        return categories[random.nextInt(categories.length)];
    }

    public Date generateRandomDate(Date currentDate, int random)
    {
        System.out.println(random);
        Date date = new Date(currentDate.getYear(),currentDate.getMonth(),random);

        return date;
    }

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    public static void main(String[] args) {

        PastPaymentRepository pastPayments = new PastPaymentRepository();

        Short category;
        Random random = new Random();
        Date currentDate = new Date();

        for(int i=0; i<50; i++) {

            category = (short)(random.nextInt( 9)+1);
            pastPayments.AddToRepo(new PastPayment(i + 1, "payment" + String.valueOf(i + 1), round((float) Math.random() * 100, 2), category, "...", new Date(currentDate.getYear(),currentDate.getMonth(),random.nextInt( 7)+1)));
        }

        for(PastPayment pastPayment : pastPayments.getRepo())
        {
            System.out.println(pastPayment.toString());
        }

    }

}
