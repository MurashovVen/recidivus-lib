package ben.mur.recidivus;

import org.joda.time.LocalDate;
import java.io.Serializable;

/*
 * класс описывает преступление, которое совершено недавно, за которое еще не назначено наказание
 * и которое будет классифицироваться как рецидивное или не рецедивное.
 */
public class NewCrime extends Crime implements Serializable {
    // дата совершения преступления
    private LocalDate date;
    // назначенное наказание - реальное лишение свободы
    private boolean realDeprivation;

    /*
     * Проверяем, учитывается ли при определении рецидива.
     * При рецидиве учитываются только умышленные преступления, совершенные совершеннолетним
     */
    public boolean isConsider() {
        return adulthood && intent;
    }

    public void setRealDeprivation(boolean isRealDeprivation){
        realDeprivation = isRealDeprivation;
    }

    public boolean isRealDeprivation(){
        return realDeprivation;
    }

    public void setDate(String date){
        this.date = new LocalDate(date);
    }

    public LocalDate getDate() {
        return date;
    }
}
