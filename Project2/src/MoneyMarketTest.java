import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyMarketTest {

    /**
     * Testing monthly Interest with 0.65%
     */
    @Test
    void monthlyInterest() {

        MoneyMarket test1 = new MoneyMarket(new Profile("test", "test"), new Date("1/1/2020"),
                120);
        MoneyMarket test2 = new MoneyMarket(new Profile("test", "test"), new Date("1/1/2020"),
                1920);
        MoneyMarket test3 = new MoneyMarket(new Profile("test", "test"), new Date("1/1/2020"),
                24000);

        assertEquals(120*(0.0065/12), test1.monthlyInterest());
        assertEquals(1920*(0.0065/12), test2.monthlyInterest());
        assertEquals(24000*(0.0065/12), test3.monthlyInterest());
    }

    /**
     * Testing monthly Fee at various account balances with different withdraw amounts
     */
    @Test
    void monthlyFee() {
        MoneyMarket test1 = new MoneyMarket(new Profile("test", "test"), new Date("1/1/2020"),
                2500);
        assertEquals(0, test1.monthlyFee());

        MoneyMarket test2 = new MoneyMarket(new Profile("test", "test"), new Date("1/1/2020"),
                2499);
        assertEquals(12, test2.monthlyFee());

        MoneyMarket test3 = new MoneyMarket(new Profile("test", "test"), new Date("1/1/2020"),
                2501);
        assertEquals(0, test3.monthlyFee());

        MoneyMarket test4 = new MoneyMarket(new Profile("test", "test"), new Date("1/1/2020"),
                6000);
        assertEquals(0, test4.monthlyFee());
        test4.debit(100);
        test4.debit(100);
        test4.debit(100);
        test4.debit(100);
        test4.debit(100);
        test4.debit(100);
        assertEquals(0, test4.monthlyFee());
        test4.debit(100);
        assertEquals(12, test4.monthlyFee());

    }
}