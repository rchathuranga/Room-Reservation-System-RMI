package lk.ijse.roomreservation.observer;

public interface Subject {
    void register(Observer ob) throws Exception;
    void unregister(Observer ob) throws Exception;
    void notifyObservers() throws Exception;
}
