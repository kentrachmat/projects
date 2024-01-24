package banque;

import banque.exception.DebitBiggerThanException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompteEpargneTest {

    private CompteEpargne compte;

    @BeforeEach
    public void init(){
        double taux = 2.0;
        this.compte = new CompteEpargne(taux);

    }

    @Test
    public void testCreditEtDebitNulsApresCreationDuCompteEpargne(){
        assertEquals(0.0, this.compte.getCredit());
        assertEquals(0.0, this.compte.getDebit());
    }

    @Test
    public void testCrediterCompte() throws Exception {
        this.compte.crediter(2.0);
        assertEquals(2.0, this.compte.getCredit());
    }

    @Test
    public void testSoldeCompte() throws Exception {
        assertEquals(0,this.compte.getSolde());
        double credit1 = 10.2,credit2 = 20.5,credit3 =20.3,credit4= 50.4;
        double debit1 = 10.8,debit2 = 20.1,debit3 = 40.3;
        this.compte.crediter(credit1);
        this.compte.crediter(credit2);
        this.compte.crediter(credit3);
        this.compte.crediter(credit4);
        this.compte.debiter(debit1);
        this.compte.debiter(debit2);
        this.compte.debiter(debit3);
        double resultat = credit1+credit2+credit3+credit4-debit1-debit2-debit3;
        assertEquals(resultat,this.compte.getSolde());
    }

    /*@Test
    public void testDebitImpossibleSiMontantSuperieurAuSolde() throws Exception{
        compte.crediter(5.0);
        compte.debiter(7.0);
        assertEquals(5.0,compte.getSolde());
    }*/

    @Test
    public void testDebitImpossibleSiMontantSuperieurAuSolde() throws Exception{
        assertEquals(0,this.compte.getSolde());
        this.compte.crediter(10);
        assertThrows(DebitBiggerThanException.class, () -> this.compte.debiter(20));
    }

    @Test
    public void testDebitSiMontantInferieurAuSolde() throws Exception{
        assertEquals(0,this.compte.getSolde());
        this.compte.crediter(10);
        this.compte.debiter(5);
        assertEquals(5, this.compte.getSolde());
    }

    @Test
    public void testCalculInteretsCorrect() throws Exception{
        double credit = 100;
        assertEquals(0,this.compte.getSolde());
        this.compte.crediter(credit);
        assertEquals(2.0, this.compte.calculInteret());
    }

    @Test
    public void testEcheance() throws Exception{
        this.compte.crediter(100.0);
        this.compte.echeance();
        assertEquals(102.0, this.compte.getSolde());
    }

}
