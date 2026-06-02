public class Cliente implements Exibir {
    private String nome;
    private String email;
    private String cpf;
    private int id;

    public Cliente(String nome, String email, String cpf, int id) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String exibir() {
        return "ID: " + getId() + " | " +
                "Nome: " + getNome() + " | " +
                "Email: " + getEmail() + " | " +
                "CPF: " + getCpf();
    }
}
