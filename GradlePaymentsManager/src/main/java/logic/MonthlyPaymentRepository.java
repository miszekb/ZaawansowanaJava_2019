package logic;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Date;

public class MonthlyPaymentRepository {

    private ArrayList<MonthlyPayment> monthlyRepo = new ArrayList<MonthlyPayment>();

    public void AddToRepo(MonthlyPayment payment) {
        monthlyRepo.add(payment);
    }

    public void DeletePayment(int ID) {
        for(MonthlyPayment payment : monthlyRepo) {
            if(payment.getPaymentID() == ID) {
                monthlyRepo.remove(payment);
            }
        }
    }

    public MonthlyPayment getSpecificPayment(int ID) {
        for(MonthlyPayment payment : monthlyRepo) {
            if(payment.getPaymentID() == ID) {
               return payment;
            }
        }
        return null;
    }

    public ArrayList<MonthlyPayment> getRepo() {
        return monthlyRepo;
    }

    public void setMonthlyRepo(ArrayList<MonthlyPayment> payments) {
        monthlyRepo = payments;
    }

    //These will be added to Future Payments
    public ArrayList<MonthlyPayment> getUnpaidThisMonth() {
        ArrayList<MonthlyPayment> temp = new ArrayList<MonthlyPayment>();
        for (MonthlyPayment m_payment : monthlyRepo){
            if(!m_payment.getMonthList().get(new Date().getMonth()+1)) {
                temp.add(m_payment);
            }
        }
        return temp;
    }
    //These will be added to Past Payments
    public ArrayList<MonthlyPayment> getAllPaid(){
        ArrayList<MonthlyPayment> temp = new ArrayList<MonthlyPayment>();
        for (MonthlyPayment m_payment : monthlyRepo){
            for(int i=1; i<13; i++){
                if(m_payment.getMonthList().get(i)) {
                    temp.add(m_payment);
                }
            }
        }
        return temp;
    }
}
