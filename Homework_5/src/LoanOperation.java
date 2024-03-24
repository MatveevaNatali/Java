public class LoanOperation extends Operation {
    private int loanId;

    public LoanOperation(int idOfOperation, int idOfCust, String nameOfOperation, int sum, int loanId){
        super(idOfOperation,idOfCust, nameOfOperation, sum);
        this.loanId = loanId;
    }
}