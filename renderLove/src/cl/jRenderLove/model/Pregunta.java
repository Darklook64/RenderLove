
package cl.jRenderLove.model;


public class Pregunta {
    private int idChica;
    private int idPregunta;
    private String pregunta;
    private String respuesta;
    private String respuesta2;
    private String respuesta3;
    private String endQuestion;


    

    public Pregunta(int idChica,int idPregunta, String pregunta, String respuesta, String respuesta2, String respuesta3,String endQuestion) {
        this.idChica = idChica;
        this.idPregunta= idPregunta;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.respuesta2 = respuesta2;
        this.respuesta3 = respuesta3;
        this.endQuestion= endQuestion;
    }

    public Pregunta() {
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }
    
    

    public int getIdChica() {
        return idChica;
    }

    public void setIdChica(int idChica) {
        this.idChica = idChica;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getRespuesta2() {
        return respuesta2;
    }

    public void setRespuesta2(String respuesta2) {
        this.respuesta2 = respuesta2;
    }

    public String getRespuesta3() {
        return respuesta3;
    }

    public void setRespuesta3(String respuesta3) {
        this.respuesta3 = respuesta3;
    }
    
        public String getEndQuestion() {
        return endQuestion;
    }

    public void setEndQuestion(String endQuestion) {
        this.endQuestion = endQuestion;
    }
    

    
    
    
}
