package Vinnsla;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Floskur {
    @JsonProperty private int iskfloskur;
    @JsonProperty private int iskdosir;
    @JsonProperty private int samtals;
    @JsonProperty private int samtalsVerd;
    @JsonProperty private int greida;
    @JsonProperty private int iskgreida;
    @JsonProperty private int floskur;
    @JsonProperty private int dosir;


    public Floskur(){}


    public int getIskfloskur() {
        return iskfloskur;
    }

    public int getISKDosir() {
        return iskdosir;
    }

    /**
     * Hreinsar gildin fyrir samtals dósir og flöskur ásamt skilagildinu.
     */
    public void hreinsa() {
        this.samtals = 0;
        this.samtalsVerd = 0;
    }

    public int getSamtals() {
        return samtals;
    }

    public int getSamtalsVerd() {
        return samtalsVerd;
    }

    public int getGreida() {
        return greida;
    }

    public int getIskgreida() {
        return iskgreida;
    }

    /**
     * Reiknar nýtt gildi fyrir gildin sem eiga að fara í skránna.
     */
    public void baetaVidGreida() {
        greida += samtals;
        iskgreida += samtalsVerd;
    }

    public void setGreida(int greida) {
        this.greida = greida;
    }

    public void setIskgreida(int iskgreida) {
        this.iskgreida = iskgreida;
    }

    public void setiskFloskur(int newIskFloskur) {
        iskfloskur = newIskFloskur;
    }

    public void setiskDosir(int newIskDosir){
        iskdosir = newIskDosir;
    }

    public int getDosir(){
        return dosir;
    }

    public int getFloskur(){
        return floskur;
    }

    public void setFloskur(int fjoldi){
        floskur = fjoldi;
    }

    public void setDosir(int fjoldi){
        dosir = fjoldi;
    }

    /**
     * Bætir við fjölda af flöskum
     * @param fjoldi af dósum sem voru sett í kerfið
     */
    public void baetaVidFloskur(int fjoldi){
        this.floskur += fjoldi;
    }

    /**
     * Bætir við fjölda dósa
     * @param fjoldi dósa sem voru sett í kerfið
     */
    public void baetaVidDosir(int fjoldi){
        this.dosir += fjoldi;
    }

    /**
     * Reiknar nýtt gildi fyrir samtals fjölda og skilagildi.
     * @param fjoldi dósa sem voru sett inn í kerfið
     */
    public void baetaVidDosirogGjald(int fjoldi) {
        baetaVidDosir(fjoldi);
        this.samtals += fjoldi;
        this.samtalsVerd += fjoldi * iskdosir;
    }

    /**
     * Reiknar nýtt gildi fyrir samtals fjölda og skilagildi
     * @param fjoldi af flöskum sem voru sett inn í kerfið
     */
    public void baetaVidFloskurogGjald(int fjoldi) {
        baetaVidFloskur(fjoldi);
        this.samtals += fjoldi;
        this.samtalsVerd += fjoldi * iskfloskur;
    }
}
