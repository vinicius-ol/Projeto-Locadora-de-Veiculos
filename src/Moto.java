public class Moto extends Veiculo {

    public Moto(String placa, String modelo, int ano, boolean disponivel, String cor) {
        super(placa, modelo, ano, disponivel, cor);
    }

    @Override
    public double declararValorAluguel() {
        return 0;
    }
}
