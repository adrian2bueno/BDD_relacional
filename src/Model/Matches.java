package Model;

import java.util.Date;

public class Matches {
    private int partit_id;
    private int equip_id;
    private Date data_partit;
    private String matx;
    private String resultat;


    public Matches(int partit_id, int equip_id, Date data_partit, String matx, String resultat) {
        this.partit_id = partit_id;
        this.equip_id = equip_id;
        this.data_partit = data_partit;
        this.matx = matx;
        this.resultat = resultat;

    }


    public int getPartit_id() {
        return partit_id;
    }

    public void setPartit_id(int partit_id) {
        this.partit_id = partit_id;
    }

    public int getEquip_id() {
        return equip_id;
    }

    public void setEquip_id(int equip_id) {
        this.equip_id = equip_id;
    }

    public Date getData_partit() {
        return data_partit;
    }

    public void setData_partit(Date data_partit) {
        this.data_partit = data_partit;
    }

    public String getMatx() {
        return matx;
    }

    public void setMatx(String matx) {
        this.matx = matx;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }
}
