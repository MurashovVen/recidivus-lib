package ben.mur.recidivus;

public abstract class Crime {
    //категории тяжести
    protected int category;
    //умысел
    protected boolean intent;
    //совершеннолетие
    protected boolean adulthood;

    public void setAdulthood(boolean bool) {
        adulthood = bool;
    }

    public boolean isAdulthood(){
        return adulthood;
    }

    public void setIntent(boolean bool){
        intent = bool;
    }

    public boolean isIntent(){
        return intent;
    }

    public void setCategory(int number){
        this.category = number;
    }

    public int getCategory(){
        return category;
    }
    public String getCategoryText(){
        String result = "ошибка в определении категории";
        switch(category) {
            case 0:
                result= "Небольшая тяжесть";
                break;

            case 1:
                result = "Средняя тяжесть";
                break;

            case 2:
                result =  "Тяжкое";
                break;

            case 3:
                result = "Особо тяжкое";
                break;
        }

        return result;
    }
    /*
     *Определяем категорию преступления.
     * Первый аргумент - максимальное наказание предусмотренное кодексом, исчисляемое в годах
     * Второй аргумент - умысел, true = умышленное, false = неосторожное
     */
    public static int defineCategory(int maxYearsOfDeprivation, boolean intent){
        if(maxYearsOfDeprivation <= 3) {
            return 0;
        } else if ((maxYearsOfDeprivation <= 5 && intent) || (maxYearsOfDeprivation <= 10 && !intent)) {
            return 1;
        } else if ((maxYearsOfDeprivation <= 10 && intent) || (maxYearsOfDeprivation <= 15 && !intent)) {
            return 2;
        } else if(maxYearsOfDeprivation > 10 && intent) {
            return 3;
        }

        return -1;
    }
}
