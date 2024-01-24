package competition;
import competition.observer.Observer;
import java.util.ArrayList;

public class MockObserver implements Observer {

    public void update(Competitor c1, Competitor c2, Competitor winner){
        System.out.println(winner);
    }
}