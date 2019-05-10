package logic;

import java.io.IOException;

public class PaymentsManager {

    private PastPaymentRepository pastRepo;
    private FuturePaymentRepository futureRepo;
    private MonthlyPaymentRepository monthlyRepo;
    private Serializer serializer;
    private CurrencyExchanger currencyExchanger;

    private String myCurrency = "PLN";

    public PaymentsManager() {
        pastRepo = new PastPaymentRepository();
        futureRepo = new FuturePaymentRepository();
        monthlyRepo = new MonthlyPaymentRepository();
        serializer = new Serializer();
        currencyExchanger = new CurrencyExchanger();
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

    public void serializeAll(PastPaymentRepository repoPast, FuturePaymentRepository repoFuture)
    {
        serializer.SerializeAll(repoPast, repoFuture);
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
                          currencyExchanger.GetCurrencyRate(myCurrency);
        }
        catch(IOException exception)
        {
            System.out.println(exception.getMessage());
        }

        for(PastPayment payment: pastRepo.getRepo())
        {
            payment.setPrice(payment.getPaymentPrice()*currentRate);
        }

        for(FuturePayment payment: futureRepo.getRepo())
        {
            payment.setPaymentPrice(payment.getPaymentPrice()*currentRate);

        }

        for(MonthlyPayment payment: monthlyRepo.getRepo())
        {
            payment.setPaymentPrice(payment.getPaymentPrice()*currentRate);

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


}
