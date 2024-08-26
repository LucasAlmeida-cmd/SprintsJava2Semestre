import quantumleap.dominio.*;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);

        Guincho guincho1 = new Guincho("123", 250, 100000);
        Peca peca = new Peca("vira", 500.00, "Cotinental", "modelo1");
        Peca peca2 = new Peca("Vela", 250.00, "bosch", "modelo2");
        ProblemasExistentes problema1 = new ProblemasExistentes("nome1", "descrição1", 500.00, 3, peca);
        ProblemasExistentes problema2 = new ProblemasExistentes("nome2", "descrição2", 1000.00, 1, peca2);
        ArrayList<ProblemasExistentes> problemas = new ArrayList<ProblemasExistentes>();
        problemas.add(problema1);
        problemas.add(problema2);

        Veiculo veiculo = new Veiculo("Fiat", "Palio", DateUtil.parseYear("2005"), 100000.00, "EFG4H56");
        Cliente cliente = new Cliente("Arthur", "Arthur@gmail.com", "111111111", "senha", true, "Rio Paulo");
        Diagnostico diag1 = new Diagnostico(cliente, veiculo, problema1,guincho1);
        Agendamento ag1 = new Agendamento(diag1, "12/12/2024", "12:30");

        Veiculo veiculo2 = new Veiculo("Fiat", "Palio", DateUtil.parseYear("2005"), 100000.00, "ABC1D23");
        Cliente cliente2 = new Cliente("Arthur", "Arthur@gmail.com", "111111111", "senha", false, "São Paulo");
        Diagnostico diag2 = new Diagnostico(cliente2, veiculo, problema2, guincho1);
        Agendamento ag2 = new Agendamento(diag2, "13/12/2024", "12:30");

        ArrayList<Agendamento> agendamentos = new ArrayList<Agendamento>();
        agendamentos.add(ag1);
        agendamentos.add(ag2);

        Oficina oficina1 = new Oficina("oficina", "São Paulo", "11111111111", "oficina@gmail.com", agendamentos);
        Oficina oficina2 = new Oficina("oficina", "Rio de janeiro", "11111111111", "oficina@gmail.com", agendamentos);
        ArrayList<Oficina> listaOficina = new ArrayList<>();

        listaOficina.add(oficina1);
        listaOficina.add(oficina2);


        System.out.println("Bem vindo(a) ao Canal Porto Seguro!!!");
        System.out.println("Por favor, inseira seu nome: ");
        String nomeCliente = sc.nextLine();
        System.out.println("Insira seu email: ");
        String emailCliente = sc.nextLine();
        System.out.println("Insira seu senha: ");
        String senhaCliente = sc.nextLine();
        System.out.println("Insira seu telefone: ");
        String telefoneCliente = sc.nextLine();
        System.out.println("Informe se você é cliente Porto Seguro (sim ou não): ");
        String verificaCliente = sc.nextLine();
        System.out.println("Qual sua cidade?");
        String cidadeCliente = sc.nextLine();

        boolean clientePorto = false;
        if (verificaCliente.equalsIgnoreCase("sim")) {
            clientePorto = true;
        }

        Cliente cliente3 = new Cliente(nomeCliente, emailCliente, telefoneCliente, senhaCliente, clientePorto, cidadeCliente);



        System.out.println("Insira a marca do seu veículo: ");
        String montadoraVeiculo = sc.nextLine();
        System.out.println("Insira o modelo do seu veículo: ");
        String modeloVeiculo = sc.nextLine();
        System.out.println("Insira o ano do veículo (formato yyyy):");
        String anoVeiculoStr = sc.nextLine();
        Date anoVeiculo = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            anoVeiculo = sdf.parse(anoVeiculoStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Insira a quilometragem: ");
        double quantidadeQuilometros = sc.nextDouble();
        sc.nextLine();
        System.out.println("Insira a placa do veículo");
        String placaVeiculo = sc.nextLine();

        Veiculo veiculo3 = new Veiculo(montadoraVeiculo, modeloVeiculo, anoVeiculo, quantidadeQuilometros, placaVeiculo);

        System.out.println("A partir da lista de problemas, escreva o nome de qual melhor se encaixa no seu.");
        for (ProblemasExistentes problemasLista : problemas) {
            String nome = problemasLista.getNomeProblema();
            System.out.println(nome);
        }
        System.out.println("Por favor, escreva o nome igual ao da lista: ");
        String nome = sc.nextLine();

        ProblemasExistentes problema4 = new ProblemasExistentes();

        for(ProblemasExistentes problemasLista: problemas) {
            if (problemasLista.getNomeProblema().equals(nome)) {
                problema4 = problemasLista;
            }
        }



        Diagnostico diag3 = new Diagnostico(cliente3, veiculo3, problema4, guincho1);
        System.out.println("Você precisa de um Guincho ? (sim ou não)");
        String verificaGuincho = sc.nextLine();
        if (verificaGuincho.equalsIgnoreCase("sim")){
            if (clientePorto){
                diag3.setOrcamento(0);
            }else {
                diag3.setOrcamento(diag3.adicionandoGuincho());
            }
        }else if(verificaCliente.equalsIgnoreCase("Não")){
            if (clientePorto){
                diag3.setOrcamento(0);
            }else{
                diag3.setOrcamento(diag3.orcamentoPadrao());
            }
        }





        System.out.println("Digite o melhor dia para você ir até a oficina: (dd/mm/yyyy) ");
        String data = sc.nextLine();
        System.out.println("Digite o melhor horário para você ir até a oficina:");
        String hora = sc.nextLine();



        Agendamento ag3 = new Agendamento(diag3, data, hora);

        for(Oficina oficinas : listaOficina){
            if(oficinas.getLocalizacaoOficina().equalsIgnoreCase(cliente3.getLocalizacaoCliente())){
                ag3.setLocalizacao(oficinas.getLocalizacaoOficina());
            }
        }

        try {
            oficina1.verificaAgendamento(ag3);

            System.out.println("Olá senhor(a) " +cliente3.getNomeCliente() + ", seu veículo " + veiculo3.getModeloVeiculo() + " de placa " + veiculo3.getPlacaVeiculo() + " foi agendado. \nVocê deverá levar seu veículo no dia " +
                    ag3.getData() + " às " + ag3.getHora() + ", o seu orçamento será R$" + diag3.getOrcamento() + "\nA oficia está localizada na cidade: "+ ag3.getLocalizacao()+". Muito obrigado por escolher a Porto Seguro!!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}
