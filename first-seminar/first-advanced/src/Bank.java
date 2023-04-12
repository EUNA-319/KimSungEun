public class Bank {
    Account[] accountList = new Account[50]; // 은행별 고객 list
    int bankNumber = 0; // 각 은행은 계좌번호에 고유번호를 부여
    int seq=0;

    Bank(int bankNumber){
        this.bankNumber = bankNumber;
    }

    public int getSeq() {
        return seq;
    }
}
