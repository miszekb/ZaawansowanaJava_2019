import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CurrencyExchangerTest {

    @Test
    public void GetCurrencyRate() throws IOException, JSONException {
        float rate = CurrencyExchanger.GetCurrencyRate("USD");
        boolean check = (rate > 0.f);
        assertEquals(check, true);
    }

    @Test
    public void GetCurrencyRateOnDate() throws IOException, JSONException {
        float rate = CurrencyExchanger.GetCurrencyRateOnDate("USD", "2015-06-01");
        assertEquals(rate, 3.7850f, 0.01f);
    }
}
