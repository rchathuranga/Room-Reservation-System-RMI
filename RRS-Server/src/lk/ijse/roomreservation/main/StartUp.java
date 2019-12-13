package lk.ijse.roomreservation.main;

import lk.ijse.roomreservation.service.ServiceFactoyImpl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StartUp {
    public static void main(String[] args) {
        try {
            Registry registry= LocateRegistry.createRegistry(7070);
            registry.rebind("RoomReservationServer", ServiceFactoyImpl.getInstance());
            printServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printServer(){
        System.out.println("" +
                "  _____                          _____                                _   _                _____                             _____ _             _           _ \n" +
                " |  __ \\                        |  __ \\                              | | (_)              / ____|                           / ____| |           | |         | |\n" +
                " | |__) |___   ___  _ __ ___    | |__) |___  ___  ___ _ ____   ____ _| |_ _  ___  _ __   | (___   ___ _ ____   _____ _ __  | (___ | |_ __ _ _ __| |_ ___  __| |\n" +
                " |  _  // _ \\ / _ \\| '_ ` _ \\   |  _  // _ \\/ __|/ _ \\ '__\\ \\ / / _` | __| |/ _ \\| '_ \\   \\___ \\ / _ \\ '__\\ \\ / / _ \\ '__|  \\___ \\| __/ _` | '__| __/ _ \\/ _` |\n" +
                " | | \\ \\ (_) | (_) | | | | | |  | | \\ \\  __/\\__ \\  __/ |   \\ V / (_| | |_| | (_) | | | |  ____) |  __/ |   \\ V /  __/ |     ____) | || (_| | |  | ||  __/ (_| |\n" +
                " |_|  \\_\\___/ \\___/|_| |_| |_|  |_|  \\_\\___||___/\\___|_|    \\_/ \\__,_|\\__|_|\\___/|_| |_| |_____/ \\___|_|    \\_/ \\___|_|    |_____/ \\__\\__,_|_|   \\__\\___|\\__,_|\n" +
                " ==============================================================================================================================================================                                                                                                                                                             \n" +
                " " +
                "");
    }
}
