package zev2020;

/*
Существует класс Country, содержащий метода getContinent() и getPopulation() .
Найти функция int getPopelation(List<Country>  countries, String continent), которая
вычисляет населени заданного континента по списку всех стран и названию континетна,
 используя лямбда-выражения
 */


import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Z1 {

    public static void main(String[] args) {

        Country world=new  Country("",0);
        Country russian=new  Country("Evrazia",6000000);
        Country china=new  Country("Evrazia",10000000);
        Country canada=new  Country("NorthAmerica",2000000);
        Country usa=new  Country("NorthAmerica",20000000);
        Country brazilia=new  Country("SouthAmerica",5000000);
        Country egypt=new  Country("Africa",5000000);

        List<Country> countryList=new LinkedList<>( );
        countryList.add(russian);
        countryList.add(china);
        countryList.add(canada);
        countryList.add(usa);
        countryList.add(brazilia);
        countryList.add(egypt);


        System.out.println("1. Evrazia: "+ world.getPopulation( countryList,"Evrazia" ) );
        System.out.println("2. Evrazia: "+ world.getPopulation2( countryList,"Evrazia" ) );

        System.out.println("1. NorthAmerica: "+ world.getPopulation( countryList,"NorthAmerica" ) );
        System.out.println("2. NorthAmerica: "+ world.getPopulation2( countryList,"NorthAmerica" ) );


    }


}

class Country{

    String continent;
    int population;

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }


    public Country(String continent, int population) {
        this.continent = continent;
        this.population = population;
    }

    //--------------------вычисляем по странам--------------------------
    int getPopulation(List<Country> countries, String continent)
    {
        int sum=0;
        for (Country c:countries)
        {if (c.getContinent().equals( continent ))
        sum+=c.getPopulation();
        }

        return sum;
    }

    //----------варинат с лямбда---------------------
    int getPopulation2(List<Country> countries, String continent)
    {
        /*Фильтр списка стран*/
        Stream<Country> sublist=countries.stream().filter(
                country->{return country.getContinent().equals( continent );}
                );

     /* Преобразование в список численности населения*/
        Stream<Integer> populations=sublist.map( c->c.getPopulation() );

        /* Суммирование списка*/
        int population = populations.reduce( 0,(a,b)->a+b );


        return population;
    }



}