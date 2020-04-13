package gb.paqueteria.gbpaqueteria.admin;

public class Subir {


    private  Integer Uid;
    private String name;
    private String ImagenUri;
    private String description;

    public Subir() {
    }

    public Subir(String name, String imagenUri, String description, int id) {
        if (name.trim().equals("") && description.trim().equals(""))
        {
            name = "No hay Nombre";
            description = "No hay Descripcion";

        }
        //this.Uid = uid;
        this.Uid = id;
        this.name = name;
        this.ImagenUri = imagenUri;
        this.description = description;
        //this.mDescription =  description;
    }
    public Integer getUid() {
        return Uid;
    }

    public void setUid(Integer uid) {
        Uid = uid;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagenUri() {
        return ImagenUri;
    }

    public void setImagenUri(String ImagenUri) {
        this.ImagenUri = ImagenUri;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
