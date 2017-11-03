package visitor;

/**
 * Created by qiaojialin on 2017/11/3.
 */

interface Node{
    void accept(IVisitor visitor);
}

class Body implements Node{
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

class Engine implements Node{
    public  void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

class Wheel implements Node{
    private String name;
    public Wheel(String name) {
        this.name = name;
    }
    String getName() {
        return this.name;
    }
    public  void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

class Car implements Node{
    private Engine  engine = new Engine();
    private Body    body   = new Body();
    private Wheel[] wheels
            = { new Wheel("front left"), new Wheel("front right"),
            new Wheel("back left") , new Wheel("back right")  };

    public void accept(IVisitor visitor) {
        visitor.visit(this);
        engine.accept(visitor);
        body.accept(visitor) ;
        for (int i = 0; i < wheels.length; ++ i)
            wheels[i].accept(visitor);
    }
}

interface IVisitor {
    void visit(Wheel wheel);
    void visit(Engine engine);
    void visit(Body body);
    void visit(Car car);
}

class PrintVisitor implements IVisitor {

    @Override
    public void visit(Wheel wheel) {
        System.out.println("Visiting " + wheel.getName() + " wheel");

    }

    @Override
    public void visit(Engine engine) {
        System.out.println("Visiting engine");
    }

    @Override
    public void visit(Body body) {
        System.out.println("Visiting body");
    }

    @Override
    public void visit(Car car) {
        System.out.println("Visiting car");
    }

}

public class Test{

    public static void main(String[] args) {
        Car car = new Car();
        car.accept(new PrintVisitor());
    }
}
