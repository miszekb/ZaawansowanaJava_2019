import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FuturePaymentRepositoryTest {

    private FuturePaymentRepository futureRepo;
    private FuturePayment futurePayment;
    private FuturePayment futurePayment2;

    @Before
    public void setUp() {
        this.futurePayment = new FuturePayment(1,"pszne obiad",21.37f,(short)2, "bardzo pszne");
        this.futurePayment2 = new FuturePayment(2,"pszne obiad2",14.10f,(short)3, "bardzo pszne2");
        this.futureRepo = new FuturePaymentRepository();
        futureRepo.AddToRepo(futurePayment);
        futureRepo.AddToRepo(futurePayment2);
    }

    @Test
    public void toString_TEST() {
        assertEquals("1,pszne obiad,21.37,2,bardzo pszne\n" +
                "2,pszne obiad2,14.1,3,bardzo pszne2\n", futureRepo.toString());
    }

    @Test
    public void addToRepo() {
        assertEquals("1,pszne obiad,21.37,2,bardzo pszne\n" +
                "2,pszne obiad2,14.1,3,bardzo pszne2\n", futureRepo.toString());
    }

    @Test
    public void deletePayment() {
        futureRepo.DeletePayment(2);
        assertEquals(1, futureRepo.getRepo().size());
    }

    @Test
    public void getRepo() {
        assertEquals(futurePayment, futureRepo.getRepo().get(0));
        assertEquals(futurePayment2, futureRepo.getRepo().get(1));

    }
}