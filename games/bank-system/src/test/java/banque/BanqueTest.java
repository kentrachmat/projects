package banque;


import banque.exception.DebitBiggerThanException;
import banque.exception.theAccountDoesNotExist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BanqueTest {
    private Banque myBanque;

    @BeforeEach
    public void init(){
        this.myBanque = new Banque();
    }

    @Test
    public void testPasDeComptesApresCreation() {
        assertTrue(this.myBanque.getComptes().isEmpty());
    }

    @Test
    public void testCreationCompteEtCompteEpargne() {
        this.myBanque.creerCompte();
        assertEquals(1, this.myBanque.getComptes().size());
        this.myBanque.creerCompteEpargne(2.0);
        assertEquals(2, this.myBanque.getComptes().size());
    }

    @Test
    public void testCrediterCompteEtCompteEpargne() throws Exception {
        this.myBanque.creerCompte();
        this.myBanque.crediterCompte(0, 2.0);
        assertEquals(2.0, this.myBanque.getComptes().get(0).getSolde());
        this.myBanque.creerCompteEpargne(3.0);
        this.myBanque.crediterCompte(1, 5.0);
        assertEquals(5.0, this.myBanque.getComptes().get(1).getSolde());
    }

    @Test
    public void testDebiterCompteEtCompteEpargne() throws Exception {
        this.myBanque.creerCompte();
        this.myBanque.crediterCompte(0, 2.0);
        this.myBanque.debiterCompte(0, 1.0);
        assertEquals(1.0, this.myBanque.getComptes().get(0).getSolde());
        this.myBanque.creerCompteEpargne(3.0);
        this.myBanque.crediterCompte(1, 5.0);
        this.myBanque.debiterCompte(1, 3.0);
        assertEquals(2.0,this.myBanque.getComptes().get(1).getSolde());
    }

    @Test
    public void testExistenceCompte() {
        this.myBanque.creerCompte();
        assertTrue(this.myBanque.existenceCompte(0));
        assertFalse(this.myBanque.existenceCompte(4));
    }

    @Test
    public void testVirementAvecDeuxComptesExistants() throws Exception {
        this.myBanque.creerCompte();
        this.myBanque.crediterCompte(0, 5.0);
        this.myBanque.creerCompte();
        assertEquals(5.0, this.myBanque.getComptes().get(0).getSolde());
        this.myBanque.virement(0, 1, 2.0);
        assertEquals(3.0, this.myBanque.getComptes().get(0).getSolde());
        assertEquals(2.0, this.myBanque.getComptes().get(1).getSolde());
    }

    @Test
    public void testVirementAvecCompteEmetteurInexistant() throws Exception {
        this.myBanque.creerCompte();
        this.myBanque.crediterCompte(0, 5.0);
        assertThrows(theAccountDoesNotExist.class, () -> this.myBanque.virement(4, 0, 2.0));
    }

    @Test
    public void testVirementAvecCompteDestinataireInexistant() throws Exception {
        this.myBanque.creerCompte();
        this.myBanque.crediterCompte(0, 5.0);
        assertThrows(theAccountDoesNotExist.class, () -> this.myBanque.virement(0, 4, 2.0));
    }

    @Test
    public void testVirementAvecCompteEpargneEmetteurImpossibleSiValeurSupperieureAuSolde() throws Exception {
        this.myBanque.creerCompteEpargne(2.0);
        this.myBanque.crediterCompte(0, 5.0);
        this.myBanque.creerCompte();
        assertThrows(DebitBiggerThanException.class, () -> this.myBanque.virement(0, 1, 8.0));
    }
}
