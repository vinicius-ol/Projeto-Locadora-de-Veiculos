import java.util.ArrayList;

public class SAC {

    private ArrayList<Atendimento> atendimentos;
    private int proximoProtocolo;

    public SAC() {
        atendimentos = new ArrayList<>();
        proximoProtocolo = 1;
    }

    public void abrirChamado(String tipo, String descricao) {

        Atendimento atendimento =
                new Atendimento(proximoProtocolo, tipo, descricao);

        atendimentos.add(atendimento);

        System.out.println("\nChamado registrado com sucesso!");
        System.out.println("Número do protocolo: " + proximoProtocolo);

        proximoProtocolo++;
    }

    public void listarChamados() {

        if (atendimentos.isEmpty()) {
            System.out.println("\nNenhum chamado registrado.");
            return;
        }

        System.out.println("\n===== CHAMADOS REGISTRADOS =====");

        for (Atendimento atendimento : atendimentos) {
            atendimento.exibir();
        }
    }

    public Atendimento buscarChamado(int protocolo) {

        for (Atendimento atendimento : atendimentos) {

            if (atendimento.getProtocolo() == protocolo) {
                return atendimento;
            }
        }

        return null;
    }

    public void atualizarStatus(int protocolo, String novoStatus) {

        Atendimento atendimento = buscarChamado(protocolo);

        if (atendimento != null) {

            atendimento.setStatus(novoStatus);

            System.out.println(
                    "\nStatus atualizado para: "
                            + novoStatus
            );

        } else {

            System.out.println(
                    "\nChamado não encontrado."
            );
        }
    }
}