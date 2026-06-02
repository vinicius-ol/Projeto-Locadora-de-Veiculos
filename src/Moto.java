public class Moto extends Veiculo {

    public Moto(String placa, String modelo, int ano, boolean disponivel, String cor) {
        super(placa, modelo, ano, disponivel, cor);
    }

    @Override
    public double declararValorAluguel() {
        return 50.0; 
    }

    @Override
    public double calcularValorAluguel() {
        return declararValorAluguel() + 15.0; 
    }

    @Override
    public String exibir() {
        return super.exibir() + " | Diária: R$ " + String.format("%.2f", declararValorAluguel()) + 
               " | Com Taxa de Equipamento: R$ " + String.format("%.2f", calcularValorAluguel());
    }
}
