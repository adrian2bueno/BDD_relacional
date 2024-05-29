package Model;

public class PlayerMatches {
    private int id_match;
    private int id_jug;
    private int punts;
    private int rebots;
    private int assist;

    public PlayerMatches() {
    }

    public int getId_match() {
        return id_match;
    }

    public void setId_match(int id_match) {
        this.id_match = id_match;
    }

    public int getId_jug() {
        return id_jug;
    }

    public void setId_jug(int id_jug) {
        this.id_jug = id_jug;
    }

    public int getPunts() {
        return punts;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }

    public int getRebots() {
        return rebots;
    }

    public void setRebots(int rebots) {
        this.rebots = rebots;
    }

    public int getAssist() {
        return assist;
    }

    public void setAssist(int assist) {
        this.assist = assist;
    }
}
