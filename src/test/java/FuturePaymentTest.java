import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class FuturePaymentTest {

    private FuturePayment futurePayment;

    @Before
    public void setUp() {
        Date date = new Date(2019,3,12);
        this.futurePayment = new FuturePayment(1,"pszne obiad",21.37f,(short)2, "bardzo pszne");
    }

    @Test
    public void toString_TEST() {
        assertEquals("1,pszne obiad,21.37,2,bardzo pszne", futurePayment.toString());
    }

    @Test
    public void getPaymentID() {
        assertEquals(1, futurePayment.getPaymentID());
    }

    @Test
    public void getPaymentName() {
        assertEquals("pszne obiad", futurePayment.getPaymentName());
    }

    @Test
    public void getPaymentPrice() {
        assertEquals(21.37f, futurePayment.getPaymentPrice(), 0.0f);
    }

    @Test
    public void getPaymentType() {
        assertEquals((short)2, futurePayment.getPaymentType());
    }

    @Test
    public void getPaymentDescription() {
        assertEquals("bardzo pszne", futurePayment.getPaymentDescription());
    }

    @Test
    public void setPaymentID() {
        futurePayment.setPaymentID(2);
        assertEquals(2, futurePayment.getPaymentID());
    }

    @Test
    public void setPaymentName() {
        futurePayment.setPaymentID(2);
        assertEquals(2, futurePayment.getPaymentID());
    }

    @Test
    public void setPaymentPrice() {
        futurePayment.setPaymentPrice(14.10f);
        assertEquals(14.10f, futurePayment.getPaymentPrice(), 7.27f);
    }

    @Test
    public void setPaymentType() {
        futurePayment.setPaymentType((short)3);
        assertEquals((short)3, futurePayment.getPaymentType());
    }

    @Test
    public void setPaymentDescription() {
        futurePayment.setPaymentDescription("NewDescription");
        assertEquals("NewDescription", futurePayment.getPaymentDescription());
    }
}