package gb.paqueteria.gbpaqueteria.cliente;

public class Destinos {

    public   Double latitud;
    public   Double longitud;
    public   String codigo;
    public   String billete;
    public   String telefono;

    public Destinos() {
    }

    public Destinos(Double latitud, Double longitud, String codigo, String billete, String telefono) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.codigo = codigo;
        this.billete = billete;
        this.telefono = telefono;
    }
    public Destinos(String codigo, String telefono) {
        this.codigo = codigo;
        this.telefono = telefono;
    }
}
