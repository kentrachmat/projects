package banque;

import banque.exception.theAccountDoesNotExist;
import java.util.ArrayList;

/**
 * A class to design a Bank to manage the accounts behaviour
 *
 * @author Hichem KARFA, Benedictus Kent RACHMAT
 * @version 1.0
 */
public class Banque {

    private ArrayList<Compte> listeComptes;

    /**
     * Banque constructor
     */
    public Banque() {
        this.listeComptes = new ArrayList<>();
    }

    /**
     * return the bank account list
     * @return the bank account list
     */
    public ArrayList<Compte> getComptes() {
        return this.listeComptes;
    }

    /**
     * create an account and add it to the list
     */
    public void creerCompte() {
        Compte c = new Compte();
        listeComptes.add(c);
    }

    /**
    * create an saving account and add it to the list
    * @param tauxInterets the bank interest
    */
    public void creerCompteEpargne(Double tauxInterets) {
        Compte c = new CompteEpargne(tauxInterets);
        listeComptes.add(c);
    }

    /**
     * credit an account from a given id and value
     * @param id the bank account index from the list
     * @param v the value to be credited
     * @throws Exception raised when value is 0 / negatif / bigger than the maximum value 
     */
    public void crediterCompte(int id, Double v) throws Exception {
        if (existenceCompte(id)) {
            listeComptes.get(id).crediter(v);
        }
    }

    /**
     * debit an account from a given id and value
     * @param id the bank account index from the list
     * @param v the value to be credited
     * @throws Exception raised when value is 0 / negatif / bigger than the maximum value 
     */
    public void debiterCompte(int id, Double v) throws Exception {
        if (existenceCompte(id)) {
            listeComptes.get(id).debiter(v);
        }
    }

    /**
     * check if the account exist from the list
     * @param id the bank account index from the list
     * @return true if the account exists, false otherwise
     */
    public boolean existenceCompte(int id) {
        return id <= listeComptes.size() && listeComptes.get(id) != null;
    }

    /**
     * transfer a money from one account to the other
     * @param idEmetteur the bank account index from the list to be debited
     * @param idDestinataire the bank account index from the list to be credited
     * @param montant the value to be transfered
     * @throws Exception raised when the account does not exist or 
     *                   the montant is 0 / negatif / bigger than the maximum value 
     */
    public void virement(int idEmetteur, int idDestinataire, Double montant) throws Exception {
        if(existenceCompte(idEmetteur) && existenceCompte(idDestinataire)) {
            listeComptes.get(idEmetteur).debiter(montant);
            listeComptes.get(idDestinataire).crediter(montant);
        }else {
            throw new theAccountDoesNotExist("the account does not exist");
        }
    }
}
