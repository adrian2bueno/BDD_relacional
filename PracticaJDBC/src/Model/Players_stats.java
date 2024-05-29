package Model;

public class Players_stats {
    private int id_jugador;
    private float avg_puntos;
    private float avg_rebotes;
    private float avg_asistencias;

    public Players_stats(int id_jugador, float avg_puntos, float avg_rebotes, float avg_asistencias) {
        this.id_jugador = id_jugador;
        this.avg_puntos = avg_puntos;
        this.avg_rebotes = avg_rebotes;
        this.avg_asistencias = avg_asistencias;
    }

    public Players_stats() {

    }

    public int getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(int id_jugador) {
        this.id_jugador = id_jugador;
    }

    public float getAvg_puntos() {
        return avg_puntos;
    }

    public void setAvg_puntos(float avg_puntos) {
        this.avg_puntos = avg_puntos;
    }

    public float getAvg_rebotes() {
        return avg_rebotes;
    }

    public void setAvg_rebotes(float avg_rebotes) {
        this.avg_rebotes = avg_rebotes;
    }

    public float getAvg_asistencias() {
        return avg_asistencias;
    }

    public void setAvg_asistencias(float avg_asistencias) {
        this.avg_asistencias = avg_asistencias;
    }

    @Override
    public String toString() {
        return "Players_stats{" +
                "id_jugador=" + id_jugador +
                ", avg_puntos=" + avg_puntos +
                ", avg_rebotes=" + avg_rebotes +
                ", avg_asistencias=" + avg_asistencias +
                '}';
    }
}
