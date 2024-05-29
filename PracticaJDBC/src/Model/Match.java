package Model;

public class Match {
    private int id;
    private int visitante_id;
    private int puntos_visitante;
    private  int local_id;
    private  int puntos_local;

    public Match(int visitante_id, int puntos_visitante, int local_id, int puntos_local) {
        this.visitante_id = visitante_id;
        this.puntos_visitante = puntos_visitante;
        this.local_id = local_id;
        this.puntos_local = puntos_local;
    }

    public Match() {
    }

    public int getVisitante_id() {
        return visitante_id;
    }

    public void setVisitante_id(int visitante_id) {
        this.visitante_id = visitante_id;
    }

    public int getPuntos_visitante() {
        return puntos_visitante;
    }

    public void setPuntos_visitante(int puntos_visitante) {
        this.puntos_visitante = puntos_visitante;
    }

    public int getLocal_id() {
        return local_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLocal_id(int local_id) {
        this.local_id = local_id;
    }

    public int getPuntos_local() {
        return puntos_local;
    }

    public void setPuntos_local(int puntos_local) {
        this.puntos_local = puntos_local;
    }

    @Override
    public String toString() {
        return String.format(
                "Match (id=%s,visitante_id=%s, puntos_visitante=%s, local_id=%s, puntos_local=%s)",this.id==0? "-":this.id, this.visitante_id, this.puntos_visitante, this.local_id, this.puntos_local);
    }
}
