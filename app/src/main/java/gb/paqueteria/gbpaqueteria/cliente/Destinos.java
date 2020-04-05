package gb.paqueteria.gbpaqueteria.cliente;

public class Destinos {

    public   Double latitud;
    public   Double longitud;
    public   String codigo;
    public   String billete;
    public   String telefono;

    public Destinos() {
        super();
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

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getBillete() {
        return billete;
    }

    public void setBillete(String billete) {
        this.billete = billete;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


}
