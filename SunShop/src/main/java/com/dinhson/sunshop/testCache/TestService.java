package com.dinhson.sunshop.testCache;

import org.hibernate.annotations.Cascade;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

    private static List<Student> students = new ArrayList<>();

    @Cacheable("students")
    public List<Student> getAll(){
        students.add(new Student(1, "dinh son"));
        students.add(new Student(2, "dinh son"));
        students.add(new Student(3, "dinh son"));
        students.add(new Student(4, "dinh son"));
        students.add(new Student(5, "dinh son"));
        System.out.println("lay toan bo lan");
        return students;
    }

    @Cacheable("student")
    public Student getById(int id){

        System.out.println("lay id: " + id);
        return students.stream().filter(s -> s.getId() == id).findFirst().get();
    }

    //@CachePut("student")
    public Student updateById(int id, String name){
        System.out.println("update " + id + name);
        Student s = getById(id);
        s.setName(name);
        return s;
    }

    @CacheEvict(value = "students", allEntries = true)
    public void cleanCache(){}


    public Student addNewStudent(int id, String name){
        Student s = new Student(id, name);
        students.add(s);
        System.out.println("addd");
        //cleanCache();
        return s;
    }
}
