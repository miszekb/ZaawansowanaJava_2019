import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.util.ArrayList;


public class Serializer {

    private XMLEncoder encoder;
    private XMLDecoder decoder;

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

    public void SerializeAll(PastPaymentRepository pastRepo, FuturePaymentRepository futureRepo)
    {
        SerializePast(pastRepo);
        SerializeFuture(futureRepo);
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
}
