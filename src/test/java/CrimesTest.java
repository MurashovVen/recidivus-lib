import ben.mur.recidivus.NewCrime;
import ben.mur.recidivus.OldCrime;
import ben.mur.recidivus.Recidivus;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

public class CrimesTest {
    @Test
    public void recidivusP023NTest(){
        NewCrime newCrime = new NewCrime();
        newCrime.setAdulthood(true);
        newCrime.setCategory(0);
        newCrime.setIntent(true);
        newCrime.setDate("2019-08-30");

        LinkedList<OldCrime> oldCrimes = new LinkedList<>();

        for(int i = 0; i<4; i++){
            OldCrime oldCrimeSource = new OldCrime();

            oldCrimeSource.setIntent(true);
            oldCrimeSource.setAdulthood(true);
            oldCrimeSource.setConditional(false);
            oldCrimeSource.setCategory(1);
            oldCrimeSource.setEarlyRepayment(false);

            if(i != 1) {
                oldCrimeSource.setTypePunishment(i);
            }
            else {
                oldCrimeSource.setTypePunishment(0);
            }

            oldCrimeSource.setServitude(true);
            oldCrimeSource.setEndDatePunishment("2018-08-29");
            oldCrimes.add(oldCrimeSource);
        }

        String recidivusType = Recidivus.getTypeRecidivus(oldCrimes, newCrime);

        Assert.assertEquals("Рецидива нет", recidivusType);
    }

    @Test
    public void recidivusPNTest(){
        NewCrime newCrime = new NewCrime();

        newCrime.setAdulthood(true);
        newCrime.setCategory(0);
        newCrime.setIntent(true);
        newCrime.setDate("2019-08-30");

        LinkedList<OldCrime> oldCrimes = new LinkedList<>();

        for(int i = 0; i<11; i++){
            OldCrime oldCrime = new OldCrime();

            oldCrime.setIntent(true);
            oldCrime.setAdulthood(true);
            oldCrime.setConditional(false);
            oldCrime.setCategory(1);
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
        }

        String recidivusType = Recidivus.getTypeRecidivus(oldCrimes, newCrime);

        Assert.assertEquals("Рецидива нет", recidivusType);

    }

    @Test
    public void recidivusP023YTest(){
        NewCrime newCrime = new NewCrime();

        newCrime.setAdulthood(true);
        newCrime.setCategory(0);
        newCrime.setIntent(true);
        newCrime.setDate("2019-08-28");

        LinkedList<OldCrime> oldCrimes = new LinkedList<>();

        for(int i = 0; i<4; i++){
            OldCrime oldCrime = new OldCrime();

            oldCrime.setIntent(true);
            oldCrime.setAdulthood(true);
            oldCrime.setConditional(false);
            oldCrime.setCategory(1);
            oldCrime.setEarlyRepayment(false);
            oldCrime.setServitude(true);

            if(i != 1) {
                oldCrime.setTypePunishment(i);
            } else {
                oldCrime.setTypePunishment(0);
            }

            oldCrime.setEndDatePunishment("2018-08-29");

            oldCrimes.add(oldCrime);
        }

        String recidivusType = Recidivus.getTypeRecidivus(oldCrimes, newCrime);

        Assert.assertEquals("Простой рецидив", recidivusType);

    }

    @Test
    public void recidivusPYTest(){
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

            if(i < 4) {
                oldCrime.setCategory(1);
            }
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
        }

        String recidivusType = Recidivus.getTypeRecidivus(oldCrimes, newCrime);

        Assert.assertEquals("Простой рецидив", recidivusType);

    }

    @Test
    public void recidivusP11NTest(){
        NewCrime newCrime = new NewCrime();

        newCrime.setAdulthood(true);
        newCrime.setCategory(0);
        newCrime.setIntent(true);
        newCrime.setDate("2019-08-30");

        LinkedList<OldCrime> oldCrimes = new LinkedList<>();

        for(int i = 1; i<4; i++){
            OldCrime oldCrime = new OldCrime();

            oldCrime.setIntent(true);
            oldCrime.setAdulthood(true);
            oldCrime.setConditional(false);
            oldCrime.setCategory(i);
            oldCrime.setEarlyRepayment(false);
            oldCrime.setTypePunishment(11);

            if (i == 1) {
                oldCrime.setStartDatePunishment("2015-08-29");
            }

            if (i == 2) {
                oldCrime.setStartDatePunishment("2010-08-29");
            }
            if (i == 3) {
                oldCrime.setStartDatePunishment("2008-08-29");
            }
            oldCrime.setDatePunishment(1,0, 0);

            oldCrimes.add(oldCrime);
        }

        String recidivusType = Recidivus.getTypeRecidivus(oldCrimes, newCrime);

        Assert.assertEquals("Рецидива нет", recidivusType);
    }

    @Test
    public void recidivusP11YTest(){
        NewCrime newCrime = new NewCrime();

        newCrime.setAdulthood(true);
        newCrime.setCategory(0);
        newCrime.setIntent(true);
        newCrime.setDate("2019-08-28");

        LinkedList<OldCrime> oldCrimes = new LinkedList<>();

        for(int i = 1; i<4; i++){
            OldCrime oldCrime = new OldCrime();

            oldCrime.setIntent(true);
            oldCrime.setAdulthood(true);
            oldCrime.setConditional(false);
            oldCrime.setCategory(i);
            oldCrime.setEarlyRepayment(false);
            oldCrime.setTypePunishment(11);

            if (i == 1) {
                oldCrime.setStartDatePunishment("2015-08-29");
            }
            if (i == 2) {
                oldCrime.setStartDatePunishment("2010-08-29");
            }
            if (i == 3) {
                oldCrime.setStartDatePunishment("2008-08-29");
            }

            oldCrime.setDatePunishment(1,0, 0);

            oldCrimes.add(oldCrime);

            String recidivusType = Recidivus.getTypeRecidivus(oldCrimes, newCrime);

            Assert.assertEquals("Простой рецидив", recidivusType);
        }
    }
}


