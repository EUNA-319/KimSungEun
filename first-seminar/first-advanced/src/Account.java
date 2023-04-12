public class Account {
    private String account_number; // 계좌번호 -> 앞에 고유 3자리를 통해 은행 식별 가능
    private String owner; // 소유자
    private int balance; // 잔고

    public Account(String account_number, String owner, int balance) {
        this.account_number = account_number;
        this.owner = owner;
        this.balance = balance;
    }

    public String getAccount_number() { // 한 번 발급한 계좌번호는 변경할 수 없으므로 setter X
        return account_number;
    }

    public String getOwner() { // 계좌 소유자는 변경할 수 없으므로 setter X

        return owner;
    }

    public int getBalance() { // 계좌의 총 금액은 입금/출금에 의해서만 변경되어야 하므로 setter X

        return balance;
    }

    public void deposit(int amount){ // 입금

        this.balance+=amount;
    }

    public int withdraw(int amount){ // 출금 시 금액이 부족하면 안되므로 예외처리 필요
        if(this.balance < amount) { // 출금하려는 금액보다 부족하면
            System.out.println("잔액이 부족합니다.");
            return 0;
        }
        else{
            this.balance-=amount;
            return amount; // 얼만큼 출금하였는지 반환
        }
    }

    public int remittance(int amount){
        if(this.balance < amount) { // 송금하려는 금액보다 부족하면
            System.out.println("잔액이 부족합니다.");
            return 0;
        }
        else{
            this.balance-=amount;
            return amount; // 얼만큼 송금하였는지 반환
        }
    }

    @Override
    public String toString() {
        return "{account_number= " + account_number + ", " +
                "owner= " + owner + ", " +
                "balance= " + balance +
                '}';
    }
}
