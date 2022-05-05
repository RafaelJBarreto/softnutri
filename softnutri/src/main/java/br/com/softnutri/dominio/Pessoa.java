package br.com.softnutri.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.softnutri.util.Criptografia;
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {

	@Id
	@Column
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
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "pessoaPapel",
            joinColumns = {
            @JoinColumn(name = "idPessoa")
            },
            inverseJoinColumns = {
            @JoinColumn(name = "idPapel") })
    private Set<Papel> papel;
	
	public Set<Papel> getPapel() {
		return papel;
	}

	public void setPapel(Set<Papel> papel) {
		this.papel = papel;
	}

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
		this.nome = Criptografia.encode(nome);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = Criptografia.encode(email);
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = Criptografia.encode(cpf);
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
		this.endereco = Criptografia.encode(endereco);
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
