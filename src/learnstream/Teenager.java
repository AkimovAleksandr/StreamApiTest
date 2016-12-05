/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnstream;

/**
 *
 * @author aleksandr.akimov
 */
public class Teenager extends Person{

    public Teenager(String name, Integer age) {
        super(name, age);
    }

    public Teenager(Person person) {
        super(person.getName(), person.getAge());
    }

    
    
}
