
package cl.jRenderLove.model;

public class Atraccion {
    private int id;
    private int atraccion;
    private int idChica;

    public Atraccion(int id, int atraccion, int idChica) {
        this.id = id;
        this.atraccion = atraccion;
        this.idChica = idChica;
    }

    public Atraccion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAtraccion() {
        return atraccion;
    }

    public void setAtraccion(int atraccion) {
        this.atraccion = atraccion;
    }

    public int getIdChica() {
        return idChica;
    }

    public void setIdChica(int idChica) {
        this.idChica = idChica;
    }
    
    
    
}
