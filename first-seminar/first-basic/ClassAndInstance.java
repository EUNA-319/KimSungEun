// 추상화: 불필요한 세부 사항들을 제거한 후, 우리가 필요한 정보에 따라 가장 본질적이고 공통적인 부분만을 추출하여 표현
class Student {// Student 클래스 생성
    // 클래스는 속성과 행위를 멤버변수와 메서드로 정의할 수 있음
    // 학생에 대한 정보는 다음과 같이 아래 세가지 속성(studentId, name, major)으로 정의

    // * 자바의 캡슐화: 클래스의 속성과 행위를 하나의 캡슐로 만들어 데이터를 외부로부터 보호하는 방법
    // * 접근 제어자의 종류: public, private, protected, default
    private String studentId;
    private String name;
    private String major;

    // 클래스의 생성자는 new 연산자를 통해 클래스의 인스턴스를 생성할 때 먼저 호출이 되는 메서드
    // 생성자가 없다면 중복되는 코드가 많아질 수 있음
    // 해당 예시에서 학생이 여러 명이라면 각각의 학번, 이름, 전공을 따로따로 설정할 경우 중복되는 코드가 많아짐
    // 이럴 때 아래와 같이 생성자를 활용하면 깔끔하게 사용할 수 있음
    public Student(String studentId, String name, String major){ // 외부에서 값을 받아 생성자를 통해 클래스 내부 변수에 값을 셋팅
        this.studentId = studentId;
        this.name = name;
        this.major = major;
    }

    // 아래 세 가지 행위(enrolment, assignment, study)를 메서드로 정의
    public void enrolment(){
        System.out.println("학생이 수강신청을 합니다");
    }
    public void assignment() {
        System.out.println("학생이 과제를 합니다");
    }

    public void study() {
        System.out.println("학생이 공부를 합니다");
    }

    public void studentInfo(){
        System.out.print("학번: " + studentId+", "+"이름: "+name+", "+"전공: "+major);
    }
}

class CollegeOfEngineering extends Student{
    // 상속: 기존에 정의된 클래스를 이어받고, 자신만의 속성과 행위를 추가하여 새로운 클래스를 정의하는 것
    // 각 단과대학 학생들을 생성하기 위해 Student 클래스를 상속받은 공과대학 클래스를 생성
    // 공과대학 학생들은 각자 배정받은 실습실이 있다고 가정하여 추가적인 속성을 정의한다

    private String lab;
    public CollegeOfEngineering(String studentId, String name, String major, String lab) {
        super(studentId,name,major);
        this.lab = lab;
    }

    // Student에 정의되어 있지 않은 training(실습) 행위를 정의
    public void training(){
        System.out.println("학생이 실습을 합니다");
    }

    // 공과대학 학생의 정보는 실습실의 정보를 추가하기위해 아래와 같이 새로운 행위를 정의할 수 있으나 해당 기능은 StudentInfo에 약간의 기능이 추가됨
    // 이럴 경우에 부모 클래스의 메서드 동작을 재정의하는 오버라이딩을 사용할 수 있음
    /*
    public void engineeringStudentInfo(){
        System.out.println("학번: " + studentId+", "+"이름: "+name+", "+"전공: "+major+", "+"실습실: "+lab);
    }
     */

    // studentInfo override
    @Override
    public void studentInfo(){
        super.studentInfo();
        System.out.print(", "+"실습실: "+lab);
    }

    // 오버라이딩은 자바의 다형성이라는 개념과 관련이 있으며 그 안에는 오버라이딩 외에도 오버로딩이라는 개념이 포함됨
    // ex) 계산기에서의 덧셈은 정수형 덧셈도 있을 수 있지만 실수형 덧셈도 있을 수 있음 -> 이럴 경우 인자의 타입을 다르게함으로써 메서드의 오버로딩이 가능하다
}

public class ClassAndInstance {
    public static void main(String[] args) {
        // 클래스와 인스턴스 - Student 클래스의 객체 생성
        Student student1 = new Student("2311340", "눈송이", "경제학부");
        student1.studentInfo();
        System.out.println();

        // 상속받은 CollegeOfEngineering 클래스 객체 생성 및 오버라이딩 확인
        CollegeOfEngineering student2 = new CollegeOfEngineering("2011340","김성은","컴퓨터과학전공","명신관 404B");
        student2.studentInfo();
        System.out.println();
    }
}


