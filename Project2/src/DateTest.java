import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    /**
     * testing various valid and invalid Dates
     */
    @Test
    void isValid() {
        Date test1 = new Date("1/1/2020");
        Date test2 = new Date("02/28/2019");
        Date test3 = new Date("02/29/2019");
        Date test4 = new Date("02/29/1900");
        Date test5 = new Date("0/1/2020");
        Date test6 = new Date("1/00/2020");
        Date test7 = new Date("02/29/2000");

        assertTrue(test1.isValid());
        assertTrue(test2.isValid());
        assertFalse(test3.isValid());
        assertFalse(test4.isValid());
        assertFalse(test5.isValid());
        assertFalse(test6.isValid());
        assertTrue(test7.isValid());

    }
}