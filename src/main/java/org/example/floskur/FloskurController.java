package org.example.floskur;

import Vinnsla.Floskur;
import Vinnsla.FloskurVinnsla;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

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
    public Label fxVistudGogn;
    public Button fxSkilagjald;
    private Locale currentLocale = new Locale("is");

    @FXML
    public void initialize() {
        hladaTungumal(); // hlaðar inn viðmót fyrir tungumál
        vinnslufloskur = FloskurVinnsla.lesaFloskurData(); // les json skránna
        uppfaeraUI();

        fxFloskur.textProperty().addListener((observable, oldValue, newValue) -> {
            fxFloskur.getStyleClass().removeAll("texti-red"); // fjarlægja rauða styleClass
        });

        fxDosir.textProperty().addListener((observable, oldValue, newValue) -> {
            fxDosir.getStyleClass().removeAll("texti-red"); // fjarlægja rauða styleClass
        });
    }

    /**
     * Breytir um tungumál
     * <p>Tungumáli breytt og allur texti á tökkum og fleira breytt</p>
     */
    private void hladaTungumal() {
        ResourceBundle bundle = ResourceBundle.getBundle("org.example.floskur.floskur", currentLocale);
        floskur.setText(bundle.getString("label.floskur"));
        dosir.setText(bundle.getString("label.dosir"));
        fxSamtalsTexti.setText(bundle.getString("label.fxSamtalsTexti"));
        fxTungumal.setText(bundle.getString("button.fxTungumal"));
        fxHreinsa.setText(bundle.getString("button.fxHreinsa"));
        fxGreida.setText(bundle.getString("button.fxGreida"));
        fxVista.setText(bundle.getString("button.fxVista"));
        fxHreinsaGogn.setText(bundle.getString("button.fxHreinsaGogn"));
        fxDosir.setPromptText(bundle.getString("textField.fxFloskur"));
        fxFloskur.setPromptText(bundle.getString("textField.fxFloskur"));
        fxVistudGogn.setText(bundle.getString("label.fxVistudGogn"));
        fxSkilagjald.setText(bundle.getString("button.fxSkilagjald"));
    }

    /**
     * Uppfærir viðmótið
     * <p>Setur inn gögnin sem koma úr json skrá sem heldur utan um gögn</p>
     */
    private void uppfaeraUI() {
        fxGreidaSamtals.setText(Integer.toString(vinnslufloskur.getGreida()));
        fxISKGreida.setText(Integer.toString(vinnslufloskur.getIskgreida()));
    }

    /**
     * Meðhöndlar fjölda af dósum sem settar eru inn í kefið.
     *
     * <p>Athugar hvort fjöldinn sé löglegur. Reiknar út skilagjaldið og bætir því
     * við samtals fjölda af flöskum og dósum</p>
     */
    @FXML
    protected void onDosir() {
        String dosir = fxDosir.getText();

        try {
            int intDosir = Integer.parseInt(dosir);
            if (intDosir < 0 || intDosir >= 10000) {
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
     */

    @FXML
    protected void onFloskur() {
        String floskur = fxFloskur.getText();
        try {
            int intFloskur = Integer.parseInt(floskur);
            if (intFloskur < 0 || intFloskur >= 10000){
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
     * <p>Hreinsar fjölda af flöskum og dósum sem voru settar inn ásamt skilagjaldi.
     * Hreinsar einnig það sem var búið að bæta við í sameiginlegann fjölda og skilagjalds.</p>
     */

    @FXML
    protected void onHreinsa(){
        hreinsa();
    }

    /**
     * Tekur sameiginlegan fjölda og bætir við.
     *
     * <p>Bætir sameiginlegum fjölda af flöskum og dósum, ásamt skilagjaldi við það sem
     * er búið að greiða fyrir. Hreinsar svo kerfið í leiðinni. </p>
     */
    @FXML
    protected void onGreida() {
        int greida = vinnslufloskur.getGreida();
        int ISKGreida = vinnslufloskur.getIskgreida();
        fxGreidaSamtals.setText(Integer.toString(greida));
        fxISKGreida.setText(Integer.toString(ISKGreida));
        vinnslufloskur.hreinsa();
        hreinsa();
    }

    /**
     * Endur stillir gildin
     */
    public void hreinsa(){
        vinnslufloskur.hreinsa();
        fxFloskur.setText("");
        fxDosir.setText("");
        fxISKFloskur.setText("0");
        fxISKDosir.setText("0");
        fxSamtals.setText("0");
        fxISKsamtals.setText("0");
        fxFloskur.getStyleClass().removeAll("texti-green", "texti-red");
        fxDosir.getStyleClass().removeAll("texti-green", "texti-red");
    }

    /**
     * Færir gögn í skrá
     * <p>Tekur gögnin sem er búið að setja inn og uppfærir json skrá
     * sem heldur utan um gögn.</p>
     */
    @FXML
    private void onVista() {
        int greida = vinnslufloskur.getGreida();
        int ISKGreida = vinnslufloskur.getIskgreida();

        FloskurVinnsla.uppfaeraGreidaValues(greida, ISKGreida);
        vinnslufloskur = FloskurVinnsla.lesaFloskurData();
        uppfaeraUI();
    }

    /**
     * Hreinsar gögn
     * <p>Hreinsar öll gögn úr json skrá sem heldur utan um gögnin.</p>
     */
    public void onHreinsaGogn() {
        FloskurVinnsla.hreinsaGreidaValues();
        vinnslufloskur = FloskurVinnsla.lesaFloskurData();
        uppfaeraUI();
    }

    /**
     * Breytir tungumáli
     * <p>Skoðar hvaða tungumál er sett og breytir tungumálinu
     * Kallar svo á fall til að breyta öllum texta.</p>
     */
    public void onTungumal() {
        currentLocale = currentLocale.getLanguage().equals("is") ? new Locale("en") : new Locale("is");
        hladaTungumal();
    }

    /**
     * Breyta skilagjaldi
     * <p>Opnar Dialog glugga þar sem hægt er að setja inn nýtt skilagjald.
     * Leyfir notanda að velja á milli hvort sem það er verið að breyta skilagjaldi
     * hjá dósum eða flöskur.</p>
     *
     */
    public void breytaSkilagjaldi() {
        ResourceBundle bundle = ResourceBundle.getBundle("org.example.floskur.floskur", currentLocale);

        Dialog<ButtonType> skilagjaldDialog = new Dialog<>();
        skilagjaldDialog.setTitle(bundle.getString("skilagjaldTitle"));

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll(
                bundle.getString("label.floskur"),
                bundle.getString("label.dosir")
        );
        choiceBox.getSelectionModel().selectFirst();

        TextField valueField = new TextField();
        valueField.setPromptText("ISK kr");

        VBox content = new VBox(10);
        content.getChildren().addAll(choiceBox, valueField);
        skilagjaldDialog.getDialogPane().setContent(content);

        // Add buttons
        skilagjaldDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        skilagjaldDialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    int newValue = Integer.parseInt(valueField.getText());
                    if (newValue < 0 || newValue >= 1000) {
                        synaVilluskilabod(bundle.getString("ekkitolustafur"));
                        return;
                    }

                    if (choiceBox.getValue().equals(bundle.getString("label.floskur"))) {
                        FloskurVinnsla.breytaIskFloskur(newValue);
                    } else {
                        FloskurVinnsla.breytaIskDosir(newValue);
                    }
                    vinnslufloskur = FloskurVinnsla.lesaFloskurData();

                } catch (NumberFormatException e) {
                    synaVilluskilabod(bundle.getString("ekkitolustafur"));
                }
            }
        });
    }

    /**
     * Gefur skilaboð um að það sé rangt inntak.
     */
    private void synaVilluskilabod(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
}
