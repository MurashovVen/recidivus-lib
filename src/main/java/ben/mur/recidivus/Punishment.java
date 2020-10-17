package ben.mur.recidivus;

import org.joda.time.LocalDate;

import java.io.Serializable;
/*
 * Класс описывает назначенное наказание за совершенное преступление
 */
public class Punishment implements Serializable {
    /*
     * массив ссылок на подпункты статьи УК РФ, регламентирующие виды наказаний
     */
    final static String[] TYPES_PUNISHMENTS ={"п. а ст. 44 УК РФ", "п. б ст. 44 УК РФ", "п. в ст. 44 УК РФ", "п. г ст. 44 УК РФ", "п. д ст. 44 УК РФ",
            "п. е ст. 44 УК РФ", "п. ж ст. 44 УК РФ", "п. з ст. 44 УК РФ", "п. з.1 ст. 44 УК РФ", "п. и ст. 44 УК РФ",
            "п. к ст. 44 УК РФ", "п. л ст. 44 УК РФ", "п. м ст. 44 УК РФ", "п. н ст. 44 УК РФ"};
    final public static String[] TYPES_PUNISHMENTS_TEXT={"Штраф", "Лишение права занимать определенные должности или заниматься определенной деятельностью",
            "Лишение специального, воинского или почетного звания, классного чина и государственных наград", "Обязательные работы",
            "Исправительные работы", "Ограничение по военной службе", "Утратил силу(ф.з. от 08.12.2003 № 162-ФЗ",
            "Ограничение свободы", "Принудительные работы", "Арест", "Содержание в дисциплинарной воинской части", "Лишение свободы на определенный срок"};
    /*
     * суть в том, что некоторые виды наказаний исчисляются в днях, месяцах и годах, а некоторые в иных величинах.
     * для определения рецидива при назначенном лишении свободы нам необходимо знать количество дней, месяцев и лет,
     * при назначении иных видов наказаний, никакие иные свойства избранного наказания, кроме типа, не важны.
     */
    private int years;
    private int months;
    private int days;
    // день начала отбытия отбытия наказания
    private LocalDate startDate;               
    // срок прекращения
    protected LocalDate endDate;            
    // вид наказания
    private int typePunishment;    

    public void setStartDate(String date){
        this.startDate = new LocalDate(date);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(int years, int months, int days ) {
        this.endDate = startDate.plusYears(years).plusMonths(months).plusDays(days);
    }

    public void setEndDate(String date){
        this.endDate = new LocalDate(date);
    }

    public void setDatePunishment(int years, int months, int days){
        this.years = years;
        this.months = months;
        this.days = days;
        setEndDate(years, months, days);
    }
    
    public int getYears(){
        return this.years;
    }

    public int getMonths(){
        return this.months;
    }

    public int getDays(){
        return this.days;
    }

    /*
     * Нумерация типов происходит в соответствии с константным массивом ссылок на УК РФ "TYPES_PUNISHMENTS"
     */
    public void setTypePunishment(int number){
        this.typePunishment = number;
    }

    public int getTypePunishment(){
        return typePunishment;
    }
}

