// 자바의 제네릭: 특정 타입을 사용자의 필요에 의해 지정할 수 있도록하는 일반적인 타입이라는 뜻
// 데이터의 타입검사를 컴파일 단계에서 할 수 있으며 비슷한 기능을 지원하는 경우 코드의 재사용성이 높아진다
// 제네릭으로 지정할 수 있는 타입은 참조타입이다
// 제네릭은 클래스, 인터페이스, 메소드에 사용이 가능하다

class GenericStack<T>{
    int top;
    Object [] stack;
    public GenericStack(){
        top = 0;
        stack = new Object[10];
    }

    void push(T item){
        if(top==10)
            return ;
        stack[top] = item;
        top++;
    }
    T pop(){
        if(top==0)
            return null;
        top--;
        return (T)stack[top]; // 타입 매개변수 타입으로 캐스팅 (중요)
    }
}
public class JavaGeneric {
    public static void main(String[] args){
        GenericStack<Integer> intStack = new GenericStack<>();
        GenericStack<String> stringStack = new GenericStack<>();

        intStack.push(1);
        intStack.push(2);
        intStack.push(3);

        for(int i=0;i<3;i++){
            System.out.println(intStack.pop());
        }

        stringStack.push("가");
        stringStack.push("나");
        stringStack.push("다");

        for(int i=0;i<3;i++){
            System.out.println(stringStack.pop());
        }
    }
}