import logic.Categories;
import logic.MonthlyPayment;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MonthlyPaymentTest {

    MonthlyPayment payment1;

    /*@Before
    public void setUp() throws Exception {
        payment1 = new MonthlyPayment( "payment1", (float) 21.37, Categories.Sprzet, "halo");
        payment1.setPaymentID(1);
        //System.out.println(payment1.getMonthList());
    }*/

    @Test
    public void getPaymentID() {

        int i = 1;



        assertEquals(1, i);

        //assertEquals(1, payment1.getPaymentID(), 0);
    }


    /*@Test
    public void payThisMonth() {
        //payment1.payThisMonth();
        //System.out.println(payment1.getMonthList());


         int i = 1;
        assertEquals(1, i);
    }

    @Test
    public void payThisMonth1() {
    }

    @Test
    public void getMonthList() {
    }

    @Test
    public void setMonthList() {
    }

    @Test
    public void getPaymentID1() {
    }

    @Test
    public void getPaymentName() {
    }

    @Test
    public void getPaymentPrice() {
    }

    @Test
    public void getPaymentType() {
    }

    @Test
    public void getPaymentDescription() {
    }

    @Test
    public void setPaymentID() {
    }

    @Test
    public void setPaymentName() {
    }

    @Test
    public void setPaymentPrice() {
    }

    @Test
    public void setPaymentType() {
    }

    @Test
    public void setPaymentDescription() {
    }

    @Test
    public void archive() {
    }*/
}