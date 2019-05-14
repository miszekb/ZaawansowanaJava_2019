package logic;

import org.json.JSONException;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class MainClass {

    //tiny example to test Travis continuous integration


    public static void main(String[] args) throws IOException, JSONException
    {
        Date date = new Date(2019,3,12);
        PastPayment pastPayment = new PastPayment("pszne obiad",21.37f,Categories.Transport, "bardzo pszne", date);
        PastPaymentRepository pastPaymentRepository = new PastPaymentRepository();
        pastPaymentRepository.AddToRepo(pastPayment);

        float rate = CurrencyExchanger.GetCurrencyRate("USD");
        float rate2 = CurrencyExchanger.GetCurrencyRateOnDate("USD", "2016-01-15");
        System.out.println(rate);
        System.out.println(rate2);
    }


}
