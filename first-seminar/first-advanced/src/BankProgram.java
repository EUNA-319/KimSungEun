import java.text.DecimalFormat;
import java.util.Scanner;

public class BankProgram {
    private static Bank CaCaO = new Bank(333); // 은행별 고객 list
    private static Bank NewHan = new Bank(110);
    private static Bank HaNa = new Bank(357);
    private static Bank temp; // 가입된 고객의 은행 정보를 저장
    private static int bankcharge = 1000; // 타행 송금시 부과되는 수수료는 1000원임
    private static Scanner sc = new Scanner(System.in);
    private static int createAccount() {
        String ano;
        String name;
        int bankno;
        int balance;
        System.out.print("계좌 개설 은행을 선택하세요 [1] CaCaO, [2]NewHan, [3]HaNa: ");
        bankno = sc.nextInt();
        switch(bankno){
            case 1: temp=CaCaO; break;
            case 2: temp=NewHan; break;
            case 3: temp=HaNa; break;
        }

        System.out.print("소유주명을 입력하세요: ");
        name = sc.next();
        System.out.print("초기 입금액을 입력하세요: ");
        balance = sc.nextInt();
        if(temp.seq == 50){
            System.out.println("해당 은행에 가입자수가 너무 많습니다.");
        }
        else {
            for (int i = 0; i < temp.accountList.length; i++) {
                if (temp.accountList[i] == null) {
                    ano = temp.bankNumber + "-" + String.format(new DecimalFormat("0000").format(++temp.seq));
                    temp.accountList[i] = new Account(ano, name, balance);
                    System.out.println("계좌가 개설되었습니다");
                    break;
                }
            }
            return 1;
        }
        return 0;
    }
    private static void accountList(){
        int find=0;
        System.out.print("목록을 확인할 은행을 선택하세요 [1] CaCaO, [2]NewHan, [3]HaNa: ");
        find = sc.nextInt();
        switch(find){
            case 1: temp=CaCaO; break;
            case 2: temp=NewHan; break;
            case 3: temp=HaNa; break;
        }
        for(int i=0;i<temp.getSeq();i++){
            System.out.println(temp.accountList[i].toString());
        }
    }
    private static void deposit(){
        String inano;
        Account inaccount;
        int inmoney;
        System.out.print("입금할 계좌번호를 입력하세요: ");
        inano = sc.next();
        switch(inano.substring(0,3)){
            case "333": temp=CaCaO; break;
            case "110": temp=NewHan; break;
            case "357": temp=HaNa; break;
        }
        inaccount = findAccount(inano,temp);
        if(inaccount!=null){
            System.out.print("입금할 금액을 입력하세요: ");
            inmoney = sc.nextInt();
            inaccount.deposit(inmoney);
        }
        else{
            System.out.println("해당하는 계좌번호가 없습니다.");
        }
    }
    private static void withdraw() {
        String outano;
        int outmoney;
        int result;
        Account outaccount;
        System.out.print("출금할 계좌번호를 입력하세요: ");
        outano = sc.next();
        switch(outano.substring(0,3)){
            case "333": temp=CaCaO; break;
            case "110": temp=NewHan; break;
            case "357": temp=HaNa; break;
        }
        outaccount = findAccount(outano,temp);
        if(outano!=null){
            System.out.print("출금할 금액을 입력하세요: ");
            outmoney = sc.nextInt();
            result = outaccount.withdraw(outmoney);
            if(result==0){
                System.out.println("출금에 실패했습니다.");
            }
        }
        else{
            System.out.println("해당하는 계좌번호가 없습니다.");
        }
    }

    private static int remittance() {
        String outano;
        String inano;
        Account outaccount;
        Account inaccount;
        int money;
        int result;

        System.out.print("출금할 계좌번호를 입력하세요: ");
        outano = sc.next();
        switch(outano.substring(0,3)){
            case "333": temp=CaCaO; break;
            case "110": temp=NewHan; break;
            case "357": temp=HaNa; break;
        }
        outaccount = findAccount(outano, temp);
        if(outaccount!=null) {
            System.out.print("송금할 계좌번호를 입력하세요: ");
            inano = sc.next();
            switch(inano.substring(0,3)){
                case "333": temp=CaCaO; break;
                case "110": temp=NewHan; break;
                case "357": temp=HaNa; break;
            }
            inaccount = findAccount(inano,temp);
            System.out.print("송금할 금액을 입력하세요: ");
            money = sc.nextInt();
            if (outano.substring(0, 3).equals(inano.substring(0, 3))) {
                result=outaccount.remittance(money);
                if(result==0){
                    System.out.println("송금에 실패했습니다.");
                    return 0;
                }
                inaccount.deposit(money);
            }
            else{
                System.out.println("타행 송금시 수수료 1000원이 부과됩니다");
                outaccount.remittance(money+bankcharge);
                inaccount.deposit(money);
            }
            return 1;
        }
        else{
            System.out.println("송금에 실패했습니다. 계좌번호를 확인하세요.");
            return 0;
        }
    }

    private static Account findAccount(String ano, Bank find){
        Account account;
        for(int i=0;i<find.getSeq();i++){
            if(find.accountList[i]!=null){
                if(find.accountList[i].getAccount_number().equals(ano)){
                    account = find.accountList[i];
                    return account;
                }
            }
        }
        return null;
    }

    public static void main(String [] args){
        int selectNo;
        boolean run = true;
        Scanner scan = new Scanner(System.in);

        System.out.println("==========================[은행프로그램]==========================");
        while(run){
            System.out.println("------------------------------------------------------------");
            System.out.println("1. 계좌생성 | 2. 계좌목록 | 3. 예금 | 4. 출금 | 5. 송금 | 6. 종료 ");
            System.out.print("선택: ");
            selectNo = scan.nextInt();
            switch(selectNo) {
                case 1: createAccount(); break;
                case 2: accountList(); break;
                case 3: deposit(); break;
                case 4: withdraw(); break;
                case 5: remittance(); break;
                case 6: run=false; break;
            }
            System.out.println("------------------------------------------------------------");
        }
        System.out.println("============================================================");
    }
}
