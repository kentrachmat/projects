package competition;

import competition.observer.*;
import competition.strategy.*;

import io.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A class to design a MainCompetition class.
 * MainCompetition is a class where we can choose to play a league or a tournament game
 *
 * @author Hichem KARFA, Benedictus Kent RACHMAT
 * @version 1.0
 */
public class MainCompetition {

    /**
     * method to be called when the program starts
     *
     * @param args the argument from the terminal
     */
    public static void main(String[] args) {
        Competition competition = init();
        if (competition == null) {
            System.out.print("An error has occurred");
        } else {
            competition.play();
        }
    }

    /**
     * create a new competition
     *
     * @return the chosen competition
     */
    private static Competition init() {
        String competition = chooseCompetition();
        int nbCompetitors = chooseNbCompetitors();
        List<Competitor> competitors = createCompetitors(nbCompetitors);
        Match match = new RandomMatch();
        Observer journaliste = new Journalist("J1");
        Observer bookmaker = new Bookmaker("B1");
        match.addObserver(journaliste);
        match.addObserver(bookmaker);

        switch (competition) {
            case "1":
                return new League(competitors, match);
            case "2":
                return new Tournament(competitors, match);
            case "3":
                return new Master(competitors, match, chooseNbGroups(nbCompetitors), chooseStrategy());
            default:
                return null;
        }
    }

    /**
     * asks the user to choose a competition type
     *
     * @return returns the competition type
     */
    public static String chooseCompetition() {
        String[] valuesCompetition = {"1", "2", "3"};
        String inputCompetition = "";
        while (!Arrays.asList(valuesCompetition).contains(inputCompetition)) {
            System.out.println("*** WELCOME TO THE MASTER GAME ***\n");
            System.out.print("1 - League\n2 - Tournament\n3 - Master\nEnter your choice : ");
            try {
                inputCompetition = Input.readString();
            } catch (Exception e) {
                inputCompetition = "";
            }
            if (!Arrays.asList(valuesCompetition).contains(inputCompetition)) {
                System.out.print("\nValue Incorrect\n\n");
            }
        }
        return inputCompetition;
    }

    /**
     * ask the user to choose the number of participants
     *
     * @return the number of participants
     */
    public static int chooseNbCompetitors() {
        int nbCompetitors = 0;
        while (nbCompetitors < 1 || nbCompetitors > 16) {
            System.out.print("\nEnter the amount of player to play this game (16 maximum) : ");
            try {
                nbCompetitors = Integer.parseInt(Input.readString());
            } catch (Exception e) {
                nbCompetitors = 0;
            }
            if (nbCompetitors < 1 || nbCompetitors > 16) {
                System.out.print("\nValue Incorrect\n");
            }
        }
        return nbCompetitors;
    }

    /**
     * ask the user to choose the number of groups
     *
     * @param nbCompetitors the number of Competitors
     * @return the number of group
     */
    public static int chooseNbGroups(int nbCompetitors) {
        int nbPools = 0;
        while (nbPools < 1 || nbPools > nbCompetitors) {
            System.out.print("\nEnter the number of pools  (" + nbCompetitors + " maximum) : ");
            try {
                nbPools = Integer.parseInt(Input.readString());
            } catch (Exception e) {
                nbPools = 0;
            }
            if (nbPools < 1 || nbPools > nbCompetitors) {
                System.out.print("\nIncorrect value\n");
            }
        }
        return nbPools;
    }

    /**
     * ask the user to choose a Strategy
     *
     * @return a strategy for choosing the winner from the first round
     */
    public static SelectionStrategy chooseStrategy() {
        String[] valuesStrategy = {"1", "2"};
        String inputStrategy = "";
        while (!Arrays.asList(valuesStrategy).contains(inputStrategy)) {
            System.out.print("\nChoose the strategy of winning : \n1 - FirstStrategy : First winner of each group\n2 - FirstAndSecondStrategy : First and Second winner of each group\nEnter your choice : ");
            try {
                inputStrategy = Input.readString();
            } catch (Exception e) {
                inputStrategy = "";
            }
            if (!Arrays.asList(valuesStrategy).contains(inputStrategy)) {
                System.out.print("\nIncorrect value\n");
            }
        }
        switch (inputStrategy) {
            case "1":
                return new FirstStrategy();
            case "2":
                return new FirstAndSecondStrategy();
            default:
                return null;
        }
    }

    /**
     * create a list of the participants
     *
     * @param nbCompetitors number of the participants
     * @return list of the participants
     */
    public static List<Competitor> createCompetitors(int nbCompetitors) {
        ArrayList<Competitor> competitors = new ArrayList<>();
        String[] names = {"Tom", "Lola", "Hichem", "Julie", "Alexis", "Manon", "Maxime", "Lucie", "Louis", "Marie", "Paul",
                "Emma", "Corentin", "Justine", "Florian", "Louise"};
        for (int i = 0; i < nbCompetitors; i++) {
            Competitor c = new Competitor(names[i]);
            competitors.add(c);
        }
        return competitors;
    }
}