package br.com.softnutri.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.softnutri.dominio.Pessoa;
import br.com.softnutri.util.Criptografia;

public class PessoaDTO {

	private String nome;
	private String email;
	private String cpf;
	private LocalDate dataNascimento;
	private String endereco;
	private BigDecimal altura;
	private List<TelefoneDTO> telefones;

	public PessoaDTO(Pessoa pessoa) {
		this.nome = Criptografia.decode(pessoa.getNome());
		this.email = Criptografia.decode(pessoa.getEmail());
		this.cpf = Criptografia.decode(pessoa.getCpf());
		this.dataNascimento = pessoa.getDataNascimento();
		this.endereco = Criptografia.decode(pessoa.getEndereco());
		this.telefones = new ArrayList<>();
		this.telefones.addAll(pessoa.getTelefones().stream().map(TelefoneDTO::new).toList());
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

	public static List<PessoaDTO> converter(List<Pessoa> pessoas) {
		return pessoas.stream().map(PessoaDTO::new).toList();
	}

}