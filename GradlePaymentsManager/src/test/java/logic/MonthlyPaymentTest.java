package logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonthlyPaymentTest {

    MonthlyPayment payment1;

    @Before
    public void setUp() throws Exception {
        payment1 = new MonthlyPayment(1, "payment1", (float) 21.37, Categories.Sprzet, "halo");
        //System.out.println(payment1.getMonthList());
    }

    @Test
    public void getPaymentID() {
        assertEquals(1, payment1.getPaymentID(), 0);
    }


    @Test
    public void payThisMonth() {
        payment1.payThisMonth();
        System.out.println(payment1.getMonthList());

    }
}