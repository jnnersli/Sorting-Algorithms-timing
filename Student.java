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
public class Student implements Comparable<Student>{
    private String name;
    private int number;
    
    public Student(String name, int number) {
        this.name = name;
        this.number = number;
    }
    
    @Override
    //compares names
    public int compareTo(Student student) {
        return this.name.compareTo(student.name);
    }
    
    @Override
    public String toString() {
        return(name + number);
    }
}
