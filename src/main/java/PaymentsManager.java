public class PaymentsManager {

    public float getSavings() {
        return savings;
    }

    public void setSavings(float savings) {
        this.savings = savings;
    }

    public void addToSavings(float cash) {
        savings += cash;
    }

    private float savings;


}
