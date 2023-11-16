package com.hjt.javaDemo.collection.domain;

import java.time.LocalDate;
import java.util.List;

public class StudentInfo implements Comparable<StudentInfo> {

    //名称
    private String name;

    //性别 true男 false女
    private Boolean gender;

    //年龄
    private Integer age;

    //身高
    private Double height;

    //出生日期
    private LocalDate birthday;

    public StudentInfo(String name, Boolean gender, Integer age, Double height, LocalDate birthday){
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.birthday = birthday;
    }

    public String toString(){
        String info = String.format("%s\t\t%s\t\t%s\t\t\t%s\t\t%s",this.name,this.gender.toString(),this.age.toString(),this.height.toString(),birthday.toString());
        return info;
    }

    public static void printStudents(List<StudentInfo> studentInfos){
        System.out.println("[姓名]\t\t[性别]\t\t[年龄]\t\t[身高]\t\t[生日]");
        System.out.println("----------------------------------------------------------");
        studentInfos.forEach(s->System.out.println(s.toString()));
        System.out.println(" ");
    }

    @Override
    public int compareTo(StudentInfo ob) {
        return this.age.compareTo(ob.getAge());
        //return 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}

