package logic;

public class PaymentsManager {

    private PastPaymentRepository pastRepo;
    private FuturePaymentRepository futureRepo;

    public PaymentsManager(PastPaymentRepository pastRepo, FuturePaymentRepository futureRepo) {
        this.pastRepo = pastRepo;
        this.futureRepo = futureRepo;
    }

    public PastPaymentRepository getPastRepo() {
        return pastRepo;
    }

    public void setPastRepo(PastPaymentRepository pastRepo) {
        this.pastRepo = pastRepo;
    }

    public FuturePaymentRepository getFutureRepo() {
        return futureRepo;
    }

    public void setFutureRepo(FuturePaymentRepository futureRepo) {
        this.futureRepo = futureRepo;
    }

    public PastPayment getSpecificPastPayment(int ID) {
        return null;
    }

    public FuturePayment getSpecificFuturePayment(int ID) {
        return null;
    }

}
