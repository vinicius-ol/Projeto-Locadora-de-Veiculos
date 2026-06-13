public abstract class Veiculo implements Exibivel {
    private String placa;
    private String modelo;
    private int ano;
    private boolean disponivel;
    private String cor;

    public Veiculo(String placa, String modelo, int ano, boolean disponivel, String cor) {
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
        this.disponivel = disponivel;
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public abstract double declararValorAluguel();

    public abstract double calcularValorAluguel();

    @Override
    public String exibir() {
        return "Placa: " + getPlaca() + " | " +
                "Modelo: " + getModelo() + " | " +
                "Ano: " + getAno() + " | " +
                "Disponivel: " + isDisponivel() + " | " +
                "Cor :" + getCor();
    }


}
