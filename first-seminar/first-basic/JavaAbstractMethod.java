// 추상메서드: 추상 메서드를 포함하는 클래스를 추상 클래스라고하며, abstract 키워드는 클래스나 메서드명 앞에 붙을 수 있다
// 추상클래스는 즉, 내부에 추상 메서드가 있으니 상속받는 자식 클래스에서 구현하라는 의미를 내포하고 있다
// * 추상 클래스 자체를 new 키워드를 통해 인스턴스 객체로 만드는 것은 불가능하다
abstract class Animal{
    public String kind;
    abstract void infoKind();
    abstract void sound();
}

class Cat extends Animal{
    Cat(){
        this.kind = "포유류";
    }
    @Override
    void infoKind() {
        System.out.println("kind: "+kind);
    }
    void sound() {
        System.out.println("야옹");
    }
}

class Fish extends Animal {
    public Fish() {
        this.kind = "어류";
    }
    @Override
    void infoKind() {
        System.out.println("kind: "+kind);
    }
    @Override
    void sound() {
        System.out.println("뻐끔뻐금");
    }
}
public class JavaAbstractMethod {
    public static void main(String[] args) {
        Cat animal1 = new Cat();
        Fish animal2 = new Fish();

        animal1.infoKind();
        animal1.sound();
        animal2.infoKind();;
        animal2.sound();

    }
}

// 추상 클래스는 자식 클래스가 부모 클래스와 속성과 행위를 물려받고 더 나아가 기능을 확장시키는 역할을 한다
// 그러나 인터페이스는 함수의 틀만 존재하며 함수의 구현을 상속받는 클래스에서 강제하기 위한다는 강제성을 지닌다