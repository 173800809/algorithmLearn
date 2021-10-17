import com.sun.istack.internal.NotNull;

import java.util.Date;

public class Student {


    private String name;
    private int age;
    private int stature;
    private Date time;
    //todo :下面应该使用List
    private SpecialityEnum specialities;

    public Student(String name, int age, int stature) {
        this.name = name;
        this.age = age;
        this.stature = stature;
    }

    public Student(String name, int age, int stature, SpecialityEnum specialities) {
        this.name = name;
        this.age = age;
        this.stature = stature;
        this.specialities = specialities;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Student(String name, int age, int stature, Date time, SpecialityEnum specialities) {
        this.name = name;
        this.age = age;
        this.stature = stature;
        this.time = time;
        this.specialities = specialities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getStature() {
        return stature;
    }

    public void setStature(int stature) {
        this.stature = stature;
    }

    public SpecialityEnum getSpecialities() {
        return specialities;
    }

    public void setSpecialities(SpecialityEnum specialities) {
        this.specialities = specialities;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", stature=" + stature +
                ", time=" + time +
                ", specialities=" + specialities +
                '}';
    }
}
