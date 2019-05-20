import logic.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class PaymentsManagerTest {

    private PaymentsManager paymentsManager;
    private Date date;

    @Before
    public void setUp(){
        paymentsManager = new PaymentsManager();

        date = new Date(2019,3,12);
        Date date2 = new Date(2018, 4, 10);
        PastPayment pastPayment = new PastPayment("pszne obiad",21.37f, Categories.Kosmetyki, "bardzo pszne", date);
        PastPayment pastPayment2 = new PastPayment("pszne obiad2",14.10f,Categories.Sprzet, "bardzo pszne2", date2);
        pastPayment.setID(1);
        pastPayment2.setID(2);
        paymentsManager.addToPastPaymentRepository(pastPayment);
        paymentsManager.addToPastPaymentRepository(pastPayment2);

        FuturePayment futurePayment = new FuturePayment("pszne obiad",21.37f, Categories.Kosmetyki, "bardzo pszne");
        FuturePayment futurePayment1 = new FuturePayment("pszne obiad2",14.10f,Categories.Sprzet, "bardzo pszne2");
        futurePayment.setID(1);
        futurePayment.setID(2);
        paymentsManager.addToFuturePaymentRepository(futurePayment);
        paymentsManager.addToFuturePaymentRepository(futurePayment1);

    }

    @Test
    public void getMyCurrency() {
        assertEquals("PLN", paymentsManager.getMyCurrency());
    }

    @Test
    public void addToPastRepo() {
        PastPayment pp = new PastPayment("pszne obiad3",14.10f,Categories.Sprzet, "bardzo pszne2", new Date());
        pp.setID(3);
        paymentsManager.addToPastPaymentRepository(pp);

        assertEquals(3, paymentsManager.getPastPaymentRepository().getPastPayments().size());
    }

    @Test
    public void getPastRepo() {
        assertEquals(2, paymentsManager.getPastPaymentRepository().getPastPayments().size());
    }

    @Test
    public void setPastRepo() {
        PastPaymentRepository pastPaymentRepository = new PastPaymentRepository();
        PastPayment pastPayment = new PastPayment("pszne obiad",21.37f, Categories.Kosmetyki, "bardzo pszne", new Date());
        pastPayment.setID(1);
        pastPaymentRepository.addToRepository(pastPayment);

        paymentsManager.setPastPaymentRepository(pastPaymentRepository);
        assertEquals(1, paymentsManager.getPastPaymentRepository().getPastPayments().size());
    }

    @Test
    public void getSpecificPastPayment() {
        PastPayment pastPayment = new PastPayment("pszne obiad",21.37f, Categories.Kosmetyki, "bardzo pszne", this.date);
        pastPayment.setID(1);
        assertEquals(pastPayment.toString(), paymentsManager.getSpecificPastPayment(1).toString());
    }

    @Test
    public void addToFutureRepo() {
        FuturePayment futurePayment = new FuturePayment("pszne obiad3",21.37f, Categories.Kosmetyki, "bardzo pszne");
        futurePayment.setID(3);
        paymentsManager.addToFuturePaymentRepository(futurePayment);
        assertEquals(3, paymentsManager.getFuturePaymentRepository().getFuturePayments().size());
    }

    @Test
    public void getFutureRepo() {
        assertEquals(2, paymentsManager.getFuturePaymentRepository().getFuturePayments().size());
    }

    @Test
    public void setFutureRepo() {
        FuturePaymentRepository futurePaymentRepository = new FuturePaymentRepository();
        FuturePayment futurePayment = new FuturePayment("pszne obiad",21.37f, Categories.Kosmetyki, "bardzo pszne");
        futurePayment.setID(1);
        FuturePayment futurePayment1 = new FuturePayment("pszne obiad2",14.10f,Categories.Sprzet, "bardzo pszne2");
        futurePayment1.setID(2);
        futurePaymentRepository.addToRepository(futurePayment);
        futurePaymentRepository.addToRepository(futurePayment1);

        paymentsManager.setFuturePaymentRepository(futurePaymentRepository);
        assertEquals(2, paymentsManager.getFuturePaymentRepository().getFuturePayments().size());
    }

    @Test
    public void getSpecificFuturePayment() {
        FuturePayment futurePayment = new FuturePayment("pszne obiad",21.37f, Categories.Kosmetyki, "bardzo pszne");
        futurePayment.setID(15);
        paymentsManager.addToFuturePaymentRepository(futurePayment);
        assertEquals(futurePayment.toString(), paymentsManager.getSpecificFuturePayment(15).toString());
    }

    @Test
    public void convertEveryPrice() {
        paymentsManager.convertEveryPrice("USD");
        float getCurrency = 0;
        for(FuturePayment payment: paymentsManager.getFuturePaymentRepository().getFuturePayments())
        {
            getCurrency = payment.getPriceInDifferentCurrency();
        }
        boolean checkCurrency =  (getCurrency > 0);
        assertEquals(true, checkCurrency);
    }

    @Test
    public void getChosenCurrencyRate() {
        float rateUSD = paymentsManager.getChosenCurrencyRate("USD");
        assertNotEquals(null, rateUSD);
    }
}