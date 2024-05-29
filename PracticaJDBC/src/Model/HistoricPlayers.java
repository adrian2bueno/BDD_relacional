package Model;

public class HistoricPlayers {
    private int id_jugador;
    private  float punts;

    private  float rebots;

    private float assist;

    private String ultim_equip;

    public HistoricPlayers(int id_jugador, float punts, float rebots, float assist, String ultim_equip) {
        this.id_jugador = id_jugador;
        this.punts = punts;
        this.rebots = rebots;
        this.assist = assist;
        this.ultim_equip = ultim_equip;
    }

    public int getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(int id_jugador) {
        this.id_jugador = id_jugador;
    }

    public float getPunts() {
        return punts;
    }

    public void setPunts(float punts) {
        this.punts = punts;
    }

    public float getRebots() {
        return rebots;
    }

    public void setRebots(float rebots) {
        this.rebots = rebots;
    }

    public float getAssist() {
        return assist;
    }

    public void setAssist(float assist) {
        this.assist = assist;
    }

    public String getUltim_equip() {
        return ultim_equip;
    }

    public void setUltim_equip(String ultim_equip) {
        this.ultim_equip = ultim_equip;
    }
}
