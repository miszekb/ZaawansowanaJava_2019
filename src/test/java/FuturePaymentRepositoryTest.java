import logic.Categories;
import logic.FuturePayment;
import logic.FuturePaymentRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FuturePaymentRepositoryTest {

    private FuturePaymentRepository futureRepo;
    private FuturePayment futurePayment;
    private FuturePayment futurePayment2;

    @Before
    public void setUp() {
        this.futurePayment = new FuturePayment("pszne obiad",21.37f, Categories.Kosmetyki, "bardzo pszne");
        this.futurePayment2 = new FuturePayment("pszne obiad2",14.10f,Categories.Sprzet, "bardzo pszne2");
        futurePayment.setID(1);
        futurePayment2.setID(2);
        this.futureRepo = new FuturePaymentRepository();
        futureRepo.addToRepository(futurePayment);
        futureRepo.addToRepository(futurePayment2);
    }

    @Test
    public void toString_TEST() {
        assertEquals("1,pszne obiad,21.37,Kosmetyki,bardzo pszne\n" +
                "2,pszne obiad2,14.1,Sprzet,bardzo pszne2\n", futureRepo.toString());
    }

    @Test
    public void addToRepository() {
        assertEquals("1,pszne obiad,21.37,Kosmetyki,bardzo pszne\n" +
                "2,pszne obiad2,14.1,Sprzet,bardzo pszne2\n", futureRepo.toString());
    }

    @Test
    public void deletePayment() {
        futureRepo.deletePayment(2);
        assertEquals(1, futureRepo.getFuturePayments().size());
    }

    @Test
    public void getFuturePayments() {
        assertEquals(futurePayment, futureRepo.getFuturePayments().get(0));
        assertEquals(futurePayment2, futureRepo.getFuturePayments().get(1));
    }
}