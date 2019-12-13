package lk.ijse.roomreservation.connector;

import lk.ijse.roomreservation.service.ServiceFactory;
import lk.ijse.roomreservation.service.custom.ManagerService;

import java.rmi.Naming;

public class ProxyHandler {
    private static ProxyHandler proxyHandler;
    private ManagerService managerService;

    private ProxyHandler() throws Exception {
        ServiceFactory lookup = (ServiceFactory) Naming.lookup("rmi://localhost:7070/RoomReservationServer");
        managerService= (ManagerService) lookup.getService(ServiceFactory.ServiceType.MANAGER);
    }

    public static ProxyHandler getInstance() throws Exception {
        if(null==proxyHandler){
            proxyHandler=new ProxyHandler();
        }
        return proxyHandler;
    }

    public ManagerService getService(){
        return managerService;
    }
}
