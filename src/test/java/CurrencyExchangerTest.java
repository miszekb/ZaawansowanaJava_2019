import logic.CurrencyExchanger;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CurrencyExchangerTest {

    @Test
    public void GetCurrencyRate() {
        float rate = CurrencyExchanger.getCurrencyRate("USD");
        boolean check = (rate > 0.f);
        assertEquals(check, true);
    }

    @Test
    public void GetCurrencyRateOnDate() {
        float rate = CurrencyExchanger.getCurrencyRateOnDate("USD", "2015-06-01");
        assertEquals(rate, 3.7850f, 0.01f);
    }
}
