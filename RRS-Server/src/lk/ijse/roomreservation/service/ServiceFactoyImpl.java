package lk.ijse.roomreservation.service;

import lk.ijse.roomreservation.service.custom.ManagerServiceImpl;

import java.rmi.server.UnicastRemoteObject;

public class ServiceFactoyImpl extends UnicastRemoteObject implements ServiceFactory {
    private static ServiceFactoyImpl serviceFactoy;
    private ServiceFactoyImpl() throws Exception{}

    public static ServiceFactory getInstance() throws Exception {
        if(null==serviceFactoy){
            serviceFactoy=new ServiceFactoyImpl();
        }
        return serviceFactoy;
    }

    @Override
    public SuperService getService(ServiceType types) throws Exception {
        switch (types){
            case MANAGER:return new ManagerServiceImpl();
            default:return null;
        }
    }
}
