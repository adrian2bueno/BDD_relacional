package Model;

public class Teams {
    private int equipId;
    private String ciutat;
    private String nom;
    private String acronim;
    private String divisio;
    private int guanyades;
    private int perdudes;

    public Teams(String ciutat, String nom, String acronim, String divisio, int guanyades, int perdudes) {
        this.equipId = 0;
        this.ciutat = ciutat;
        this.nom = nom;
        this.acronim = acronim;
        this.divisio = divisio;
        this.guanyades = guanyades;
        this.perdudes = perdudes;
    }

    public void setEquipId(int equipId) {
        this.equipId = equipId;
    }

    public int getIdEquip() {
        return equipId;
    }

    public String getCiutat() {
        return ciutat;
    }

    public String getNom() {
        return nom;
    }

    public String getAcronim() {
        return acronim;
    }

    public String getDivisio() {
        return divisio;
    }

    public int getGuanyades() {
        return guanyades;
    }

    public int getPerdudes() {
        return perdudes;
    }
}
