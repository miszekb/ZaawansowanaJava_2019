package logic;

public class MonthlyPayment implements Payment {

    private int ID;
    private String name;
    private float price;
    private Categories type;
    private String description;
    private int dayOfMonth;

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    @Override
    public int getPaymentID() {
        return ID;
    }

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
