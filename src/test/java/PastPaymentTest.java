import logic.Categories;
import logic.PastPayment;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class PastPaymentTest {

    private PastPayment pastPayment;

    @Before
    public void setUp() {
        Date date = new Date(2019,3,12);
        this.pastPayment = new PastPayment(1,"pszne obiad",21.37f, Categories.Transport, "bardzo pszne", date);
    }

    @Test
    public void toString_TEST() {
        assertEquals("1,pszne obiad,21.37,Transport,bardzo pszne,04/12/3919 00:00:00", pastPayment.toString());
    }

    @Test
    public void getPaymentID() {
        assertEquals(1, pastPayment.getPaymentID());
    }

    @Test
    public void getPaymentName() {
        assertEquals("pszne obiad", pastPayment.getPaymentName());
    }

    @Test
    public void getPaymentPrice() {
        assertEquals(21.37f, pastPayment.getPaymentPrice(),0.0f);
    }

    @Test
    public void getPaymentType() {
        assertEquals("Transport", pastPayment.getPaymentType());
    }

    @Test
    public void getPaymentDescription() {
        assertEquals("bardzo pszne", pastPayment.getPaymentDescription());
    }

    @Test
    public void setPaymentID() {
        pastPayment.setPaymentID(2);
        assertEquals(2, pastPayment.getPaymentID());
    }

    @Test
    public void setPaymentName() {
        pastPayment.setPaymentName("NewName");
        assertEquals("NewName", pastPayment.getPaymentName());
    }

    @Test
    public void setPaymentPrice() {
        pastPayment.setPaymentPrice(14.10f);
        assertEquals(14.10f, pastPayment.getPaymentPrice(), 7.27f);
    }

    @Test
    public void setPaymentType() {
        pastPayment.setPaymentType(Categories.Sprzet);
        assertEquals("Sprzet", pastPayment.getPaymentType());
    }

    @Test
    public void setPaymentDescription() {
        pastPayment.setPaymentDescription("NewDescription");
        assertEquals("NewDescription", pastPayment.getPaymentDescription());
    }

    @Test
    public void getPaymentDate() {
        assertEquals( new Date(2019,3,12), pastPayment.getPaymentDate());
    }

    @Test
    public void setPaymentDate() {
        Date date2 = new Date(2008,3,12);
        pastPayment.setPaymentDate(date2);
        assertEquals(new Date(2008,3,12), pastPayment.getPaymentDate());
    }
}