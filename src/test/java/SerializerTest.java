import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class SerializerTest {

    @Test
    public void serializePast() {
        Date date = new Date(2019, 3, 12);
        Date date2 = new Date(2018, 4, 10);
        PastPayment pastPayment = new PastPayment(1, "pszne obiad", 21.37f, (short) 2, "bardzo pszne", date);
        PastPayment pastPayment2 = new PastPayment(2, "pszne obiad2", 14.10f, (short) 1, "bardzo pszne2", date2);

        PastPaymentRepository pastRepo = new PastPaymentRepository();
        pastRepo.AddToRepo(pastPayment);
        pastRepo.AddToRepo(pastPayment2);

        Serializer serializer = new Serializer();
        serializer.SerializePast(pastRepo);

        PastPaymentRepository pastRepo2 = serializer.DeserializePast();
        // System.out.println(pastRepo2.toString());
        assertEquals(pastRepo.toString(), pastRepo2.toString());

    }

    @Test
    public void serializeFuture() {
        FuturePayment futurePayment = new FuturePayment(1, "pszne obiad", 21.37f, (short) 2, "bardzo pszne");
        FuturePayment futurePayment2 = new FuturePayment(2, "pszne obiad2", 14.10f, (short) 3, "bardzo pszne2");

        FuturePaymentRepository futureRepo = new FuturePaymentRepository();

        futureRepo.AddToRepo(futurePayment);
        futureRepo.AddToRepo(futurePayment2);

        Serializer serializer = new Serializer();
        serializer.SerializeFuture(futureRepo);

        FuturePaymentRepository futureRepo2 = serializer.DeserializeFuture();
        //System.out.println(futureRepo2.toString());
        assertEquals(futureRepo.toString(), futureRepo2.toString());

    }

}
