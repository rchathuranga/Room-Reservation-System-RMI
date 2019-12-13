package lk.ijse.roomreservation.service;

import java.rmi.Remote;

public interface ServiceFactory extends Remote {
    public enum ServiceType{
        MANAGER
    }
    public SuperService getService(ServiceType types) throws Exception;
}
