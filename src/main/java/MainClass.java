import java.util.Date;

public class MainClass {

    //tiny example to test Travis continuous integration




    public static void main(String[] args)
    {
        PastPayment pastPayment = new PastPayment(1,"pszne obiad",21.37f,(short)2, "bardzo pszne", new Date());
        System.out.print(pastPayment.toString());
    }


}
