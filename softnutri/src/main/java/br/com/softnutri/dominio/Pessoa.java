package br.com.softnutri.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long idPessoa;

	@Basic(optional = false)
	@Column(name = "nome", nullable = false, length = 150)
	protected String nome;

	@Basic(optional = false)
	@Column(name = "email", unique = true, nullable = false)
	protected String email;

	@Basic(optional = false)
	@Column(name = "cpf", nullable = false)
	protected String cpf;

	@Basic(optional = false)
	@Column(name = "dataNascimento", nullable = false)
	protected LocalDate dataNascimento;

	@Basic(optional = false)
	@Column(name = "endereco", nullable = false)
	protected String endereco;

	@Enumerated(EnumType.STRING)
	@Basic(optional = false)
	@Column(name = "sexo", nullable = false, length = 1)
	protected Sexo sexo;

	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	protected List<Telefone> telefones = new ArrayList<>();

	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
	protected List<HistoricoCorporal> historicoPeso = new ArrayList<>();

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public List<HistoricoCorporal> getHistoricoPeso() {
		return historicoPeso;
	}

	public void setHistoricoPeso(List<HistoricoCorporal> historicoPeso) {
		this.historicoPeso = historicoPeso;
	}

}
