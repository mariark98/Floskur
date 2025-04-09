package Vinnsla;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Floskur {
    @JsonProperty private int iskfloskur;
    @JsonProperty private int iskdosir;
    @JsonProperty private int samtals;
    @JsonProperty private int samtalsVerd;
    @JsonProperty private int greida;
    @JsonProperty private int iskgreida;


    public Floskur(){}


    public void setFjoldiFloskur(int floskur) {
        getSamtals(floskur, true);
    }

    public int getIskfloskur() {
        return iskfloskur;
    }

     public void setFjoldiDosir(int dosir) {
        getSamtals(dosir, false);
     }

     public void getSamtals(int fjoldi, boolean floskur){
        samtals = fjoldi + samtals;
        if (floskur){
            samtalsVerd = (fjoldi * iskfloskur) + samtalsVerd;
        }
        else {
            samtalsVerd = (fjoldi * iskdosir) + samtalsVerd;
        }
     }

    public int getISKDosir() {
        return iskdosir;
    }

    public void hreinsa() {
        samtals = 0;
        samtalsVerd = 0;
    }

    public int getSamtals() {
        return samtals;
    }

    public int getSamtalsVerd() {
        return samtalsVerd;
    }

    public int getGreida() {
        greida = greida + samtals;
        return greida;
    }

    public int getIskgreida() {
        iskgreida = iskgreida + samtalsVerd;
        return iskgreida;
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
}
