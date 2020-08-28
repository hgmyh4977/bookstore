package test;

/**
 * description: Demo1 <br>
 * date: 2020/8/15 20:36 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
public class Demo1 {
    public static void main(String[] args) {
        Person person = new Person("zhangSan",22,"male");
        System.out.println(convertToString(person));
    }
    public static String convertToString(Object o ){
        return o.toString();
    }
}
class Person{
    private String name;
    private int age;
    private String sex;

    public Person(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
