//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Carro carro = new Carro("ABC-1234", "Civic", 2020, true, "Preto", true);
        Moto moto = new Moto("XYZ-9876", "Titan", 2022, true, "Vermelha");
        Cliente cliente = new Cliente("nome", "email@email.com", "123.456.789-00", 1);

        System.out.println(carro.exibir());
        System.out.println(moto.exibir());
        System.out.println(cliente.exibir());
    }
    }
