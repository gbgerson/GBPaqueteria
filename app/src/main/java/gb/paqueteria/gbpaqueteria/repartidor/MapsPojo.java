package gb.paqueteria.gbpaqueteria.repartidor;

public class MapsPojo {
    // creamos una clase con variables privadas o usando el encapsulamiento
    private double latitud;
    private double longitud;
    private String codigo;
    private  String telefono;


    // luego creamos un constructor vacio
    public MapsPojo() {
    }
    // luego un constructor con paraemtros
    public MapsPojo(double latitud, double longitud, String codigo, String telefono) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.codigo = codigo;
        this.telefono = telefono;
    }

    // luego generamos los setter y getters de las varibles antes creadas

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
