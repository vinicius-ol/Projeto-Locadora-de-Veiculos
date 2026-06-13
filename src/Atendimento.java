public class Atendimento {

    private int protocolo;
    private String tipo;
    private String descricao;
    private String status;

    public Atendimento(int protocolo, String tipo, String descricao) {
        this.protocolo = protocolo;
        this.tipo = tipo;
        this.descricao = descricao;
        this.status = "Aberto";
    }

    public int getProtocolo() {
        return protocolo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void exibir() {
        System.out.println("\n========================");
        System.out.println("PROTOCOLO: " + protocolo);
        System.out.println("TIPO: " + tipo);
        System.out.println("DESCRIÇÃO: " + descricao);
        System.out.println("STATUS: " + status);
        System.out.println("========================");
    }
}