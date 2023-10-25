package model;

/**
 *
 * @author v177950
 */
public class Cliente {
    private int id;
    private String nome;
    private String endereco;
    private String telefone;
    private String CEP;
    private String email;

    public Cliente(int id, String nome, String endereco, String cep, String email, String telefone){
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.CEP = cep;
        this.email = email;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCEP() {
        return this.CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        if(!email.equals("")) this.email = email;
    } 
    
    @Override
    public String toString() {
        return "Cliente{" + "nome=" + nome + ", endereco=" + endereco + ", cep=" + CEP + ", email=" + email+ ", telefone=" + telefone + '}';
    }
}
