package banque;

import banque.exception.OverLimitNumberException;
import banque.exception.ZeroNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import banque.exception.NegatifNumberException;
import static org.junit.jupiter.api.Assertions.*;

class CompteTest {

    private Compte compte;

    @BeforeEach
    public void init(){
        this.compte = new Compte();
    }

    @Test
    public void testCreationCompte() {
        assertEquals(0,this.compte.getDebit());
        assertEquals(0,this.compte.getCredit());
    }

    @Test
    public void testCrediterCompte() throws Exception {
        assertEquals(0,this.compte.getCredit());
        this.compte.crediter(5);
        assertEquals(5,this.compte.getCredit());
    }

    @Test
    public void testDebiterCompte() throws Exception{
        assertEquals(0,this.compte.getDebit());
        this.compte.debiter(10);
        assertEquals(10,this.compte.getDebit());
    }

     @Test
    public void testDebiterNegatif() {
        assertEquals(0,this.compte.getDebit());
        assertThrows(NegatifNumberException.class, () -> this.compte.debiter(-10));
    }

    @Test
    public void testCrediterNegatif() {
        assertEquals(0,this.compte.getCredit());
        assertThrows(NegatifNumberException.class, () -> this.compte.debiter(-4));
    }


    @Test
    public void testSoldeCompte() throws Exception{
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

    @Test
    public void testTableauHistoriqueCreditEtDebit() throws Exception{
        double credit1 = 4.0 ,credit2 = 6.0,credit3 =10.0,credit4= 5.0;
        double debit1 = 10.0,debit2 = 20.0,debit3 = 40.0,debit4 = 2.0;
        assertEquals(0.0, this.compte.getCredit(), 0);
        this.compte.crediter(credit1);
        this.compte.crediter(credit2);
        this.compte.crediter(credit3);
        this.compte.crediter(credit4);
        double total=credit1+credit2+credit3+credit4;
        assertEquals(total, this.compte.getCredit(), 0);
        assertEquals(0.0, this.compte.getDebit(), 0);
        this.compte.debiter(debit1);
        this.compte.debiter(debit2);
        this.compte.debiter(debit3);
        this.compte.debiter(debit4);
        total=debit1+debit2+debit3+debit4;
        assertEquals(total, this.compte.getDebit(), 0);
    }

    @Test
    public void testCrediterZero() {
        assertThrows(ZeroNumberException.class, () -> this.compte.crediter(0));
    }


    @Test
    public void testDebiterZero() {
        assertThrows(ZeroNumberException.class, () -> this.compte.debiter(0));
    }

    @Test
    public void testDebiterLimit() {
        assertThrows(OverLimitNumberException.class, () -> this.compte.debiter(100001));
    }

    @Test
    public void testCrediterLimit() {
        assertThrows(OverLimitNumberException.class, () -> this.compte.crediter(100001));
    }
}