package zev2020;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Z2 {
/* напишите функция List<Integer> getRandomSubset(List<Integer> list),  возвращающую случаныое множество
произвольного размера.
все подмоножества(включая пустое) должны выбиратьяя с одинаковой вероятностью.
При написагнии использовать лямбда -выражения.
*/


    public static void main(String[] args) {

        List<Integer> list1 = new ArrayList<>();
        list1.add( 1 );
        list1.add( 2 );
        list1.add( 3 );
        list1.add( 4 );
        list1.add( 5 );
        list1.add( 6 );


        System.out.println( "Вывесли лист " + list1.toString() );


        Z2 d=new Z2();
        List<Integer> sub1 = d.getRandomSubset( list1);
        System.out.println( "Вывесли sub1 лист (без лямбда)" + sub1.toString() );


        List<Integer> sub2 = d.getRandomSubset1( list1);
        System.out.println( "Вывесли sub2 лист (лямбда) " + sub2.toString() );


        List<Integer> sub3 = d.getRandomSubset1( list1);
        System.out.println( "Вывесли sub3 лист (лямбда и предикат)" + sub3.toString() );

    }





 ///без лямбда
    List<Integer> getRandomSubset(List<Integer> list)
    {
      List<Integer> subset=new ArrayList<>();
        Random random=new Random();
        for (int item:list)
        {
         /*Да/Нет  равновероятно попадет этот элемент во множество или нет */

            if (random.nextBoolean())
            {subset.add(item);}
        }
        return subset;
    };

/// реализация с лямбда
    List<Integer> getRandomSubset1(List<Integer> list)
    {
        Random random=new Random();
        List<Integer> subset=list.stream().filter( k->{return random.nextBoolean();}).collect( Collectors.toList());
        return  subset;

    };

    /// реализация с лямбда  и предикатом
    List<Integer> getRandomSubset2(List<Integer> list)
    {
        Random random=new Random();
        Predicate<Object> filtr=o->{return random.nextBoolean();};

        List<Integer> subset=list.stream().filter( filtr).collect( Collectors.toList());
        return  subset;

    };








}
