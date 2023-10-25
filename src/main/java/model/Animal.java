package model;

/**
 *
 * @author v177950
 */
public class Animal {
    private int id;
    private String nome;
    private Integer anoNascimento;
    private String sexo;
    private Integer idEspecie;
    private Integer idCliente;

    public Animal(int id, String nome, Integer anoNascimento, String sexo, Integer idEspecie, Integer idCliente) {
        this.id = id;
        this.nome = nome;
        this.anoNascimento = anoNascimento;
        this.sexo = sexo;
        this.idEspecie = idEspecie;
        this.idCliente = idCliente;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(Integer idEspecie) {
        this.idEspecie = idEspecie;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", nome=" + nome + ", anoNascimento=" + anoNascimento + ", sexo=" + sexo + ", idEspecie=" + idEspecie + ", idCliente=" + idCliente + '}';
    }
    
    
    
    
}
