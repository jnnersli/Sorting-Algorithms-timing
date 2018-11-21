/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpsc200_jli3_3;

/**
 *
 * @author jenny
 */
public class Person implements Comparable<Person>{
    private String name;
    private int number;
    
    public Person(String name, int number) {
        this.name = name;
        this.number = number;
    }
    
    @Override
    //compares numbers
    public int compareTo(Person person) {
        if(this.number > person.number)
            return 1;
        else if (this.number == person.number)
            return 0;
        else
            return -1;
    }
    
    @Override
    public String toString() {
        return(name + number);
    }
}
