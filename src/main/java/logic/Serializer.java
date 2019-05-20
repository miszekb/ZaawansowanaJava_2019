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
            xStream.alias("PastPayment", PastPayment.class);
            xStream.allowTypeHierarchy(Collection.class);
            xStream.toXML(pastPaymentRepository.getPastPayments(), outStream);
            for(PastPayment pp:pastPaymentRepository.getPastPayments()){
                pp.savePastPayment();
            }
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
            xStream.alias("FuturePayment", FuturePayment.class);
            xStream.allowTypeHierarchy(Collection.class);
            xStream.toXML(futurePaymentRepository.getFuturePayments(), outStream);
            for(FuturePayment fp:futurePaymentRepository.getFuturePayments()){
                fp.saveFuturePayment();
            }
        }
        catch(IOException exception) {
            System.out.println(exception.getMessage());
        }
    }


    public void serializeAll(PastPaymentRepository pastPaymentRepository,
                             FuturePaymentRepository futurePaymentRepository)
    {
        serializePast(pastPaymentRepository);
        serializeFuture(futurePaymentRepository);
    }

    public PastPaymentRepository deserializePast()
    {
        PastPaymentRepository pastPaymentRepository = new PastPaymentRepository();

        for (Payment pay : readFromFile("PAST.xml")) {
            pastPaymentRepository.addToRepository((PastPayment) pay);
        }

        return pastPaymentRepository;
    }

    public FuturePaymentRepository deserializeFuture()
    {
        FuturePaymentRepository futurePaymentRepository = new FuturePaymentRepository();

        for (Payment pay : readFromFile("FUTURE.xml")) {
            futurePaymentRepository.addToRepository((FuturePayment) pay);
        }

        return futurePaymentRepository;
    }

    private ArrayList<Payment> readFromFile(String path){

        ArrayList<Payment> payments = new ArrayList<>();

        try {
            File xmlFile = new File(path);
            XStream xStream = new XStream(new DomDriver());

            xStream.alias("FuturePayment", FuturePayment.class);
            xStream.alias("PastPayment", PastPayment.class);
            xStream.allowTypeHierarchy(Collection.class);

            payments = (ArrayList<Payment>)xStream.fromXML(new FileInputStream(xmlFile));

        } catch(IOException exception) {
            System.out.println(exception.getMessage());
        }

        return payments;
    }

}
