public class Cliente implements Exibivel {
    private static int contadorId = 0;

    private String nome;
    private String email;
    private String cpf;
    private int id;

    public Cliente(String nome, String email, String cpf) {
        this.id = ++contadorId;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
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

    @Override
    public String exibir() {
        return "ID: " + getId() + " | " +
                "Nome: " + getNome() + " | " +
                "Email: " + getEmail() + " | " +
                "CPF: " + getCpf();
    }
}
