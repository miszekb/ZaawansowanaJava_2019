package logic;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Serializer {

    public void serializePast(PastPaymentRepository pastPaymentRepository)
    {
        try {
            FileOutputStream outStream = new FileOutputStream("PAST.xml");
            XStream xStream = new XStream(new DomDriver());
            xStream.toXML(pastPaymentRepository.getPastPayments(), outStream);
        }
        catch(IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void serializeFuture(FuturePaymentRepository futurePaymentRepository)
    {
        try {
            FileOutputStream outStream = new FileOutputStream("FUTURE.xml");
            XStream xStream = new XStream(new DomDriver());
            xStream.toXML(futurePaymentRepository.getFuturePayments(), outStream);
        }
        catch(IOException exception) {
            System.out.println(exception.getMessage());
        }
    }


    public void serializeMonth(MonthlyPaymentRepository monthlyPaymentRepository)
    {
        try {
            FileOutputStream outStream = new FileOutputStream("MONTHLY.xml");
            XStream xStream = new XStream(new DomDriver());
            xStream.toXML(monthlyPaymentRepository.getMonthlyPayments(), outStream);
        }
        catch(IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void serializeAll(PastPaymentRepository pastPaymentRepository,
                             FuturePaymentRepository futurePaymentRepository,
                             MonthlyPaymentRepository monthlyPaymentRepository)
    {
        serializePast(pastPaymentRepository);
        serializeFuture(futurePaymentRepository);
        serializeMonth(monthlyPaymentRepository);
    }

    public PastPaymentRepository deserializePast()
    {
        PastPaymentRepository pastPaymentRepository = new PastPaymentRepository();

        try {
            File xmlFile = new File("PAST.xml");
            XStream xStream = new XStream(new DomDriver());

            for (PastPayment e : (ArrayList<PastPayment>) xStream.fromXML(new FileInputStream(xmlFile))) {
                pastPaymentRepository.addToRepository(e);
            }
        } catch(IOException exception) {
            System.out.println(exception.getMessage());
        }
        return pastPaymentRepository;
    }

    public FuturePaymentRepository deserializeFuture()
    {
        FuturePaymentRepository futurePaymentRepository = new FuturePaymentRepository();

        try {
            File xmlFile = new File("FUTURE.xml");
            XStream xStream = new XStream(new DomDriver());
            xStream.allowTypeHierarchy(Collection.class);

            for (FuturePayment e : (ArrayList<FuturePayment>) xStream.fromXML(new FileInputStream(xmlFile))) {
                futurePaymentRepository.addToRepository(e);
            }
        } catch(IOException exception) {
            System.out.println(exception.getMessage());
        }
        return futurePaymentRepository;
    }


    public MonthlyPaymentRepository deserializeMonth()
    {
        MonthlyPaymentRepository monthlyPaymentRepository = new MonthlyPaymentRepository();

        try {
            File xmlFile = new File("MONTHLY.xml");
            XStream xStream = new XStream(new DomDriver());

            for (MonthlyPayment e : (ArrayList<MonthlyPayment>) xStream.fromXML(new FileInputStream(xmlFile))) {
                monthlyPaymentRepository.addToRepository(e);
            }
        } catch(IOException exception) {
            System.out.println(exception.getMessage());
        }
        return monthlyPaymentRepository;
    }
}
