import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import logic.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class SerializerTest {
    PastPaymentRepository pastPaymentRepository;
    FuturePaymentRepository futurePaymentRepository;
    XStream xStream;
    Serializer serializer;
    PastPayment pastPayment;
    PastPayment pastPayment2;

    FuturePayment futurePayment;
    FuturePayment futurePayment2;
    FuturePayment futurePayment3;
    FuturePayment futurePayment4;

    @Before
    public void setUp() {
        Date date = new Date(2019, 3, 12);
        Date date2 = new Date(2018, 4, 10);
        pastPayment = new PastPayment( "pszne obiad", 21.37f, Categories.Zywnosc, "bardzo pszne", date);
        pastPayment2 = new PastPayment( "pszne obiad2", 14.10f, Categories.Ubrania_Obuwie, "bardzo pszne2", date2);
        pastPayment.setID(10);
        pastPayment2.setID(20);
        pastPaymentRepository = new PastPaymentRepository();
        pastPaymentRepository.addToRepository(pastPayment);
        pastPaymentRepository.addToRepository(pastPayment2);

        futurePayment = new FuturePayment( "usluga1", 10.21f, Categories.Rozrywka, "nothinghere1");
        futurePayment2 = new FuturePayment( "usluga2", 15.22f, Categories.Transport, "nothinghere2");
        futurePayment3 = new FuturePayment( "usluga3", 20.23f, Categories.Chemia_SrodkiCzystosci, "nothinghere3");
        futurePayment4 = new FuturePayment( "usluga4", 25.24f, Categories.Uzywki, "nothinghere4");
        futurePayment.setID(50);
        futurePayment2.setID(60);
        futurePayment3.setID(70);
        futurePayment4.setID(80);
        futurePaymentRepository = new FuturePaymentRepository();
        futurePaymentRepository.addToRepository(futurePayment);
        futurePaymentRepository.addToRepository(futurePayment2);
        futurePaymentRepository.addToRepository(futurePayment3);
        futurePaymentRepository.addToRepository(futurePayment4);

        xStream = new XStream(new DomDriver());
        xStream.alias("FuturePayment", FuturePayment.class);
        xStream.alias("PastPayment", PastPayment.class);

        serializer = new Serializer();
    }

    @Test
    public void serializePast() {
        serializer.serializePast(pastPaymentRepository);

        PastPaymentRepository pastPaymentRepository2 = new PastPaymentRepository();

        try {
            File xmlFile = new File("PAST.xml");

            for (PastPayment e : (ArrayList<PastPayment>) xStream.fromXML(new FileInputStream(xmlFile))) {
                pastPaymentRepository2.addToRepository(e);
            }
        } catch(IOException exception) {
            System.out.println(exception.getMessage());
        }

        assertEquals(pastPaymentRepository.toString(), pastPaymentRepository2.toString());
    }

    @Test
    public void serializeFuture() {
        serializer.serializeFuture(futurePaymentRepository);

        FuturePaymentRepository futurePaymentRepository2 = new FuturePaymentRepository();

        try {
            File xmlFile = new File("FUTURE.xml");

            for (FuturePayment e : (ArrayList<FuturePayment>) xStream.fromXML(new FileInputStream(xmlFile))) {
                futurePaymentRepository2.addToRepository(e);
            }
        } catch(IOException exception) {
            System.out.println(exception.getMessage());
        }

        assertEquals(futurePaymentRepository.toString(), futurePaymentRepository2.toString());
    }

    @Test
    public void deserializePast() {
        PastPaymentRepository pastPaymentRepository2 = serializer.deserializePast();
        assertEquals(pastPaymentRepository.toString(), pastPaymentRepository2.toString());
    }

    @Test
    public void deserializeFuture() {
        FuturePaymentRepository futurePaymentRepository2 = serializer.deserializeFuture();
        assertEquals(futurePaymentRepository.toString(), futurePaymentRepository2.toString());
    }

    @After
    public void exti() {
        futurePayment.reomoveFuturePayment(futurePayment.getID());
        futurePayment2.reomoveFuturePayment(futurePayment2.getID());
        futurePayment3.reomoveFuturePayment(futurePayment3.getID());
        futurePayment4.reomoveFuturePayment(futurePayment4.getID());
    }
}
