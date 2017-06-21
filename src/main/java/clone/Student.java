package clone;

/**
 * Created by qiaojialin on 2017/6/9.
 */
public class Student implements Cloneable
{
    private String name;
    private int age;
    private Professor p;

    Student(String name,int age,Professor p)
    {
        this.name=name;
        this.age=age;
        this.p=p;
    }

    public Object clone()
    {
        Student o = null;
        try {
            o=(Student)super.clone();
            o.p = p.clone();
        } catch(CloneNotSupportedException e) {
            System.out.println(e.toString());
        }
        return o;
    }

    public static void main(String[] args)
    {
        Professor p = new Professor("wangwu", 50);
        Student s1 = new Student("zhangsan", 18, p);
        Student s2 = (Student)s1.clone();
        s2.p.name = "lisi";
        s2.p.age = 30;
        //学生1的教授不改变。
        System.out.println("name="+s1.p.name+","+"age="+s1.p.age);
        System.out.println("name="+s2.p.name+","+"age="+s2.p.age);
    }
}