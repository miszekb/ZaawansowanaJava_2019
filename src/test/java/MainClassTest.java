import org.junit.Test;

import static org.junit.Assert.*;

public class MainClassTest {
    private PastPaymentRepository pastRepo;
    private FuturePaymentRepository futureRepo;
    private DrawingClass plotDrawer;

    public MainClassTest(PastPaymentRepository pastRepo, FuturePaymentRepository futureRepo, DrawingClass plotDrawer) {
        this.pastRepo = pastRepo;
        this.futureRepo = futureRepo;
        this.plotDrawer = plotDrawer;
    }

    public void setPastRepo(PastPaymentRepository pastRepo) {
        this.pastRepo = pastRepo;
    }

    public void setFutureRepo(FuturePaymentRepository futureRepo) {
        this.futureRepo = futureRepo;
    }




}