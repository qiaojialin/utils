package clone;

/**
 * Created by qiaojialin on 2017/6/9.
 */
public class Professor implements Cloneable
{
    String name;
    int age;

    Professor(String name,int age)
    {
        this.name = name;
        this.age = age;
    }

    @Override
    public Professor clone()
    {
        Professor person = null;
        try {
            person=(Professor)super.clone();
        } catch(CloneNotSupportedException e) {
            System.out.println(e.toString());
        }
        return person;
    }

    public static void main(String[] args)
    {
        Professor s1= new Professor("zhangsan",18);
        Professor s2= s1.clone();
        s2.name="lisi";
        s2.age=20;
        //修改学生2后，不影响学生1的值。
        System.out.println("name="+s1.name+","+"age="+s1.age);
        System.out.println("name="+s2.name+","+"age="+s2.age);
    }
}
