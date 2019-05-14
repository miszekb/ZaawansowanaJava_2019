package logic;

import java.util.*;

public class MonthlyPayment implements Payment {

    private int ID;
    private String name;
    private float price;
    private Categories type;
    private String description;
    private HashMap<Integer, Boolean> monthList = new HashMap<>();
    //TEGO NIE SERIALIZUJEMY !!!
    private float priceInDifferentCurrency;
    private int firstMonth;

    public MonthlyPayment(String name, float price, Categories type, String description) {
        this.ID = Integer.parseInt((UUID.randomUUID().toString().replaceAll("[^0-9]", "")).substring(0,5));
        this.firstMonth = new Date().getMonth() + 1;
        this.name = name;
        this.price = price;
        this.type = type;
        this.description = description;

        for(int i = this.firstMonth; i<13; i++)
        {
            monthList.put(new Integer(i), Boolean.FALSE);
            if(i == 12) { i = 0;}
        }
    }

    public boolean payThisMonth() {
        if(!monthList.get(new Integer(new Date().getMonth() +1))) {
            monthList.replace(new Integer(new Date().getMonth() + 1), Boolean.TRUE);
            return true;
        }
        return false;
    }

    public HashMap<Integer, Boolean> getMonthList() {
        return monthList;
    }

    public void setMonthList(HashMap<Integer, Boolean> monthList) {
        this.monthList = monthList;
    }

    public void setPriceInDifferentCurrency(float currency){
        priceInDifferentCurrency = price/currency;
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

    public float getPriceInDifferentCurrency() {
        return priceInDifferentCurrency;
    }
}
