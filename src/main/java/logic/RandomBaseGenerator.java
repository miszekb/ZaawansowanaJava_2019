package logic;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

public class RandomBaseGenerator {

    protected Categories[] categories = Categories.values();
    protected Random random = new Random();

    public Categories getRandomCategory()
    {
        return categories[random.nextInt(categories.length)];
    }

    public static void main(String[] args) {

        PastPaymentRepository pastPaymentRepository = new PastPaymentRepository();

        Short category;
        Random random = new Random();
        Date currentDate = new Date();

        for(int i=0; i<50; i++) {

            category = (short)(random.nextInt( 9)+1);
        }

        for(PastPayment pastPayment : pastPaymentRepository.getPastPayments())
        {
            System.out.println(pastPayment.toString());
        }
    }

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    public Date generateRandomDate(Date currentDate, int random)
    {
        System.out.println(random);
        Date date = new Date(currentDate.getYear(),currentDate.getMonth(),random);

        return date;
    }
}
