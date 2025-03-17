package org.example.floskur;

import Vinnsla.Floskur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Tekur á móti fjölda af flöskum og dósum.
 * Reiknar skilagjald.
 * Bætir fjölda af dósum eða flöskum við sameiginlegann fjölda ásamt skilagjaldi.
 * Gefur kost á að hreinsa kerfið ásamt að greiða.
 *
 * @author María Rún Karlsdóttir mrk7@hi.is
 */

public class FloskurController implements Initializable {

    private Floskur vinnslufloskur;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vinnslufloskur = new Floskur();
    }

    public Label fxISKDosir;
    public Label fxISKFloskur;
    public Label fxSamtals;
    public Label fxISKsamtals;
    public TextField fxDosir;
    public TextField fxFloskur;
    public Label fxGreidaSamtals;
    public Label fxISKGreida;


    /**
     * Meðhöndlar fjölda af dósum sem settar eru inn í kefið.
     *
     * <p>Athugar hvort fjöldinn sé löglegur. Reiknar út skilagjaldið og bætir því
     * við samtals fjölda af flöskum og dósum</p>
     *
     * @param actionEvent atburðurinn sem varð.
     */
    @FXML
    protected void onDosir(ActionEvent actionEvent) {
        String dosir = fxDosir.getText();

        try {
            int intDosir = Integer.parseInt(dosir);
            if (intDosir < 0) {
                throw new IllegalArgumentException();
            }
            vinnslufloskur.setFjoldiDosir(intDosir);
            fxDosir.getStyleClass().removeAll("texti-red"); // fjarlægja rauða styleClass
            fxDosir.getStyleClass().add("texti-green");
            int verddosir = vinnslufloskur.getISKDosir() * intDosir;
            fxISKDosir.setText(Integer.toString(verddosir));
            int samtals = vinnslufloskur.getSamtals();
            int samtalsverd = vinnslufloskur.getSamtalsVerd();
            fxSamtals.setText(Integer.toString(samtals));
            fxISKsamtals.setText(Integer.toString(samtalsverd));
        }
        catch (IllegalArgumentException e) {
            fxDosir.getStyleClass().removeAll("texti-green");
            fxDosir.getStyleClass().add("texti-red");
        }
    }

    /**
     * Meðhöndlar fjölda af flöskum sem settar eru inn í kefið.
     *
     * <p>Athugar hvort fjöldinn sé löglegur. Reiknar út skilagjaldið og bætir því
     * við samtals fjölda af flöskum og dósum</p>
     *
     * @param actionEvent atburðurinn sem varð.
     */

    @FXML
    protected void onFloskur(ActionEvent actionEvent) {
        String floskur = fxFloskur.getText();
        try {
            int intFloskur = Integer.parseInt(floskur);
            if (intFloskur < 0) {
                throw new IllegalArgumentException();
            }
            vinnslufloskur.setFjoldiFloskur(intFloskur);
            fxFloskur.getStyleClass().removeAll("texti-red"); // fjarlægja rauða styleClass
            fxFloskur.getStyleClass().add("texti-green");
            int verdfloskur = vinnslufloskur.getISKFloskur() * intFloskur;
            fxISKFloskur.setText(Integer.toString(verdfloskur));
            int samtals = vinnslufloskur.getSamtals();
            int samtalsVerd = vinnslufloskur.getSamtalsVerd();
            fxSamtals.setText(Integer.toString(samtals));
            fxISKsamtals.setText(Integer.toString(samtalsVerd));
        }
        catch (IllegalArgumentException e) {
            fxFloskur.getStyleClass().removeAll("texti-green");
            fxFloskur.getStyleClass().add("texti-red");
        }
    }

    /**
     *  Hreinsar kefið.
     *
     * <p>Hreinsar fjölda af flöskum og dósum sem voru settar inn ásakmt skilagjaldi.
     * Hreinsar einnig það sem var búið að bæta við í sameiginlegann fjölda og skilagjalds.</p>
     *
     * @param actionEvent atburðurinn sem varð.
     */

    @FXML
    protected void onHreinsa(ActionEvent actionEvent){
        vinnslufloskur.hreinsa();
        fxDosir.setText("");
        fxFloskur.setText("");
        fxISKDosir.setText("0");
        fxISKFloskur.setText("0");
        fxSamtals.setText("0");
        fxISKsamtals.setText("0");
        fxFloskur.getStyleClass().removeAll("text-green");
        fxFloskur.getStyleClass().add("texti-black");
        fxDosir.getStyleClass().removeAll("text-green");
        fxDosir.getStyleClass().add("texti-black");
    }

    /**
     * Tekur sameiginlegan fjölda og bætir við.
     *
     * <p>Bætir sameiginlegum fjölda af flöskum og dósum, ásamt skilagjaldi við það sem
     * er búið að greiða fyrir. Hreinsar svo kerfið í leiðinni. </p>
     *
     * @param actionEvent atburðurinn sem varð.
     */
    @FXML
    protected void onGreida(ActionEvent actionEvent) {
        int greida = vinnslufloskur.getGreida();
        int ISKGreida = vinnslufloskur.getISKGreida();
        fxGreidaSamtals.setText(Integer.toString(greida));
        fxISKGreida.setText(Integer.toString(ISKGreida));
        vinnslufloskur.hreinsa();
        fxFloskur.setText("");
        fxDosir.setText("");
        fxISKFloskur.setText("0");
        fxISKDosir.setText("0");
        fxSamtals.setText("0");
        fxISKsamtals.setText("0");
    }


     // public void onStafur(KeyEvent keyEvent) {
        //fxFloskur.getStyleClass().removeAll("texti-red");
        //fxFloskur.getStyleClass().add("texti-black");
        //fxDosir.getStyleClass().removeAll("texti-red");
        //fxDosir.getStyleClass().add("texti-black");
    //}
}
