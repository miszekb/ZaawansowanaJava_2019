package logic;

public class MainClass {

    public static void main(String[] args)
    {
        FuturePayment futurePayment = new FuturePayment( "działa dziala", 10, Categories.Rozrywka, "o kurde");
        //futurePayment.saveFuturePayment();
        futurePayment = futurePayment.getFuturePayment(92827);

        System.out.print(futurePayment.toString());

    }
}