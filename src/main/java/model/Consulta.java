package model;

import java.util.Calendar;

/**
 *
 * @author v177950
 */
public class Consulta {
    private int id;
    private Calendar data;
    private String horario;
    private String comentario;
    private Integer idAnimal;
    private Integer idVeterinario;
    //private Integer idTratamento;
    private Integer terminado;

    public Consulta(int id, Calendar data, String horario, String comentario, Integer idAnimal, Integer idVeterinario, Integer terminado) {
        this.id = id;
        this.data = data;
        this.horario = horario;
        this.comentario = comentario;
        this.idAnimal = idAnimal;
        this.idVeterinario = idVeterinario;
        //this.idTratamento = idTratamento;
        this.terminado = terminado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public Integer getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    /*public Integer getIdTratamento() {
        return idTratamento;
    }

    public void setIdTratamento(int idTratamento) {
        this.idTratamento = idTratamento;
    }*/

    public Integer getTerminado() {
        return terminado;
    }

    public void setTerminado(int terminado) {
        this.terminado = terminado;
    }

    @Override
    public String toString() {
        return "Consulta{" + "id=" + id + ", data=" + data + ", horario=" + horario + ", comentario=" + comentario + ", idAnimal=" + idAnimal + ", idVeterinario=" + idVeterinario + ",  terminado=" + terminado + '}';
    }

    
    
}
