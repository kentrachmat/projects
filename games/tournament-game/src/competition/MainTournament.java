package competition;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to design a MainTournament class.
 * MainTournament is a class where the game starts
 *
 * @author Hichem KARFA, Benedictus Kent RACHMAT
 * @version 1.0
 */
public class MainTournament {

    /**
     * Start the tournament game
     *
     * @param args The arguments entered to play the game.
     */
    public static void main(String[] args) {
        List<Competitor> competitors = new ArrayList<>();
        Match match = new RandomMatch();

        if (args.length < 1) {
            System.out.print("\n   Error :\n   - Please input the argument with the correct format !");
            System.out.print("\n   Usage 1:\n   - java -jar jar/tournament.jar Player1 Player2 Player3 ....\n");
        } else {
            for (String arg : args) {
                competitors.add(new Competitor(arg));
            }

            Competition competition = new Tournament(competitors, match);
            competition.play();
        }
    }
}