
package cl.jRenderLove.miniGamesClass;


public class AbieGame {
    private int idPalabra;
    private String palabra;

    public AbieGame(int idPalabra, String palabra) {
        this.idPalabra = idPalabra;
        this.palabra = palabra;
    }

    public AbieGame() {
    }

    public int getIdPalabra() {
        return idPalabra;
    }

    public void setIdPalabra(int idPalabra) {
        this.idPalabra = idPalabra;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }
    
    
    
}
