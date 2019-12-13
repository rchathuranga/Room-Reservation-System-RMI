package lk.ijse.roomreservation.alert;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class MyAlert {
    private AnchorPane pnl;
    private Label lbl;
    private TranslateTransition transition;

    public MyAlert(AnchorPane pnl, Label lbl){
        this.pnl=pnl;
        this.lbl=lbl;

         transition= new TranslateTransition(Duration.millis(5350),pnl);
        transition.setFromY(-43);
        transition.setToY(0);
    }

    public enum AlertType{
        DONE,FAIL,WARNING,NOTICE;
    }

    public void show(AlertType type,String msg){
        transition.stop();
        lbl.setText(msg);
        switch (type){
            case DONE:{
                pnl.setStyle("-fx-background-color: #00ab66");
                transition.play();
                break;
            }
            case WARNING:{
                pnl.setStyle("-fx-background-color: #ab9600");
                transition.play();
                break;
            }
            case FAIL:{
                pnl.setStyle("-fx-background-color: red");
                transition.play();
                break;
            }
            case NOTICE:{
                pnl.setStyle("-fx-background-color:  #151515");
                lbl.setStyle("-fx-text-fill:  #fff");
                transition.play();
            }
            default:{pnl.setStyle("-fx-background-color:  #151515");}
        }
    }
}
