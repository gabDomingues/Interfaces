package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.Locacao;
import entities.Veiculo;
import services.ServicoLocacao;
import services.TaxaServicoBrasil;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Entre com os dados do aluguel: ");
		System.out.print("Modelo do carro: ");
		String modeloCarro = sc.nextLine();
		Veiculo v1 = new Veiculo(modeloCarro);
		
		System.out.print("Retirada(dd/MM/yyyy hh:mm): ");
		String dataRetirada = sc.nextLine();
		LocalDateTime data1 = LocalDateTime.parse(dataRetirada, fmt);
		System.out.print("Retorno(dd/MM/yyyy hh:mm): ");
		String dataRetorno = sc.nextLine();
		LocalDateTime data2 = LocalDateTime.parse(dataRetorno, fmt);
		
		Locacao l1 = new Locacao(data1, data2, v1);
		 
		System.out.print("Entre com o preco por hora: ");
		double precoHora = sc.nextDouble();
		System.out.print("Entre com o preco por dia: ");
		double precoDia= sc.nextDouble();
		
		ServicoLocacao servicoLocacao =  new ServicoLocacao(precoDia, precoHora, new TaxaServicoBrasil());
		
		servicoLocacao.processarFatura(l1);
		
		System.out.println("FATURA: ");
		System.out.println("Pagamento Basico: " + l1.getFatura().getPagamentoBasico());
		System.out.println("Imposto: " + l1.getFatura().getTaxa());
		System.out.println("Pagamento total: " + l1.getFatura().getTotalPagamento());
		
		sc.close();
	}

}
