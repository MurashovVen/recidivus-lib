package ben.mur.recidivus;

import java.util.List;

/*
 * Класс позволяет определить наличие рецедива одного преступления,
 * и типа рецидива или его отсутствие группы преступлений
 */
public class Recidivus {
    /*
     * части 1, 2, 3 статьи 18 УК РФ.
     */
    public static String getTypeRecidivus(List<OldCrime> oldCrimes, NewCrime newCrime){

        boolean danger = false;
        boolean realDanger = false;
        boolean simple = false;
        int numCategory1RD = countCategory(1, true, oldCrimes, newCrime);
        int numCategory2RD = countCategory(2, true, oldCrimes, newCrime);
        int numCategory3RD = countCategory(3, true, oldCrimes, newCrime);
        int numCategory2 = countCategory(2, false, oldCrimes, newCrime);
        int numCategory3 = countCategory(3, false, oldCrimes, newCrime);

        if ((numCategory1RD >= 2 && newCrime.getCategory() == 2 && newCrime.isRealDeprivation())
                || (((numCategory2RD >= 1) || numCategory3RD >= 1) && newCrime.getCategory() == 2)) {
            danger = true;
        }
        if ((numCategory2RD >= 2 && newCrime.getCategory() == 2 && newCrime.isRealDeprivation())
                || ((numCategory2 >= 2 || numCategory3 >= 1) && newCrime.getCategory() == 3)
                || numCategory3 >= 2 && newCrime.getCategory()==2) {
            realDanger = true;
        }

        for (int i = 0; i< oldCrimes.size(); i++){
            if (isRecidivus(oldCrimes, newCrime, i)){
                simple = true;
                break;
            }
        }

        if (realDanger) {
            return "Особо опасный рецидив";
        } else if(danger){
            return "Опасный рецидив";
        } else if (simple) {
            return "Простой рецидив";
        } else {
            return "Рецидива нет";
        }
    }

    /*
     * считаем количество рецидивных преступлений по категориям в соответствии со статьей 18 УК РФ
     */
    protected static int countCategory(int catNumKey, boolean realDeprivated, List<OldCrime> oldCrimes, NewCrime newCrime){
        int result = 0;
        for (int i = 0; i < oldCrimes.size(); i++){
            int catNum = oldCrimes.get(i).getCategory();
            boolean recidivus = isRecidivus(oldCrimes, newCrime, i);

            if(realDeprivated) {
                if (catNum == catNumKey && recidivus
                        && oldCrimes.get(i).getTypePunishment() == 11) {
                    result++;
                }
            } else if (catNum == catNumKey && recidivus) {
                result++;
            }
        }
        return result;
    }

    /*
     * определяем, есть ли вообще рецидив по конкретному преступлению (ч.1 ст. 18 УК РФ + пленум по рецидиву СССР)
     */
    public static boolean isRecidivus(List<OldCrime> oldCrimes, NewCrime newCrime, int num){
        boolean GOP = oldCrimes.get(num).isGOP();
        boolean servitude = oldCrimes.get(num).isServitude();
        boolean punishment023 = oldCrimes.get(num).isPunishment023();
        boolean considerNC = newCrime.isConsider();
        boolean considerOC = oldCrimes.get(num).isConsider();

        if (!GOP && !servitude && punishment023 && considerNC && considerOC){
            return true;
        } else if(considerNC && considerOC) {
            int result = oldCrimes.get(num).getEndCriminalRecord().compareTo(newCrime.getDate());

            return result >= 0;
        }
        return false;
    }
}
