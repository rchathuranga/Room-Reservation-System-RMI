package lk.ijse.roomreservation.observer;

import java.util.ArrayList;

public class ObserverImpl {
    private static ArrayList<Observer> observers=new ArrayList<>();

    public void register(Observer ob){
        System.out.println("reg : "+ob);
        observers.add(ob);
    }

    public void unregister(Observer ob){
        System.out.println("unreg : "+ob);
        observers.remove(ob);
    }

    public void notifyObservers(){
        for (Observer ob : observers) {
            new Thread(() -> {
                try {
                    ob.update();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }


}
