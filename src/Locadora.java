import java.util.ArrayList;
import java.util.List;

public class Locadora {
    private String nome;
    private String endereco;
    private int numero;
    private List<Veiculo> frota;

    public Locadora(String nome, String endereco, int numero) {
        this.nome = nome;
        this.endereco = endereco;
        this.numero = numero;
        this.frota = new ArrayList<>();
        inicializarFrota();
    }

    private void inicializarFrota() {
        frota.add(new Carro("ABC-1234", "Honda Civic", 2020, true, "Preto", true));
        frota.add(new Carro("DEF-5678", "Toyota Corolla", 2021, true, "Branco", true));
        frota.add(new Moto("GHI-9012", "Honda Titan", 2022, true, "Vermelha"));
        frota.add(new Moto("JKL-3456", "Yamaha Factor", 2023, true, "Azul"));
    }

    public List<Veiculo> buscarDisponiveis() {
        List<Veiculo> disponiveis = new ArrayList<>();
        for (Veiculo veiculo : frota) {
            if (veiculo.isDisponivel()) {
                disponiveis.add(veiculo);
            }
        }
        return disponiveis;
    }

    public double realizarAluguel(Veiculo veiculo, Cliente cliente) {
        veiculo.setDisponivel(false);
        return veiculo.declararValorAluguel();
    }

    public String getLocalizacao() {
        return endereco + ", nº " + numero;
    }

    public String getNome() {
        return nome;
    }
}
