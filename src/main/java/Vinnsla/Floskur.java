package Vinnsla;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Floskur {
    @JsonProperty private int ISKFloskur;
    @JsonProperty private int ISKDosir;
    @JsonProperty private int samtals;
    @JsonProperty private int samtalsVerd;
    @JsonProperty private int greida;
    @JsonProperty private int ISKGreida;


    public Floskur(){}


    public void setFjoldiFloskur(int floskur) {
        getSamtals(floskur, true);
    }

    public int getISKFloskur() {
        return ISKFloskur;
    }

     public void setFjoldiDosir(int dosir) {
        getSamtals(dosir, false);
     }

     public void getSamtals(int fjoldi, boolean floskur){
        samtals = fjoldi + samtals;
        if (floskur){
            samtalsVerd = (fjoldi * ISKFloskur) + samtalsVerd;
        }
        else {
            samtalsVerd = (fjoldi * ISKDosir) + samtalsVerd;
        }
     }

    public int getISKDosir() {
        return ISKDosir;
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

    public int getISKGreida() {
        ISKGreida = ISKGreida + samtalsVerd;
        return ISKGreida;
    }
}
