import logic.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class PaymentsManagerTest {

    private PaymentsManager paymentsManager;
    private Date date;


    @Before
    public void setUp() throws Exception {
        paymentsManager = new PaymentsManager();

        date = new Date(2019,3,12);
        Date date2 = new Date(2018, 4, 10);
        PastPayment pastPayment = new PastPayment(1,"pszne obiad",21.37f, Categories.Kosmetyki, "bardzo pszne", date);
        PastPayment pastPayment2 = new PastPayment(2,"pszne obiad2",14.10f,Categories.Sprzet, "bardzo pszne2", date2);
        paymentsManager.addToPastRepo(pastPayment);
        paymentsManager.addToPastRepo(pastPayment2);

        FuturePayment futurePayment = new FuturePayment(1,"pszne obiad",21.37f, Categories.Kosmetyki, "bardzo pszne");
        FuturePayment futurePayment1 = new FuturePayment(2,"pszne obiad2",14.10f,Categories.Sprzet, "bardzo pszne2");
        paymentsManager.addToFutureRepo(futurePayment);
        paymentsManager.addToFutureRepo(futurePayment1);

    }

    @Test
    public void getMyCurrency() {
        assertEquals("PLN", paymentsManager.getMyCurrency());
    }

    @Test
    public void addToPastRepo() {
        paymentsManager.addToPastRepo(new PastPayment(3,"pszne obiad3",14.10f,Categories.Sprzet, "bardzo pszne2", new Date()));
        assertEquals(3, paymentsManager.getPastRepo().getRepo().size());
    }

    @Test
    public void getPastRepo() {
        assertEquals(2, paymentsManager.getPastRepo().getRepo().size());
    }

    @Test
    public void setPastRepo() {
        PastPaymentRepository pastPaymentRepository = new PastPaymentRepository();
        PastPayment pastPayment = new PastPayment(1,"pszne obiad",21.37f, Categories.Kosmetyki, "bardzo pszne", new Date());
        pastPaymentRepository.AddToRepo(pastPayment);

        paymentsManager.setPastRepo(pastPaymentRepository);
        assertEquals(1, paymentsManager.getPastRepo().getRepo().size());
    }

    @Test
    public void getSpecificPastPayment() {
        PastPayment pastPayment = new PastPayment(1,"pszne obiad",21.37f, Categories.Kosmetyki, "bardzo pszne", this.date);
        assertEquals(pastPayment.toString(), paymentsManager.getSpecificPastPayment(1).toString());
    }

    @Test
    public void addToFutureRepo() {
        FuturePayment futurePayment = new FuturePayment(3,"pszne obiad3",21.37f, Categories.Kosmetyki, "bardzo pszne");
        paymentsManager.addToFutureRepo(futurePayment);
        assertEquals(3, paymentsManager.getFutureRepo().getRepo().size());
    }

    @Test
    public void getFutureRepo() {
        assertEquals(2, paymentsManager.getFutureRepo().getRepo().size());
    }

    @Test
    public void setFutureRepo() {
        FuturePaymentRepository futurePaymentRepository = new FuturePaymentRepository();
        FuturePayment futurePayment = new FuturePayment(1,"pszne obiad",21.37f, Categories.Kosmetyki, "bardzo pszne");
        FuturePayment futurePayment1 = new FuturePayment(2,"pszne obiad2",14.10f,Categories.Sprzet, "bardzo pszne2");
        futurePaymentRepository.AddToRepo(futurePayment);
        futurePaymentRepository.AddToRepo(futurePayment1);

        paymentsManager.setFutureRepo(futurePaymentRepository);
        assertEquals(2, paymentsManager.getFutureRepo().getRepo().size());
    }

    @Test
    public void getSpecificFuturePayment() {
        FuturePayment futurePayment = new FuturePayment(1,"pszne obiad",21.37f, Categories.Kosmetyki, "bardzo pszne");
        assertEquals(futurePayment.toString(), paymentsManager.getSpecificFuturePayment(1).toString());
    }

    // TODO: tests for monthly payments
    @Test
    public void addToMonthlyRepo() {
    }

    @Test
    public void getMonthlyRepo() {
    }

    @Test
    public void setMonthlyRepo() {
    }

    @Test
    public void getSpecificMonthlyPayment() {
    }

    @Test
    public void serializePast() {
        paymentsManager.serializePast(paymentsManager.getPastRepo());
        paymentsManager.deserializerPast();
        assertEquals(2, paymentsManager.getPastRepo().getRepo().size());
    }

    @Test
    public void deserializerPast() {
        paymentsManager.serializePast(paymentsManager.getPastRepo());
        paymentsManager.deserializerPast();
        assertEquals(2, paymentsManager.getPastRepo().getRepo().size());
    }

    @Test
    public void serializerFuture() {
        paymentsManager.serializerFuture(paymentsManager.getFutureRepo());
        paymentsManager.deserializeFuture();
        assertEquals(2, paymentsManager.getFutureRepo().getRepo().size());
    }

    @Test
    public void deserializeFuture() {
        paymentsManager.serializerFuture(paymentsManager.getFutureRepo());
        paymentsManager.deserializeFuture();
        assertEquals(2, paymentsManager.getFutureRepo().getRepo().size());
    }

    @Test
    public void serializeAll() {
    }

    @Test
    public void convertEveryPrice() {
        //TODO:currently have not good idea how to test that
    }

    @Test
    public void getChosenCurrencyRate() {
        float rateUSD = paymentsManager.getChosenCurrencyRate("USD");
        assertNotEquals(null, rateUSD);
    }


}