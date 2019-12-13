package lk.ijse.roomreservation.observer;

import java.rmi.Remote;

public interface Observer extends Remote {
    void update() throws Exception;
}
