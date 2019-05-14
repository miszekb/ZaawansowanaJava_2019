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
        futureRepo.AddToRepo(futurePayment);
        futureRepo.AddToRepo(futurePayment2);
    }

    @Test
    public void toString_TEST() {
        assertEquals("1,pszne obiad,21.37,Kosmetyki,bardzo pszne\n" +
                "2,pszne obiad2,14.1,Sprzet,bardzo pszne2\n", futureRepo.toString());
    }

    @Test
    public void addToRepo() {
        assertEquals("1,pszne obiad,21.37,Kosmetyki,bardzo pszne\n" +
                "2,pszne obiad2,14.1,Sprzet,bardzo pszne2\n", futureRepo.toString());
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