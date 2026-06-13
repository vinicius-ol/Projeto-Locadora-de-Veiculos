import java.util.List;
import java.util.Scanner;

public class Menu {

    private Locadora locadora;
    private Scanner scanner;
    private SAC sac;

    public Menu(Locadora locadora) {
        this.locadora = locadora;
        this.scanner = new Scanner(System.in);
        this.sac = new SAC();
    }

    public void iniciar() {
        boolean rodando = true;

        while (rodando) {

            System.out.println("\n=== " + locadora.getNome() + " ===");
            System.out.println("1 - Alugar veículo");
            System.out.println("2 - SAC");
            System.out.println("3 - Sair");
            System.out.print("Escolha: ");

            int opcao = lerInteiro();

            switch (opcao) {

                case 1 -> alugarVeiculo();

                case 2 -> menuSac();

                case 3 -> {
                    rodando = false;
                    System.out.println("Até logo!");
                }

                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void alugarVeiculo() {

        List<Veiculo> disponiveis = locadora.buscarDisponiveis();

        if (disponiveis.isEmpty()) {
            System.out.println("Nenhum veículo disponível no momento.");
            return;
        }

        System.out.println("\n--- Veículos disponíveis ---");

        for (int i = 0; i < disponiveis.size(); i++) {
            System.out.println((i + 1) + " - " + disponiveis.get(i).exibir());
        }

        System.out.print("\nEscolha o número do veículo: ");
        int escolha = lerInteiro();

        if (escolha < 1 || escolha > disponiveis.size()) {
            System.out.println("Veículo inválido.");
            return;
        }

        Veiculo veiculo = disponiveis.get(escolha - 1);

        System.out.println("\n--- Documentos do cliente ---");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("CNH (habilitação): ");
        String cnh = scanner.nextLine();

        if (!conferirDocumentos(nome, cpf, cnh)) {
            System.out.println("Documentos inválidos. Aluguel cancelado.");
            return;
        }

        Cliente cliente = new Cliente(nome, email, cpf);

        double valor = locadora.realizarAluguel(veiculo, cliente);

        System.out.println("\nAluguel realizado com sucesso!");
        System.out.println(cliente.exibir());
        System.out.println("Valor da diária: R$ " + String.format("%.2f", valor));
        System.out.println("Retire o veículo em: " + locadora.getLocalizacao());
    }

    private void menuSac() {

        boolean voltar = false;

        while (!voltar) {

            System.out.println("\n=== SAC ===");
            System.out.println("1 - Registrar reclamação");
            System.out.println("2 - Registrar sugestão");
            System.out.println("3 - Registrar dúvida");
            System.out.println("4 - Listar chamados");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            int opcao = lerInteiro();

            switch (opcao) {

                case 1 -> {
                    System.out.print("Digite sua reclamação: ");
                    String descricao = scanner.nextLine();
                    sac.abrirChamado("Reclamação", descricao);
                }

                case 2 -> {
                    System.out.print("Digite sua sugestão: ");
                    String descricao = scanner.nextLine();
                    sac.abrirChamado("Sugestão", descricao);
                }

                case 3 -> {
                    System.out.print("Digite sua dúvida: ");
                    String descricao = scanner.nextLine();
                    sac.abrirChamado("Dúvida", descricao);
                }

                case 4 -> sac.listarChamados();

                case 0 -> voltar = true;

                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private boolean conferirDocumentos(String nome, String cpf, String cnh) {

        return nome != null
                && !nome.trim().isEmpty()
                && cpf != null
                && cpf.replaceAll("\\D", "").length() == 11
                && cnh != null
                && !cnh.trim().isEmpty();
    }

    private int lerInteiro() {

        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Digite um número válido: ");
        }

        int valor = scanner.nextInt();
        scanner.nextLine();

        return valor;
    }
}