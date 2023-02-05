public class Student {
    private String name;
    private String surname;
    private String patronymic;
    private double scholarship;
    private double attend;
    private double miss;
    private int age;
    private String gender;
    private double mark;

    public Student(String name, String surname, String patronymic, double scholarship,
                   double attend, double miss, int age, String gender, double mark) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.scholarship = scholarship;
        this.attend = attend;
        this.miss = miss;
        this.age = age;
        this.gender = gender;
        this.mark = mark;
    }
    public Student() {
        this.name = "Petya";
        this.surname = "Petrov";
        this.patronymic = "Petrovich";
        this.scholarship = 1000;
        this.attend = 50;
        this.miss = 50;
        this.age = 20;
        this.gender = "male";
        this.mark = 4.5;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public double getScholarship() {
        return scholarship;
    }

    public void setScholarship(double scholarship) {
        this.scholarship = scholarship;
    }

    public double getAttend() {
        return attend;
    }

    public void setAttend(double attend) {
        this.attend = attend;
    }

    public double getMiss() {
        return miss;
    }

    public void setMiss(double miss) {
        this.miss = miss;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }


}
