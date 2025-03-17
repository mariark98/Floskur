package Vinnsla;


public class Floskur {
    int ISKFloskur = 20;
    int ISKDosir = 20;
    int samtals = 0;
    int samtalsVerd = 0;
    int greida = 0;
    int ISKGreida = 0;


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
