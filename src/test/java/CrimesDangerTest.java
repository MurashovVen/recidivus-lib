import ben.mur.recidivus.NewCrime;
import ben.mur.recidivus.OldCrime;
import ben.mur.recidivus.Recidivus;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;


public class CrimesDangerTest {

    @Test
    public void recidivusP11YTest() {
        NewCrime newCrime = new NewCrime();

        newCrime.setAdulthood(true);
        newCrime.setCategory(2);
        newCrime.setRealDeprivation(true);
        newCrime.setIntent(true);
        newCrime.setDate("2019-08-29");

        LinkedList<OldCrime> oldCrimes = new LinkedList<>();

        for (int i = 1; i < 3; i++) {
            OldCrime oldCrime = new OldCrime();

            oldCrime.setIntent(true);
            oldCrime.setAdulthood(true);
            oldCrime.setConditional(false);
            oldCrime.setCategory(1);
            oldCrime.setEarlyRepayment(false);
            oldCrime.setTypePunishment(11);
            oldCrime.setStartDatePunishment("2016-08-30");
            oldCrime.setDatePunishment(1, 0, 0);

            oldCrimes.add(oldCrime);
        }
        String recidivusType = Recidivus.getTypeRecidivus(oldCrimes, newCrime);

        Assert.assertEquals("Опасный рецидив", recidivusType);
    }

    @Test
    public void recidivusP11Y2Test() {
        NewCrime newCrime = new NewCrime();

        newCrime.setAdulthood(true);
        newCrime.setCategory(2);
        newCrime.setRealDeprivation(true);
        newCrime.setIntent(true);
        newCrime.setDate("2019-08-29");

        for (int i = 1; i < 3; i++) {
            LinkedList<OldCrime> oldCrimes = new LinkedList<>();

            OldCrime oldCrime = new OldCrime();

            oldCrime.setIntent(true);
            oldCrime.setAdulthood(true);
            oldCrime.setConditional(false);
            oldCrime.setEarlyRepayment(false);
            oldCrime.setTypePunishment(11);

            if(i == 1) {
                oldCrime.setCategory(2);
                oldCrime.setStartDatePunishment("2010-08-30");
            }
            else {
                oldCrime.setCategory(3);
                oldCrime.setStartDatePunishment("2008-08-30");
            }

            oldCrime.setDatePunishment(1, 0, 0);

            oldCrimes.add(oldCrime);

            String recidivusType = Recidivus.getTypeRecidivus(oldCrimes, newCrime);

            Assert.assertEquals("Опасный рецидив", recidivusType);
        }
    }
}
