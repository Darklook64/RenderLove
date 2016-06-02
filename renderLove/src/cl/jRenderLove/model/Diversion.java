
package cl.jRenderLove.model;


public class Diversion {
    private int id;
    private int diversion;
    private int idChica;

    public Diversion(int id, int diversion, int idChica) {
        this.id = id;
        this.diversion = diversion;
        this.idChica = idChica;
    }

    public Diversion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiversion() {
        return diversion;
    }

    public void setDiversion(int diversion) {
        this.diversion = diversion;
    }

    public int getIdChica() {
        return idChica;
    }

    public void setIdChica(int idChica) {
        this.idChica = idChica;
    }
    
    
    
}
