package logic;

import java.util.ArrayList;
import java.util.Date;

public class MonthlyPaymentRepository {

    private ArrayList<MonthlyPayment> monthlyPayments = new ArrayList<MonthlyPayment>();

    public void addToRepository(MonthlyPayment payment) {
        monthlyPayments.add(payment);
    }

    public ArrayList<MonthlyPayment> getMonthlyPayments() {
        return monthlyPayments;
    }

    public void setMonthlyPayments(ArrayList<MonthlyPayment> payments) {
        monthlyPayments = payments;
    }

    public void DeletePayment(int ID) {
        for(MonthlyPayment payment : monthlyPayments) {
            if(payment.getPaymentID() == ID) {
                monthlyPayments.remove(payment);
            }
        }
    }

    public MonthlyPayment getSpecificPayment(int ID) {
        for(MonthlyPayment payment : monthlyPayments) {
            if(payment.getPaymentID() == ID) {
               return payment;
            }
        }
        return null;
    }

    public ArrayList<MonthlyPayment> getUnpaidThisMonth() {
        ArrayList<MonthlyPayment> temp = new ArrayList<MonthlyPayment>();
        for (MonthlyPayment m_payment : monthlyPayments){
            if(!m_payment.getMonthList().get(new Date().getMonth()+1)) {
                temp.add(m_payment);
            }
        }
        return temp;
    }

    public ArrayList<MonthlyPayment> getAllPaid(){
        ArrayList<MonthlyPayment> temp = new ArrayList<MonthlyPayment>();
        for (MonthlyPayment m_payment : monthlyPayments){
            for(int i=1; i<13; i++){
                if(m_payment.getMonthList().get(i)) {
                    temp.add(m_payment);
                }
            }
        }
        return temp;
    }
}
