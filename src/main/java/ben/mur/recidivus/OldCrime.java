package ben.mur.recidivus;

import org.joda.time.LocalDate;
import org.joda.time.Period;

import java.io.Serializable;

/*
 * класс описывает преступление, совершенное лицом раннее, за которое уже назначено и отбыто определенное наказание
 */
public class OldCrime extends Crime implements Serializable {
    final public static String[] CATEGORY = {"Небольшая тяжесть", "Средняя тяжесть", "Тяжкое", "Особо тяжкое"};

    private final Punishment punishment;
    //Исполнено ли наказание
    private boolean servitude;
    // умысел
    // УО/ОИ
    private boolean conditional;
    //учет изменений фз 2013 в 86 ст. УК РФ
    private boolean law2013;
    //снималась ли судимость досрочно
    private boolean earlyRepayment;
    // дата снятия судимости
    private LocalDate endCriminalRecord;
    //УДО
    private boolean GOP;
    //дата УДО
    private LocalDate dateGOP;

    public OldCrime(){
        punishment = new Punishment();
    }

    public void setDateGOP(String date){dateGOP = new LocalDate(date);}

    public LocalDate getDateGOP(){return dateGOP;}

    public void setGOP(boolean bool){GOP = bool;}

    public boolean isGOP(){return GOP;}

    public void setEarlyRepayment(boolean bool){ earlyRepayment = bool;}

    public boolean isEarlyRepayment(){ return earlyRepayment;}

    public void setLaw2013(boolean bool){ law2013 = bool;}

    public boolean isLaw2013(){return law2013;}

    public void setConditional(boolean bool){ conditional = bool;}

    public boolean isConditional(){ return conditional;}

    private void setEndCriminalRecord(){
        switch (this.getTypePunishment()) {

            /*
             * В зависимости от вида назначенного наказания, дата снятия судимости исчисляется по-разному
             */
            case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9: case 10:
                if(GOP) {
                    endCriminalRecord=this.dateGOP.plusYears(1);// не определяет нормально
                } else {
                    endCriminalRecord = this.punishment.endDate.plusYears(1);
                }
                break;
            case 11:
                /*
                 * количество лет наличия судмости зависит от категории тяжести совершенного преступления
                 */
                int tempCategory = category;
                LocalDate tempEndDate =punishment.endDate;
                /*
                 * Но при наличии УДО категория тяжести исчисляется не в зависимости от
                 * фактической категории тяжести совершенного деяния
                 * а в зависимости от количества отбытых в местах лишения свободы лет
                 */
                if(GOP){
                    Period period = new Period(punishment.getStartDate(), dateGOP);
                    int periodYears = period.getYears();
                    int periodMonths = period.getMonths();
                    int periodDays = period.getDays();

                    if (periodYears < 3 || (periodYears == 3 && periodMonths == 0 && periodDays == 0 )) {
                        tempCategory = 0;
                    } else if (periodYears < 5 || (periodYears == 5 && periodMonths == 0 && periodDays == 0 )) {
                        tempCategory = 1;
                    } else if (periodYears < 10 || (periodYears == 10 && periodMonths == 0 && periodDays == 0 )) {
                        tempCategory = 2;
                    } else if(periodYears > 10 || (periodYears == 10 && (periodMonths > 0 || periodDays > 0) )) {
                        tempCategory = 3;
                    }

                    tempEndDate=dateGOP;
                }

                if (tempCategory == 1 || tempCategory == 0) {
                    endCriminalRecord= tempEndDate.plusYears(3);
                } else if (isLaw2013()) {
                    if(tempCategory == 2) {
                        endCriminalRecord = tempEndDate.plusYears(6);
                    } else if (tempCategory == 3) {
                        endCriminalRecord= tempEndDate.plusYears(8);
                    }
                } else if(tempCategory == 2) {
                    endCriminalRecord = tempEndDate.plusYears(8);
                } else if (tempCategory == 3) {
                    endCriminalRecord= tempEndDate.plusYears(10);
                }
                break;
        }
    }

    /*
     * мы обрашаемся к дате снятия судимости только в случаях, когда присутствуют все необходимые данные для ее определения
     */
    protected LocalDate getEndCriminalRecord() {
        setEndCriminalRecord();

        return endCriminalRecord;
    }

    public String getTag() {
        return (isPunishment023() && !servitude)? "Наказание не исполнено" : ("Дата снятия судимости: " + getEndCriminalRecord());
    }

    public String getTermPunishment() {
        int years = punishment.getYears();
        int months = punishment.getMonths();

        return years + (years >= 5 || years ==0? " лет, " : (years == 1? " год, ":" года, "))
                + months + ((((months >= 5) && (months <= 12)) || months == 0)?" месяцев.":(months%10 == 1? " месяц.":" месяца."));

    }

    public void setServitude(boolean bool){
        servitude = bool;
    }

    public boolean isServitude(){
        return servitude;
    }

    public boolean isPunishment023() {
        int punishment = getTypePunishment();

        return punishment == 0 || punishment == 2 || punishment == 3;
    }


    public boolean isConsider() {
        /*
         * при определении рецидива учитываются только умышленные преступления, совершенные совершеннолетним,
         * не небольшой тяжести, без досрочного снятия судимости без УО/ОИ
         * ч.1 ст. 18 УК РФ
         */
        return intent && adulthood && !conditional && (category != 0) && !earlyRepayment;
    }

    public void setStartDatePunishment(String date){
        this.punishment.setStartDate(date);
    }

    public void setDatePunishment(int years,int months,int days) {
        this.punishment.setDatePunishment(years,months,days);
    }

    public void setEndDatePunishment(String date){
        this.punishment.setEndDate(date);
    }

    public Punishment getPunishment(){
        return punishment;
    }

    public void setTypePunishment(int number){
        this.punishment.setTypePunishment(number);
    }

    public int getTypePunishment(){
        return punishment.getTypePunishment();
    }

    public String getTextPunishment(){
        return Punishment.TYPES_PUNISHMENTS_TEXT[this.getTypePunishment()];
    }
}




















