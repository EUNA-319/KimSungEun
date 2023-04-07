// 인터페이스: 클래스의 최소한의 틀로 추상 메서드와 상수로만 이루어져 있고 구현된 코드가 없으며 다중 상속이 가능하다
// * 인터페이스는 생성자가 없으며 클래스 간의 결합도를 낮출 수 있다
// * 인터페이스는 인터페이스를 상속받을 수 있으며 자식 인터페이스의 네이밍을 강제하여 객체 간의 통일성을 추구할 수 있다
// * 키워드로는 implements를 사용한다
interface Phone{
    abstract void turnOn(); // 인터페이스에서 abstract 키워드는 생략이 가능하다
    void turnOff();
    void infoMaker();
}

class IPhone implements Phone{

    @Override
    public void turnOn() {
        System.out.println("아이폰이 켜집니다.");
    }

    @Override
    public void turnOff() {
        System.out.println("아이폰이 꺼집니다.");
    }

    @Override
    public void infoMaker() {
        System.out.println("아이폰의 제조사는 애플입니다");
    }
}

class GalaxyPhone implements Phone {
    @Override
    public void turnOn() {
        System.out.println("갤럭시폰이 켜집니다.");
    }

    @Override
    public void turnOff() {
        System.out.println("갤럿시폰이 꺼집니다.");
    }

    @Override
    public void infoMaker() {
        System.out.println("갤럭시폰의 제조사는 삼성입니다");
    }
}
public class JavaInterface {
    public static void main(String[] args) {
        GalaxyPhone phone1 = new GalaxyPhone();
        IPhone phone2 = new IPhone();

        phone1.turnOn();
        phone1.infoMaker();
        phone1.turnOff();

        phone2.turnOn();
        phone2.infoMaker();
        phone2.turnOff();
    }
}
