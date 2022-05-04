package br.com.softnutri.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.softnutri.dominio.Pessoa;
import br.com.softnutri.util.Security;

public class PessoaDto {

	private String nome;
	private String email;
	private String cpf;
	private LocalDate dataNascimento;
	private String endereco;
	private BigDecimal altura;
	private List<TelefoneDto> telefones;

	public PessoaDto(Pessoa pessoa) {
		this.nome = Security.decode(pessoa.getNome());
		this.email = Security.decode(pessoa.getEmail());
		this.cpf = Security.decode(pessoa.getCpf());
		this.dataNascimento = pessoa.getDataNascimento();
		this.endereco = Security.decode(pessoa.getEndereco());
		this.telefones = new ArrayList<>();
		this.telefones.addAll(pessoa.getTelefones().stream().map(TelefoneDto::new).toList());
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getCpf() {
		return cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getAltura() {
		return altura;
	}

	public static List<PessoaDto> converter(List<Pessoa> pessoas) {
		return pessoas.stream().map(PessoaDto::new).toList();
	}

}
