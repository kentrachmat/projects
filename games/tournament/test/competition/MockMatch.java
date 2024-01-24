package competition;
import competition.observer.Observer;
import java.util.ArrayList;

public class MockMatch implements Match {

    private ArrayList<Observer> observers = new ArrayList<>();

    public Competitor playMatch(Competitor c1, Competitor c2) {
        return c1;
    }

    public void addObserver(Observer observer){
        observers.add(observer) ;
    }

    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    public void notifyObservers(Competitor c1, Competitor c2, Competitor winner) {
        for (Observer obs : this.observers) {
            obs.update(c1, c2, winner);
        }
    }

    public ArrayList<Observer> getObservers(){
        return this.observers;
    }

}