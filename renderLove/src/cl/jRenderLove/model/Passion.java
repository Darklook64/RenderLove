
package cl.jRenderLove.model;


public class Passion {
    private int id;
    private int passion;
    private int idChica;

    public Passion(int id, int passion, int idChica) {
        this.id = id;
        this.passion = passion;
        this.idChica = idChica;
    }

    public Passion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPassion() {
        return passion;
    }

    public void setPassion(int passion) {
        this.passion = passion;
    }

    public int getIdChica() {
        return idChica;
    }

    public void setIdChica(int idChica) {
        this.idChica = idChica;
    }
    
    
    
}
