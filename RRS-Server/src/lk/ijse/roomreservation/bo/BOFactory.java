package lk.ijse.roomreservation.bo;

import lk.ijse.roomreservation.bo.custom.impl.ManagerBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){}

    public static BOFactory getInstance() {
        if(null==boFactory){
            boFactory=new BOFactory();
        }
        return boFactory;
    }

    public enum BOTypes{
        MANAGERS
    }

    public SuperBO getBO(BOTypes types){
        switch (types){
            case MANAGERS: return new ManagerBOImpl();
            default:return null;
        }
    }
}
