package logic;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class Serializer {

    //SERIALIZATION METHODS

    public void SerializePast(PastPaymentRepository pastRepo)
    {
        try {
            FileOutputStream outStream = new FileOutputStream("PAST.xml");
            XStream xStream = new XStream(new DomDriver());
            xStream.toXML(pastRepo.getRepo(), outStream);
        }
        catch(IOException ex) {}
    }

    public void SerializeFuture(FuturePaymentRepository futureRepo)
    {
        try {
            FileOutputStream outStream = new FileOutputStream("FUTURE.xml");
            XStream xStream = new XStream(new DomDriver());
            xStream.toXML(futureRepo.getRepo(), outStream);
        }
        catch(IOException ex) {}
    }


    public void SerializeMonth(MonthlyPaymentRepository monthrepo)
    {
        try {
            FileOutputStream outStream = new FileOutputStream("MONTHLY.xml");
            XStream xStream = new XStream(new DomDriver());
            xStream.toXML(monthrepo.getRepo(), outStream);
        }
        catch(IOException ex) {}
    }


    public void SerializeAll(PastPaymentRepository pastRepo,
                             FuturePaymentRepository futureRepo,
                             MonthlyPaymentRepository monthlyRepo)
    {
        SerializePast(pastRepo);
        SerializeFuture(futureRepo);
        SerializeMonth(monthlyRepo);
    }

    //DESERIALIZATION METHODS

    public PastPaymentRepository DeserializePast()
    {
        PastPaymentRepository pastRepo = new PastPaymentRepository();

        try {
            File xmlFile = new File("PAST.xml");
            XStream xStream = new XStream(new DomDriver());

            for (PastPayment e : (ArrayList<PastPayment>) xStream.fromXML(new FileInputStream(xmlFile))) {
                pastRepo.AddToRepo(e);
            }
        }

        catch(IOException ex) {}
        return pastRepo;
    }

    public FuturePaymentRepository DeserializeFuture()
    {
        FuturePaymentRepository futureRepo = new FuturePaymentRepository();

        try {
            File xmlFile = new File("FUTURE.xml");
            XStream xStream = new XStream(new DomDriver());

            for (FuturePayment e : (ArrayList<FuturePayment>) xStream.fromXML(new FileInputStream(xmlFile))) {
                futureRepo.AddToRepo(e);
            }
        }
        catch(IOException ex) {}
        return futureRepo;
    }


    public MonthlyPaymentRepository DeserializeMonth()
    {
        MonthlyPaymentRepository monthRepo = new MonthlyPaymentRepository();

        try {
            File xmlFile = new File("MONTHLY.xml");
            XStream xStream = new XStream(new DomDriver());

            for (MonthlyPayment e : (ArrayList<MonthlyPayment>) xStream.fromXML(new FileInputStream(xmlFile))) {
                monthRepo.AddToRepo(e);
            }
        }

        catch(IOException ex) {}
        return monthRepo;
    }
}
