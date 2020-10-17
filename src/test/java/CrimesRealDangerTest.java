import ben.mur.recidivus.NewCrime;
import ben.mur.recidivus.OldCrime;
import ben.mur.recidivus.Recidivus;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

public class CrimesRealDangerTest {
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

            oldCrime.setCategory(2);

            oldCrime.setEarlyRepayment(false);
            oldCrime.setTypePunishment(11);
            oldCrime.setStartDatePunishment("2016-08-30");

            oldCrime.setDatePunishment(1, 0, 0);

            oldCrimes.add(oldCrime);
        }
        String recidivusType = Recidivus.getTypeRecidivus(oldCrimes, newCrime);

        Assert.assertEquals("Особо опасный рецидив", recidivusType);
    }

    @Test
    public void recidivusP023YHardTest(){
        NewCrime newCrime = new NewCrime();

        newCrime.setAdulthood(true);
        newCrime.setCategory(3);
        newCrime.setIntent(true);
        newCrime.setDate("2019-08-28");

        LinkedList<OldCrime> oldCrimes = new LinkedList<>();

        for(int i = 0; i<3; i++){
            OldCrime oldCrime = new OldCrime();

            oldCrime.setIntent(true);
            oldCrime.setAdulthood(true);
            oldCrime.setConditional(false);

            oldCrime.setCategory(2);

            oldCrime.setEarlyRepayment(false);

            oldCrime.setTypePunishment(0);

            oldCrime.setServitude(true);

            oldCrime.setEndDatePunishment("2018-08-29");

            oldCrimes.add(oldCrime);
        }

        String recidivusType = Recidivus.getTypeRecidivus(oldCrimes, newCrime);

        Assert.assertEquals("Особо опасный рецидив", recidivusType);

    }

    @Test
    public void recidivusPYHardTest(){
        NewCrime newCrime = new NewCrime();

        newCrime.setAdulthood(true);
        newCrime.setCategory(0);
        newCrime.setIntent(true);
        newCrime.setDate("2019-08-28");

        LinkedList<OldCrime> oldCrimes = new LinkedList<>();

        for(int i = 0; i<11; i++){
            OldCrime oldCrime = new OldCrime();

            oldCrime.setIntent(true);
            oldCrime.setAdulthood(true);
            oldCrime.setConditional(false);
            oldCrime.setCategory(3);
            oldCrime.setEarlyRepayment(false);

            if(i == 0 || i == 2 || i ==3 || i ==6) {
                oldCrime.setTypePunishment(1);
            }
            else {
                oldCrime.setTypePunishment(i);
            }

            oldCrime.setStartDatePunishment("2017-08-29");
            oldCrime.setDatePunishment(1,0, 0);

            oldCrimes.add(oldCrime);
            oldCrimes.add(oldCrime);

            String recidivusType = Recidivus.getTypeRecidivus(oldCrimes, newCrime);

            Assert.assertEquals("Простой рецидив", recidivusType);
        }
    }
}
