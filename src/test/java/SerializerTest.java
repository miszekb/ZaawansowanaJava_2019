import logic.*;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class SerializerTest {


    @Test
    public void serializePast() {
        Date date = new Date(2019, 3, 12);
        Date date2 = new Date(2018, 4, 10);
        PastPayment pastPayment = new PastPayment( "pszne obiad", 21.37f, Categories.Transport, "bardzo pszne", date);
        PastPayment pastPayment2 = new PastPayment( "pszne obiad2", 14.10f, Categories.Kosmetyki, "bardzo pszne2", date2);
        pastPayment.setID(1);
        pastPayment2.setID(2);

        PastPaymentRepository pastRepo = new PastPaymentRepository();
        pastRepo.AddToRepo(pastPayment);
        pastRepo.AddToRepo(pastPayment2);

        Serializer serializer = new Serializer();
        serializer.SerializePast(pastRepo);

        PastPaymentRepository pastRepo2 = serializer.DeserializePast();
        assertEquals(pastRepo.toString(), pastRepo2.toString());

    }

    @Test
    public void serializeFuture() {
        FuturePayment futurePayment = new FuturePayment( "pszne obiad", 21.37f, Categories.Transport, "bardzo pszne");
        FuturePayment futurePayment2 = new FuturePayment( "pszne obiad2", 14.10f, Categories.Kosmetyki, "bardzo pszne2");
        futurePayment.setID(1);
        futurePayment.setID(2);

        FuturePaymentRepository futureRepo = new FuturePaymentRepository();

        futureRepo.AddToRepo(futurePayment);
        futureRepo.AddToRepo(futurePayment2);

        Serializer serializer = new Serializer();
        serializer.SerializeFuture(futureRepo);

        FuturePaymentRepository futureRepo2 = serializer.DeserializeFuture();
        assertEquals(futureRepo.toString(), futureRepo2.toString());

    }

}
