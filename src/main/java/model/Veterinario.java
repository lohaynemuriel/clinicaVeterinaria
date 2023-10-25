package model;

/**
 *
 * @author v177950
 */
public class Veterinario {
    private int id;
    private String nome;
    private String email;
    private String telefone;

    public Veterinario(int id, String nome, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String endereco) {
        this.email = endereco;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Veterinario{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + '}';
    }
    
}
