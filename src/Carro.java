public class Carro extends Veiculo {
    private boolean estepe;

    public Carro(String placa, String modelo, int ano, boolean disponivel, String cor, boolean estepe) {
        super(placa, modelo, ano, disponivel, cor);
        this.estepe = estepe;
    }

    public boolean isEstepe() {
        return estepe;
    }

    public void setEstepe(boolean estepe) {
        this.estepe = estepe;
    }

    @Override
    public double declararValorAluguel() {
        return 100.0; 
    }

    @Override
    public double calcularValorAluguel() {
        return declararValorAluguel() + (declararValorAluguel() * 0.10); 
    }

    @Override
    public String exibir() {
        return super.exibir() + " | Estepe: " + isEstepe();
    }
}
