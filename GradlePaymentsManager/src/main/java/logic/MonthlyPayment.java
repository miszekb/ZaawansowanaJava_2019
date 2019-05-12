package logic;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "monthly_payment_table")
public class MonthlyPayment implements Payment {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private int ID;
    @Column(name = "name")
    private String name;
    private float price;
    private Categories type;
    private String description;
    private Short paidCounter;
    private HashMap<Integer, Boolean> monthList = new HashMap<>();

    public MonthlyPayment(int ID, String name, float price, Categories type, String description) {
        this.paidCounter = 0;
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.type = type;
        this.description = description;
        for(int i=1;i<13;i++)
        {
            monthList.put(new Integer(i), Boolean.FALSE);
        }
    }
    public MonthlyPayment() {

    }

    public void payThisMonth() {
        this.monthList.replace(new Integer(new Date().getMonth() + 1), Boolean.TRUE);
    }

    public HashMap<Integer, Boolean> getMonthList() {
        return monthList;
    }

    public void setMonthList(HashMap<Integer, Boolean> monthList) {
        this.monthList = monthList;
    }


    @Override
    public int getPaymentID() { return ID; }

    @Override
    public String getPaymentName() {
        return name;
    }

    @Override
    public float getPaymentPrice() {
        return price;
    }

    @Override
    public String getPaymentType() {
        return type.key;
    }

    @Override
    public String getPaymentDescription() {
        return description;
    }

    @Override
    public void setPaymentID(int ID) {
        this.ID = ID;
    }

    @Override
    public void setPaymentName(String name) {
        this.name = name;
    }

    @Override
    public void setPaymentPrice(float price) {
        this.price = price;
    }

    @Override
    public void setPaymentType(Categories type) {
        this.type = type;
    }

    @Override
    public void setPaymentDescription(String description) {
        this.description = description;
    }

    @Override
    public String archive() {
        return null;
    }
}
