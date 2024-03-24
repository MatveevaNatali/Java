public class CashbackOperation extends Operation{
    private int cashbackAmount;
// new comment
    public CashbackOperation(int idOfOperation, int idOfCust, String nameOfOperation, int sum, int cashbackAmount){
        super(idOfOperation,idOfCust, nameOfOperation, sum);
        this.cashbackAmount = cashbackAmount;
    }

    //реализуем переопределение print
    @Override
    public void print(){
        System.out.println("Переопределенный метод для кешбэка");
        System.out.println(idOfOperation);
        System.out.println(idOfCust);
        System.out.println(nameOfOperation);
        System.out.println(sum);
        System.out.println(cashbackAmount);
    }
}