package banque;

import banque.exception.NegatifNumberException;
import banque.exception.OverLimitNumberException;
import banque.exception.ZeroNumberException;

import java.util.ArrayList;

/**
 * A class to design a Bank account
 *
 * @author Hichem KARFA, Benedictus Kent RACHMAT
 * @version 1.0
 */
public class Compte {

    /** the maximum length for the transaction history */
    private final int STRUCT_LENGTH = 5;

    /** the initial balance */
    public static final double SOLDE_INIT = 0;

    /** the maximum value that the account can debit/credit */
    public static final int MAX_CREDIT_DEBIT = 100000;

    private ArrayList<Double> debit;
    private ArrayList<Double> credit;

    /**
     * Compte constructor
     */
    public Compte() {
        this.credit = new ArrayList<>();
        this.debit = new ArrayList<>();
        for(int i = 0 ; i < STRUCT_LENGTH ; i++) {
            this.debit.add(SOLDE_INIT);
            this.credit.add(SOLDE_INIT);
        }
    }

    /**
     * summing the debit transaction
     * @return the sum of the debit transaction
     */
    public double getDebit() {
        double result = 0.0;
        for (double value : this.debit) {
            result += value;
        }
        return result;
    }

    /**
     * summing the credit transaction
     * @return the sum of the credit transaction
     */
    public double getCredit() {
        double result = 0.0;
        for (double value : this.credit) {
            result += value;
        }
        return result;
    }

    /**
     * return the account balance
     * @return the account balance
     */
    public double getSolde() {
        return getCredit() - getDebit();
    }

    /**
     * credit the account by a given value 
     * @param value the value to be credited
     * @throws Exception raised when value is 0 / negatif / bigger than the maximum value 
     */
    public void crediter(double value) throws Exception {
        checkConditions(value);
        boolean find = false;
        int i = 0;
        while(!find)
        {
            if(i == STRUCT_LENGTH) {
                updateArrayCredit();
                this.credit.set(1, value);
                find = true;
            }
            if(!find && this.credit.get(i) == 0) {
                this.credit.set(i, value);
                find = true;
            }
            i++;
        }
    }

    /**
     * update the credit list by adding all of its value into the first index and leave the other to 0.0 
     */
    public void updateArrayCredit() {
        double total = 0.0;
        for (int i = 0; i < STRUCT_LENGTH; i++) {
            total += this.credit.get(i);
            this.credit.set(i, 0.0);
        }
        this.credit.set(0, total);
    }

    /**
     * update the debit list by adding all of its value into the first index and leave the other to 0.0 
     */
    public void updateArrayDebit() {
        double total = 0.0;
        for(int i = 0 ; i < STRUCT_LENGTH ; i++) {
            total += this.debit.get(i);
            this.debit.set(i, 0.0);
        }
        this.debit.set(0, total);
    }

    /**
     * check the condition of the value
     * @param value the value to be checked
     * @throws Exception raised when value is 0 / negatif / bigger than the maximum value 
     */
    public void checkConditions(double value) throws Exception{
        if(value == 0){
            throw new ZeroNumberException("Value cannot be 0");
        }

        if (value < 0) {
            throw new NegatifNumberException("Number cannot be negatif.");
        }

        if(value > MAX_CREDIT_DEBIT){
            throw new OverLimitNumberException("Debit cannot be bigger than your saving");
        }
    }

    /**
     * debit the account by a given value 
     * @param value the value to be debited
     * @throws Exception raised when value is 0 / negatif / bigger than the maximum value 
     */
    public void debiter(double value) throws Exception {
        checkConditions(value);
        boolean find = false;
        int i = 0;
        while(!find)
        {
            if(i == STRUCT_LENGTH) {
                updateArrayDebit();
                this.debit.set(1, value);
                find = true;
            }
            if(!find && this.debit.get(i) == 0) {
                this.debit.set(i, value);
                find = true;
            }
            i++;
        }
    }
}