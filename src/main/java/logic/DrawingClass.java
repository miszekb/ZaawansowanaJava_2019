package logic;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class DrawingClass {
    private ArrayList<PastPayment> pastPaymentsList;
    private ArrayList<FuturePayment> futurePaymentsList;
    private int id;
    private float price;
    private String description;
    private Date date;

    public DrawingClass(ArrayList<PastPayment> pastPayment, ArrayList<FuturePayment> futurePayments){
        this.pastPaymentsList = pastPayment;
        this.futurePaymentsList = futurePayments;
    }

    public int countPaymentsBetweenDates(Categories num, int monthOne, int monthLast, int dayOne, int dayLast)
    {
        ArrayList<PastPayment> temporaryPastPaymentsList = new ArrayList<PastPayment>();
        for(PastPayment pp : pastPaymentsList){
            LocalDate date = pp.getPaymentDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if(date.getMonthValue() >= monthOne && date.getMonthValue() <=monthLast && date.getDayOfMonth() >= dayOne && date.getDayOfMonth()  <= dayLast && pp.getPaymentType()==num)
                temporaryPastPaymentsList.add(pp);
        }
        return temporaryPastPaymentsList.size();
    }

    public float returnSumOfPaymentsBetweenDates(Categories num, int monthOne, int monthLast, int dayOne, int dayLast)
    {
        ArrayList<PastPayment> temporaryPastPaymentsList = new ArrayList<PastPayment>();
        for(PastPayment pp : pastPaymentsList){
            LocalDate date = pp.getPaymentDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if(date.getMonthValue() >= monthOne && date.getMonthValue() <=monthLast && date.getDayOfMonth() >= dayOne && date.getDayOfMonth()  <= dayLast && pp.getPaymentType()==num)
                temporaryPastPaymentsList.add(pp);
        }
        float sum = 0;
        for(PastPayment pp:temporaryPastPaymentsList){
            sum+=pp.getPaymentPrice();
        }
        return sum;
    }

    public int returnTypePastPayments(Categories typeNum){
        ArrayList<PastPayment> temporaryPastPaymentsList = new ArrayList<PastPayment>();
        for(PastPayment pp : pastPaymentsList){
            if(pp.getPaymentType() == typeNum){
                temporaryPastPaymentsList.add(pp);
            }
        }
        return temporaryPastPaymentsList.size();
    }

    public float returnSumOfPayments(Categories typeNum){
        ArrayList<PastPayment> temporaryPastPaymentsList = new ArrayList<PastPayment>();
        for(PastPayment pp : pastPaymentsList){
            if(pp.getPaymentType() == typeNum){
                temporaryPastPaymentsList.add(pp);
            }
        }
        float sum =0;

        for(PastPayment pp: temporaryPastPaymentsList){
            sum+=pp.getPaymentPrice();
        }
        return sum;
    }

    public int returnTypeFuturePayments(Categories typeNum){
        ArrayList<FuturePayment> temporaryFuturePaymentsList = new ArrayList<FuturePayment>();
        for(FuturePayment fp : futurePaymentsList){
            if(fp.getPaymentType() == typeNum){
                temporaryFuturePaymentsList.add(fp);
            }
        }
        return temporaryFuturePaymentsList.size();
    }

    public float returnSumOfFuturePayments(Categories typeNum){
        ArrayList<FuturePayment> temporaryFuturePaymentsList = new ArrayList<FuturePayment>();
        for(FuturePayment fp : futurePaymentsList){
            if(fp.getPaymentType() == typeNum){
                temporaryFuturePaymentsList.add(fp);
            }
        }
        float sum =0;

        for(FuturePayment fp: temporaryFuturePaymentsList){
            sum+=fp.getPaymentPrice();
        }
        return sum;
    }

    public void debugPaymentsInMonth(int month){
        ArrayList<PastPayment> temporaryPastPaymentsList = returnPaymentsInMonth(month);
        for (PastPayment pp : temporaryPastPaymentsList ) {
            System.out.println(pp.toString());
        }
    }

    private ArrayList<PastPayment> returnPaymentsInMonth(int month){
        ArrayList<PastPayment> temporaryPastPaymentsList = new ArrayList<PastPayment>();
        for (PastPayment pp : pastPaymentsList){
            if(pp.getPaymentDate().getMonth() == month)
                temporaryPastPaymentsList.add(pp);
        }
        return temporaryPastPaymentsList;
    }
}
