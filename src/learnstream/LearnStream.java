/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnstream;

import static java.util.Arrays.asList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author aleksandr.akimov
 */
public class LearnStream {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        List<Person> persons = asList(new Person("Joe", 23),
                                    new Person("Jim", 18),
                                    new Person("sdf", 45),
                                    new Person("John", 67));
        

        List<Person>  teenagers = persons.stream().
                filter(p -> p.getAge() <=18).
                collect(Collectors.toList());
        

       List<Teenager> teensList = persons.stream().
               map(Function.identity()).// с идентити
               map(x->x). // без идентити
               map(p -> new Teenager(p)).
               collect(Collectors.toList());
        System.out.println(teenagers.get(0).getName());
        System.out.println(teensList.get(0).getName());
        
        
       Map<String,Person> capsSeniors = new HashMap<>();
       capsSeniors=persons.stream().
               filter(p -> p.getAge() >=50).
               collect(Collectors.toMap(p-> p.getName().toUpperCase(), p->p));
        
       
       final Comparator<Person> comp = (p1,p2) -> Integer.compare(p1.getAge(), p2.getAge());
       Person oldestPerson = persons.stream().
               max(comp).get();
               
        System.out.println(oldestPerson);
        
        // надо заюзать как то
        Map<String,Person> SeniorsByKey = mapSortedByKey(capsSeniors);
        Map<String,Person> SeniorsByValue = mapSortedByValue(capsSeniors);
        
        
    }
    
    
    public static <K extends Comparable<? super K>, V> Map<K,V> mapSortedByKey(Map<K,V> argMap)
    {
        Map<K,V> mapResult = new LinkedHashMap<>();
        Stream<Map.Entry<K,V>> sequenticalStream = argMap.entrySet().stream();
        
        // comparingByKey() returns a comparetor tjat compares Map.Entry in natural order on key;
        argMap
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(c-> mapResult.put(c.getKey(), c.getValue()));
        return mapResult;
    }
    
    
    public static <K , V extends Comparable<? super V>> Map<K,V> mapSortedByValue(Map<K,V> argMap)
    {
        Map<K,V> mapResult = new LinkedHashMap<>();
        Stream<Map.Entry<K,V>> sequenticalStream = argMap.entrySet().stream();
        
        // comparingByKey() returns a comparetor tjat compares Map.Entry in natural order on key;
        argMap
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(c-> mapResult.put(c.getKey(), c.getValue()));
        return mapResult;
    }
    
    public Comparator<Person> byNameLowercase = (t1,t2)-> t1.getName().toLowerCase()
            .compareTo(t2.getName().toLowerCase());
    
    public Comparator<Person> byAge = (t1,t2)-> t1.getAge()
            .compareTo(t2.getAge());
    
}
