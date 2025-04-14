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

    public void baetaVidFloskur(int fjoldi){
        this.floskur += fjoldi;
    }

    public void baetaVidDosir(int fjoldi){
        this.dosir += fjoldi;
    }

    public void baetaVidDosirogGjald(int fjoldi) {
        baetaVidDosir(fjoldi);
        this.samtals += fjoldi;
        this.samtalsVerd += fjoldi * iskdosir;
    }

    public void baetaVidFloskurogGjald(int fjoldi) {
        baetaVidFloskur(fjoldi);
        this.samtals += fjoldi;
        this.samtalsVerd += fjoldi * iskfloskur;
    }
}
