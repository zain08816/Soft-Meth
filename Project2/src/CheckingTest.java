import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckingTest {

    /**
     * Testing monthly Interest with 0.05%
     */
    @Test
    void monthlyInterest() {
        Checking test1 = new Checking(new Profile("test", "test"), new Date("1/1/2020"),
                120, false);
        Checking test2 = new Checking(new Profile("test", "test"), new Date("1/1/2020"),
                1920, false);
        Checking test3 = new Checking(new Profile("test", "test"), new Date("1/1/2020"),
                24000, true);

        assertEquals(0.005, test1.monthlyInterest());
        assertEquals(0.08, test2.monthlyInterest());
        assertEquals(1.0, test3.monthlyInterest());
    }

    /**
     * Testing monthly Fee at various account balances and direct deposit accounts
     */
    @Test
    void monthlyFee() {
        Checking test1 = new Checking(new Profile("test", "test"), new Date("1/1/2020"),
                120, false);
        Checking test2 = new Checking(new Profile("test", "test"), new Date("1/1/2020"),
                1920, false);
        Checking test3 = new Checking(new Profile("test", "test"), new Date("1/1/2020"),
                100, true);
        Checking test4 = new Checking(new Profile("test", "test"), new Date("1/1/2020"),
                1600, true);

        assertEquals(25, test1.monthlyFee());
        assertEquals(0, test2.monthlyFee());
        assertEquals(0, test3.monthlyFee());
        assertEquals(0, test4.monthlyFee());
    }
}