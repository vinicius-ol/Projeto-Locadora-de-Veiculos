import java.util.List;
import java.util.Scanner;

public class Menu {
    private Locadora locadora;
    private Scanner scanner;

    public Menu(Locadora locadora) {
        this.locadora = locadora;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n=== " + locadora.getNome() + " ===");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Alugar veículo");
            System.out.println("3 - Consultar veículos");
            System.out.println("4 - Consultar clientes");
            System.out.println("5 - Sair");
            System.out.print("Escolha: ");

            int opcao = lerInteiro();

            switch (opcao) {
                case 1 -> menuCadastrarCliente();
                case 2 -> menuAlugarVeiculo();
                case 3 -> menuConsultarVeiculos();
                case 4 -> menuConsultarClientes();
                case 5 -> {
                    rodando = false;
                    System.out.println("Até logo!");
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void menuCadastrarCliente() {
        System.out.println("\n--- Cadastro de Cliente ---");
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        if (locadora.buscarClientePorCpf(cpf) != null) {
            System.out.println("Cliente com esse CPF já está cadastrado.");
            return;
        }

        if (cpf.replaceAll("\\D", "").length() != 11) {
            System.out.println("CPF inválido. Cadastro cancelado.");
            return;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        if (nome.trim().isEmpty() || email.trim().isEmpty()) {
            System.out.println("Nome e email não podem estar vazios. Cadastro cancelado.");
            return;
        }

        Cliente cliente = new Cliente(nome, email, cpf);
        locadora.cadastrarCliente(cliente);

        System.out.println("\nCliente cadastrado com sucesso!");
        System.out.println(cliente.exibir());
        System.out.println("Locadora: " + locadora.getNome());
        System.out.println("Endereço: " + locadora.getLocalizacao());

        System.out.print("\nDeseja escolher um veículo agora? (1 - Sim / 2 - Não): ");
        int opcao = lerInteiro();

        if (opcao == 1) {
            System.out.print("CNH (habilitação): ");
            String cnh = scanner.nextLine();

            if (cnh.trim().isEmpty()) {
                System.out.println("CNH inválida. Escolha de veículo cancelada.");
                return;
            }

            Veiculo veiculo = selecionarVeiculo();
            if (veiculo != null) {
                exibirConfirmacaoAluguel(cliente, veiculo);
            }
        }
    }

    private void menuAlugarVeiculo() {
        System.out.println("\n--- Alugar Veículo ---");
        System.out.print("Informe o CPF do cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = locadora.buscarClientePorCpf(cpf);

        if (cliente == null) {
            System.out.println("Cliente não encontrado. Por favor, cadastre-se primeiro (opção 1).");
            return;
        }

        System.out.print("CNH (habilitação): ");
        String cnh = scanner.nextLine();

        if (cnh.trim().isEmpty()) {
            System.out.println("CNH inválida. Aluguel cancelado.");
            return;
        }

        Veiculo veiculo = selecionarVeiculo();
        if (veiculo != null) {
            exibirConfirmacaoAluguel(cliente, veiculo);
        }
    }

    private Veiculo selecionarVeiculo() {
        List<Veiculo> disponiveis = locadora.buscarDisponiveis();

        if (disponiveis.isEmpty()) {
            System.out.println("Nenhum veículo disponível no momento.");
            return null;
        }

        System.out.println("\n--- Veículos disponíveis ---");
        System.out.println("1 - Carros");
        System.out.println("2 - Motos");
        System.out.println("3 - Todos");
        System.out.print("Escolha o tipo: ");
        int tipo = lerInteiro();

        List<Veiculo> opcoes = disponiveis.stream()
                .filter(v -> tipo == 3
                        || (tipo == 1 && v instanceof Carro)
                        || (tipo == 2 && v instanceof Moto))
                .toList();

        if (opcoes.isEmpty()) {
            System.out.println("Nenhum veículo disponível para o tipo selecionado.");
            return null;
        }

        for (int i = 0; i < opcoes.size(); i++) {
            System.out.println((i + 1) + " - " + opcoes.get(i).exibir());
        }

        System.out.print("\nEscolha o número do veículo: ");
        int escolha = lerInteiro();

        if (escolha < 1 || escolha > opcoes.size()) {
            System.out.println("Veículo inválido.");
            return null;
        }

        return opcoes.get(escolha - 1);
    }

    private void exibirConfirmacaoAluguel(Cliente cliente, Veiculo veiculo) {
        double valor = locadora.realizarAluguel(veiculo, cliente);

        System.out.println("\n========== Comprovante de Aluguel ==========");
        System.out.println("Cliente:  " + cliente.exibir());
        System.out.println("Veículo:  " + veiculo.exibir());
        System.out.println("Locadora: " + locadora.getNome());
        System.out.println("Endereço: " + locadora.getLocalizacao());
        System.out.println("Valor da diária: R$ " + String.format("%.2f", valor));
        System.out.println("============================================");
    }

    private void menuConsultarVeiculos() {
        boolean voltar = false;

        while (!voltar) {
            System.out.println("\n--- Consultar Veículos ---");
            System.out.println("1 - Ver todos");
            System.out.println("2 - Ver disponíveis");
            System.out.println("3 - Ver indisponíveis");
            System.out.println("4 - Voltar");
            System.out.print("Escolha: ");

            int opcao = lerInteiro();

            switch (opcao) {
                case 1 -> listarVeiculos(locadora.getFrota(), "Todos os veículos");
                case 2 -> listarVeiculos(locadora.buscarDisponiveis(), "Veículos disponíveis");
                case 3 -> {
                    List<Veiculo> indisponiveis = locadora.getFrota().stream()
                            .filter(v -> !v.isDisponivel())
                            .toList();
                    listarVeiculos(indisponiveis, "Veículos indisponíveis");
                }
                case 4 -> voltar = true;
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void listarVeiculos(List<Veiculo> veiculos, String titulo) {
        System.out.println("\n--- " + titulo + " ---");
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo encontrado.");
            return;
        }
        for (Veiculo v : veiculos) {
            System.out.println(v.exibir());
        }
    }

    private void menuConsultarClientes() {
        System.out.println("\n--- Clientes Cadastrados ---");
        List<Cliente> clientes = locadora.getClientes();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        System.out.println("1 - Listar todos");
        System.out.println("2 - Buscar por CPF");
        System.out.print("Escolha: ");
        int opcao = lerInteiro();

        switch (opcao) {
            case 1 -> clientes.forEach(c -> System.out.println(c.exibir()));
            case 2 -> {
                System.out.print("CPF: ");
                String cpf = scanner.nextLine();
                Cliente encontrado = locadora.buscarClientePorCpf(cpf);
                if (encontrado != null) {
                    System.out.println(encontrado.exibir());
                } else {
                    System.out.println("Cliente não encontrado.");
                }
            }
            default -> System.out.println("Opção inválida.");
        }
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
