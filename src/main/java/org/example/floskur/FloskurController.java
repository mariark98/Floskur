package org.example.floskur;

import Vinnsla.Floskur;
import Vinnsla.FloskurVinnsla;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Tekur á móti fjölda af flöskum og dósum.
 * Reiknar skilagjald.
 * Bætir fjölda af dósum eða flöskum við sameiginlegann fjölda ásamt skilagjaldi.
 * Gefur kost á að hreinsa kerfið ásamt að greiða.
 *
 * @author María Rún Karlsdóttir mrk7@hi.is
 */

public class FloskurController {



    private Floskur vinnslufloskur;

    public Label fxISKDosir;
    public Label fxISKFloskur;
    public Label fxSamtals;
    public Label fxISKsamtals;
    public TextField fxDosir;
    public TextField fxFloskur;
    public Label fxGreidaSamtals;
    public Label fxISKGreida;
    public Button fxGreida;
    public Button fxHreinsa;
    public Button fxVista;
    public Button fxHreinsaGogn;
    public Label floskur;
    public Label dosir;
    public Label fxSamtalsTexti;
    public Button fxTungumal;
    private ResourceBundle fastar;
    private Locale currentLocale = new Locale("is");

    @FXML
    public void initialize() {
        loadLanguage();
        vinnslufloskur = FloskurVinnsla.readFloskurData();
        updateUI();
    }

    private void loadLanguage() {
        ResourceBundle bundle = ResourceBundle.getBundle("org.example.floskur.floskur", currentLocale);
        floskur.setText(bundle.getString("label.floskur"));
        dosir.setText(bundle.getString("label.dosir"));
        fxSamtalsTexti.setText(bundle.getString("label.fxSamtalsTexti"));
        fxTungumal.setText(bundle.getString("button.fxTungumal"));
        fxHreinsa.setText(bundle.getString("button.fxHreinsa"));
        fxGreida.setText(bundle.getString("button.fxGreida"));
        fxVista.setText(bundle.getString("button.fxVista"));
        fxHreinsaGogn.setText(bundle.getString("button.fxHreinsaGogn"));
        fxDosir.setPromptText(bundle.getString("textField.fxDosir"));
        fxFloskur.setPromptText(bundle.getString("textField.fxFloskur"));
    }

    /**
     * Uppfærir viðmótið
     * <p>Setur inn gögnin sem koma úr json skrá sem heldur utan um gögn</p>
     */
    private void updateUI() {
        fxGreidaSamtals.setText(Integer.toString(vinnslufloskur.getGreida()));
        fxISKGreida.setText(Integer.toString(vinnslufloskur.getIskgreida()));
    }

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
            int verdfloskur = vinnslufloskur.getIskfloskur() * intFloskur;
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
        int ISKGreida = vinnslufloskur.getIskgreida();
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

    /**
     * Færir gögn í skrá
     * <p>Tekur gögnin sem er búið að setja inn og uppfærir json skrá
     * sem heldur utan um gögn.</p>
     * @param actionEvent
     */
    @FXML
    private void onVista(ActionEvent actionEvent) {
        int greida = vinnslufloskur.getGreida();
        int ISKGreida = vinnslufloskur.getIskgreida();

        FloskurVinnsla.updateGreidaValues(greida, ISKGreida);
        Floskur vinnslufloskur = FloskurVinnsla.readFloskurData();
        updateUI();
    }

    /**
     * Hreinsar gögn
     * <p>Hreinsar öll gögn úr json skrá sem heldur utan um gögnin.</p>
     * @param actionEvent
     */
    public void onHreinsaGogn(ActionEvent actionEvent) {
        FloskurVinnsla.clearGreidaValues();
        vinnslufloskur = FloskurVinnsla.readFloskurData();
        updateUI();
    }

    public void onTungumal(ActionEvent actionEvent) {
        currentLocale = currentLocale.getLanguage().equals("is") ? new Locale("en") : new Locale("is");
        loadLanguage(); // Reload UI texts
    }


    // public void onStafur(KeyEvent keyEvent) {
        //fxFloskur.getStyleClass().removeAll("texti-red");
        //fxFloskur.getStyleClass().add("texti-black");
        //fxDosir.getStyleClass().removeAll("texti-red");
        //fxDosir.getStyleClass().add("texti-black");
    //}
}
