package services;

import java.time.Duration;

import entities.Fatura;
import entities.Locacao;

public class ServicoLocacao {
	
	private Double precoDia;
	private Double precoHora;
	
	private ServicoTaxa taxaServico;

	public ServicoLocacao(Double precoDia, Double precoHora, ServicoTaxa taxaServico) {
		this.precoDia = precoDia;
		this.precoHora = precoHora;
		this.taxaServico = taxaServico;
	}

	public Double getPrecoDia() {
		return precoDia;
	}

	public void setPrecoDia(Double precoDia) {
		this.precoDia = precoDia;
	}

	public Double getPrecoHora() {
		return precoHora;
	}

	public void setPrecoHora(Double precoHora) {
		this.precoHora = precoHora;
	}

	public ServicoTaxa getTaxaServico() {
		return taxaServico;
	}

	public void setTaxaServico(TaxaServicoBrasil taxaServico) {
		this.taxaServico = taxaServico;
	}
	
	public void processarFatura(Locacao locacao	) {
		
		double minutos = Duration.between(locacao.getInicio(), locacao.getFim()).toMinutes();
		double horas = minutos/60.0;
		
		double pagamentoBasico;
		if(horas <= 12.0) {
			pagamentoBasico = this.precoHora * Math.ceil(horas);
		}
		else {
			pagamentoBasico = this.precoDia * Math.ceil(horas/24.0);
		}
		
		double imposto = taxaServico.taxa(pagamentoBasico);
		
		locacao.setFatura(new Fatura(pagamentoBasico, imposto));
	}
}
