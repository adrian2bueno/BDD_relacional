package Model;

public class Team {
    private int id;
    private String nombre;

    private String franquicia;
    public Team(String nombre) {
        this.nombre = nombre;
    }

    public Team() {

    }

    public String getFranquicia() {
        return franquicia;
    }

    public void setFranquicia(String franquicia) {
        this.franquicia = franquicia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format(
                "Team (nombre=%s)", this.nombre);
    }
}
