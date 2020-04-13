package gb.paqueteria.gbpaqueteria.admin;

public class Ofertas {
    public   String name;
    public   String description;

    public Ofertas() {
    }

    public Ofertas(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
