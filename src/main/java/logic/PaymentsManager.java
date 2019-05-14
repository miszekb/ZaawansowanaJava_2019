package logic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.Date;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class PaymentsManager {

    private PastPaymentRepository pastRepo;
    private FuturePaymentRepository futureRepo;
    private MonthlyPaymentRepository monthlyRepo;
    private Serializer serializer;
    private CurrencyExchanger currencyExchanger;

    private static String myCurrency = "PLN";

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");

    public PaymentsManager() {
        pastRepo = new PastPaymentRepository();
        futureRepo = new FuturePaymentRepository();
        monthlyRepo = new MonthlyPaymentRepository();
        serializer = new Serializer();
        currencyExchanger = new CurrencyExchanger();
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public String getMyCurrency()
    {
        return myCurrency;
    }

    //---------------------- PAST PAYMENTS METHODS ----------------------

    public void addToPastRepo(PastPayment pastPayment)
    {
        pastRepo.AddToRepo(pastPayment);
    }


    public PastPaymentRepository getPastRepo()
    {
        return pastRepo;
    }

    public void setPastRepo(PastPaymentRepository pastRepo)
    {
        this.pastRepo = pastRepo;
    }


    public PastPayment getSpecificPastPayment(int ID)
    {
        PastPayment pastPayment = null;

        for(PastPayment payment: pastRepo.getRepo())
        {
            if(payment.getID() == ID)
            {
                pastPayment = payment;
            }
        }

        return pastPayment;
    }

    //---------------------- FUTURE PAYMENTS METHODS ----------------------
    public void addToFutureRepo(FuturePayment futurePayment)
    {
        futureRepo.AddToRepo(futurePayment);
    }

    public FuturePaymentRepository getFutureRepo()
    {
        return futureRepo;
    }

    public void setFutureRepo(FuturePaymentRepository futureRepo)
    {
        this.futureRepo = futureRepo;
    }


    public FuturePayment getSpecificFuturePayment(int ID)
    {
        FuturePayment futurePayment = null;

        for(FuturePayment payment: futureRepo.getRepo())
        {
            if(payment.getID() == ID)
            {
                futurePayment = payment;
            }
        }

        return futurePayment;
    }
    //---------------------- MONTHLY PAYMENTS METHODS ----------------------

    public void addToMonthlyRepo(MonthlyPayment monthlyPayment)
    {
        monthlyRepo.AddToRepo(monthlyPayment);
    }

    public MonthlyPaymentRepository getMonthlyRepo()
    {
        return monthlyRepo;
    }

    public void setMonthlyRepo(MonthlyPaymentRepository monthlyRepo)
    {
        this.monthlyRepo = monthlyRepo;
    }

    public MonthlyPayment getSpecificMonthlyPayment(int ID)
    {
        MonthlyPayment monthlyPayment = null;

        for(MonthlyPayment payment: monthlyRepo.getRepo())
        {
            if(payment.getPaymentID() == ID)
            {
                monthlyPayment = payment;
            }
        }

        return monthlyPayment;
    }

    public void update() {
        for(MonthlyPayment payment: monthlyRepo.getRepo()) {
            if (!payment.getMonthList().get(new Date().getMonth() + 1).booleanValue()) {
                futureRepo.AddToRepo(new FuturePayment(payment.getPaymentName(), payment.getPaymentPrice(),
                        Categories.valueOf(payment.getPaymentType()),
                        payment.getPaymentDescription()));
            }

        }
    }


    //---------------------- XML SERIALIZING METHODS ----------------------

    public void serializePast(PastPaymentRepository repo)
    {
       serializer.SerializePast(repo);
    }

    public void deserializerPast()
    {
        this.pastRepo = serializer.DeserializePast();
    }

    public void serializerFuture(FuturePaymentRepository repo)
    {
        this.futureRepo = serializer.DeserializeFuture();
    }

    public void deserializeFuture()
    {
        this.futureRepo = serializer.DeserializeFuture();
    }

    public void serializeAll()
    {
        serializer.SerializeAll(pastRepo, futureRepo, monthlyRepo);
    }

    // ----------------------SQL DATABASE METHODS ----------------------

   //TODO: METHODS FOR DATABASE HANDLING

    //---------------------- CURRENCY API METHODS ----------------------

    public void convertEveryPrice(String currencyCode)
    {
        float currentRate = 0.0f;
        try
        {
            currentRate = currencyExchanger.GetCurrencyRate(currencyCode)/
                          currencyExchanger.GetCurrencyRate("PLN");
        }
        catch(IOException exception)
        {
            System.out.println(exception.getMessage());
        }

        for(PastPayment payment: pastRepo.getRepo())
        {
            payment.setPriceInDifferentCurrency(roundFloat((currentRate), 2));
        }

        for(FuturePayment payment: futureRepo.getRepo())
        {
            payment.setPriceInDifferentCurrency(roundFloat((currentRate), 2));
        }

        for(MonthlyPayment payment: monthlyRepo.getRepo())
        {
            payment.setPriceInDifferentCurrency(roundFloat((currentRate), 2));
        }

        this.myCurrency = currencyCode;
    }

    public float getChosenCurrencyRate(String currencyCode)
    {
        float rate= 0.0f;
        float current = 0.0f;

        try
        {
            rate = CurrencyExchanger.GetCurrencyRate(currencyCode);
            current = CurrencyExchanger.GetCurrencyRate(myCurrency);
        }
        catch (Exception exception)
        {
           System.out.println(exception.getMessage());
        }

        return rate/current;
    }

    private static float roundFloat(float value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Float.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_EVEN);
        return bd.floatValue();
    }


}
