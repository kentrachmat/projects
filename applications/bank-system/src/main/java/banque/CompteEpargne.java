package banque;

import banque.exception.DebitBiggerThanException; 

/**
 * A class to design a Savings Account extending the Account class
 *
 * @author Hichem KARFA, Benedictus Kent RACHMAT
 * @version 1.0
 */
public class CompteEpargne extends Compte {

    private Double interet;

    /**
     * CompteEpargne constructor
     * @param pInteret the bank interest
     */
    public CompteEpargne(Double pInteret){
        interet = pInteret;
    }

    /**
     * override the debiter function from the class Compte with additional condition
     * @param value the value to be debited
     * @throws Exception raised when value is 0 / negatif / bigger than the maximum value / bigger than the current balance
     */
    @Override
    public void debiter(double value) throws Exception {
        if(value > this.getSolde()){
            throw new DebitBiggerThanException("Value cannot more than "+ Compte.MAX_CREDIT_DEBIT);
        }
        super.debiter(value);
    }

    /**
     * calculate the bank interest
     * @return the bank interest 
     */
    public Double calculInteret() {
        return this.interet / 100.0 * getSolde();
    }

    /**
     * credit to the bank saving account with the bank interest
     * @throws Exception raised when value is 0 / negatif / bigger than the maximum value  s
     */
    public void echeance() throws Exception {
        crediter(calculInteret());
    }
}
