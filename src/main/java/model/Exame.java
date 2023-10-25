package model;

/**
 *
 * @author v177950
 */
public class Exame {
    private int id;
    private String nome;
    private Integer idConsulta;
    
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Integer idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Exame(int id, String nome, Integer idConsulta) {
        this.id = id;
        this.nome = nome;
        this.idConsulta = idConsulta;
    }  

    @Override
    public String toString() {
        return "Exame{" + "id=" + id + ", nome=" + nome + ", idConsulta=" + idConsulta + '}';
    }
    
}
