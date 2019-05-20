package logic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class PaymentsManager {

    private static String myCurrency = "PLN";

    private PastPaymentRepository pastPaymentRepository;
    private FuturePaymentRepository futurePaymentRepository;
    private Serializer serializer;
    private CurrencyExchanger currencyExchanger;

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");

    private static float roundFloat(float value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Float.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_EVEN);
        return bd.floatValue();
    }

    public PaymentsManager() {
        pastPaymentRepository = new PastPaymentRepository();
        futurePaymentRepository = new FuturePaymentRepository();
        serializer = new Serializer();
        currencyExchanger = new CurrencyExchanger();
    }

    public String getMyCurrency()
    {
        return myCurrency;
    }

    public PastPaymentRepository getPastPaymentRepository()
    {
        return pastPaymentRepository;
    }

    public FuturePaymentRepository getFuturePaymentRepository()
    {
        return futurePaymentRepository;
    }


    public FuturePayment getSpecificFuturePayment(int ID)
    {
        FuturePayment futurePayment = null;

        for(FuturePayment payment: futurePaymentRepository.getFuturePayments())
        {
            if(payment.getID() == ID)
            {
                futurePayment = payment;
            }
        }

        return futurePayment;
    }

    public PastPayment getSpecificPastPayment(int ID)
    {
        PastPayment pastPayment = null;

        for(PastPayment payment: pastPaymentRepository.getPastPayments())
        {
            if(payment.getID() == ID)
            {
                pastPayment = payment;
            }
        }

        return pastPayment;
    }


    public float getChosenCurrencyRate(String currencyCode)
    {
        float rate = CurrencyExchanger.getCurrencyRate(currencyCode);
        float current = CurrencyExchanger.getCurrencyRate(myCurrency);

        return rate/current;
    }

    public void setPastPaymentRepository(PastPaymentRepository pastPaymentRepository) { this.pastPaymentRepository = pastPaymentRepository; }

    public void setFuturePaymentRepository(FuturePaymentRepository futurePaymentRepository) { this.futurePaymentRepository = futurePaymentRepository; }


    public void addToPastPaymentRepository(PastPayment pastPayment) { pastPaymentRepository.addToRepository(pastPayment); }

    public void addToFuturePaymentRepository(FuturePayment futurePayment) { futurePaymentRepository.addToRepository(futurePayment); }


    public void serializePastPaymentRepository()
    {
       serializer.serializePast(pastPaymentRepository);
    }

    public void deserializerPastPaymentRepository()
    {
        this.pastPaymentRepository = serializer.deserializePast();
    }

    public void serializerFuturePaymentRepository(){ serializer.serializeFuture(futurePaymentRepository); }

    public void deserializeFuturePaymentRepository()
    {
        this.futurePaymentRepository = serializer.deserializeFuture();
    }

    public  void deserializeFromDB(){
        FuturePayment fp = new FuturePayment();
        for(int i=0;i<fp.allEntries().size();i++){
            this.futurePaymentRepository.addToRepository(fp.getFuturePayment(fp.allEntries().get(i)));
        }
    }

    public  void deserializePastFromDB(){
        PastPayment fp = new PastPayment();
        for(int i=0;i<fp.allEntries().size();i++){
            this.pastPaymentRepository.addToRepository(fp.getPastPayment(fp.allEntries().get(i)));
        }
    }

    public void serializeAll()
    {
        serializer.serializeAll(pastPaymentRepository, futurePaymentRepository);
    }

    public void convertEveryPrice(String currencyCode)
    {
        float currentRate  = currencyExchanger.getCurrencyRate(currencyCode)/
                          currencyExchanger.getCurrencyRate("PLN");

        pastPaymentRepository.getPastPayments().forEach((pastPayment) -> pastPayment.setPriceInDifferentCurrency(roundFloat(currentRate, 2)));
        futurePaymentRepository.getFuturePayments().forEach((futurePayment) -> futurePayment.setPriceInDifferentCurrency(roundFloat(currentRate, 2)));

        this.myCurrency = currencyCode;
    }
}
