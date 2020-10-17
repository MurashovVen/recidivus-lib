import ben.mur.recidivus.NewCrime;
import ben.mur.recidivus.OldCrime;
import ben.mur.recidivus.Recidivus;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

public class CrimesGOPTest {

    @Test
    public void recidivusP023NTest(){
        NewCrime newCrime = new NewCrime();

        newCrime.setAdulthood(true);
        newCrime.setCategory(0);
        newCrime.setIntent(true);
        newCrime.setDate("2019-08-30");

        LinkedList<OldCrime> oldCrimes = new LinkedList<>();

        for(int i = 1; i<4; i++){
            OldCrime oldCrime = new OldCrime();

            oldCrime.setGOP(true);
            oldCrime.setIntent(true);
            oldCrime.setAdulthood(true);
            oldCrime.setConditional(false);
            oldCrime.setCategory(1);
            oldCrime.setEarlyRepayment(false);
            oldCrime.setDateGOP("2018-08-29");

            if(i != 1) {
                oldCrime.setTypePunishment(i);
            } else {
                oldCrime.setTypePunishment(0);
            }

            oldCrimes.add(oldCrime);
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

            oldCrime.setGOP(true);
            oldCrime.setIntent(true);
            oldCrime.setAdulthood(true);
            oldCrime.setConditional(false);
            oldCrime.setEarlyRepayment(false);
            oldCrime.setStartDatePunishment("2017-08-29");
            oldCrime.setDatePunishment(2,0, 0);
            oldCrime.setDateGOP("2018-08-29");

            if(i == 0 || i == 2 || i ==3 || i ==6) {
                oldCrime.setTypePunishment(1);
            }
            else {
                oldCrime.setTypePunishment(i);
            }

            if(i < 4) {
                oldCrime.setCategory(1);
            }

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

            oldCrime.setGOP(true);
            oldCrime.setIntent(true);
            oldCrime.setAdulthood(true);
            oldCrime.setConditional(false);
            oldCrime.setCategory(1);
            oldCrime.setEarlyRepayment(false);
            oldCrime.setDateGOP("2018-08-29");

            if(i != 1) {
                oldCrime.setTypePunishment(i);
            } else {
                oldCrime.setTypePunishment(0);
            }

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

            oldCrime.setGOP(true);
            oldCrime.setIntent(true);
            oldCrime.setAdulthood(true);
            oldCrime.setConditional(false);
            oldCrime.setEarlyRepayment(false);
            oldCrime.setStartDatePunishment("2017-08-29");
            oldCrime.setDatePunishment(2,0, 0);
            oldCrime.setDateGOP("2018-08-29");

            if(i == 0 || i == 2 || i ==3 || i ==6) {
                oldCrime.setTypePunishment(1);
            }
            else {
                oldCrime.setTypePunishment(i);
            }

            if(i < 4) {
                oldCrime.setCategory(1);
            }

            oldCrimes.add(oldCrime);
        }

        String recidivusType = Recidivus.getTypeRecidivus(oldCrimes, newCrime);

        Assert.assertEquals("Простой рецидив", recidivusType);

    }

    @Test
    public void recidivusP11NTest(){
        for(int i = 1; i<4; i++){
            LinkedList<OldCrime> oldCrimes = new LinkedList<>();

            NewCrime newCrime = new NewCrime();

            newCrime.setAdulthood(true);
            newCrime.setCategory(0);
            newCrime.setIntent(true);

            OldCrime oldCrime = new OldCrime();
            oldCrime.setGOP(true);
            oldCrime.setIntent(true);
            oldCrime.setAdulthood(true);
            oldCrime.setConditional(false);
            oldCrime.setCategory(i);
            oldCrime.setEarlyRepayment(false);
            oldCrime.setTypePunishment(11);

            if (i == 1) {
                oldCrime.setStartDatePunishment("2015-08-29");
                oldCrime.setDateGOP("2016-08-29");
                newCrime.setDate("2019-08-30");
            }

            if (i == 2) {
                oldCrime.setStartDatePunishment("2010-08-29");
                oldCrime.setDateGOP("2014-08-29");
                newCrime.setDate("2017-08-30");
            }

            if (i == 3) {
                oldCrime.setStartDatePunishment("2008-08-29");
                oldCrime.setDateGOP("2014-08-29");
                newCrime.setDate("2022-08-30");
            }

            oldCrime.setDatePunishment(10,0, 0);

            oldCrimes.add(oldCrime);

            String recidivusType = Recidivus.getTypeRecidivus(oldCrimes, newCrime);

            Assert.assertEquals("Рецидива нет", recidivusType);
        }
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
            OldCrime oldCrimeSource = new OldCrime();

            oldCrimeSource.setGOP(true);
            oldCrimeSource.setIntent(true);
            oldCrimeSource.setAdulthood(true);
            oldCrimeSource.setConditional(false);
            oldCrimeSource.setCategory(1);
            oldCrimeSource.setEarlyRepayment(false);
            oldCrimeSource.setTypePunishment(11);

            if (i == 1) {
                oldCrimeSource.setStartDatePunishment("2015-08-29");
                oldCrimeSource.setDateGOP("2016-08-29");
                newCrime.setDate("2019-08-28");

            }

            if (i == 2) {
                oldCrimeSource.setStartDatePunishment("2010-08-29");
                oldCrimeSource.setDateGOP("2014-08-29");
                newCrime.setDate("2017-08-28");
            }

            if (i == 3) {
                oldCrimeSource.setStartDatePunishment("2008-08-29");
                oldCrimeSource.setDateGOP("2014-08-29");
                newCrime.setDate("2022-08-28");
            }

            oldCrimeSource.setDatePunishment(1,0, 0);

            oldCrimes.add(oldCrimeSource);

            String recidivusType = Recidivus.getTypeRecidivus(oldCrimes, newCrime);

            Assert.assertEquals("Простой рецидив", recidivusType);
        }
    }
}