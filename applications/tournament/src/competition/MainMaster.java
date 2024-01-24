package competition;

import competition.strategy.SelectionStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to design a MainMaster class.
 * MainMaster is a class where the first round is played using the League method, 
 * and the second round use the tournament method.
 * 
 * @author Hichem KARFA, Benedictus Kent RACHMAT
 * @version 1.0
 */
public class MainMaster {
    /**
     * reset the terminal color
     */
    public static final String ANSI_RESET = "\u001B[0m";
    /**
     * colorize terminal to red
     */
    public static final String ANSI_RED = "\u001B[31m";

    /**
     * method to be called when the program starts
     *
     * @param args the argument from the terminal
     */
    public static void main(String[] args) {
        MainMaster master = new MainMaster();
        master.init(args);
    }

    /**
     * initialize a new master competition
     */
    private void init(String[] args) {
        System.out.println(ANSI_RED + "*** WELCOME TO THE MASTER GAME ***" + ANSI_RESET);
        List<Competitor> competitors = new ArrayList<>();
        Match match = new RandomMatch();
        if (args.length < 1) {
            System.out.print("\n   Error :\n   - Please input the argument with the correct format !");
            System.out.print("\n   Usage 1:\n   - java -jar jar/master.jar Player1 Player2 Player3 ....\n\n");
        } else {
            for (String arg : args) {
                competitors.add(new Competitor(arg));
            }
            int nbGroups = MainCompetition.chooseNbGroups(competitors.size());
            SelectionStrategy strategy = MainCompetition.chooseStrategy();
            Master master = new Master(competitors, match, nbGroups, strategy);
            master.play();
        }
    }
}