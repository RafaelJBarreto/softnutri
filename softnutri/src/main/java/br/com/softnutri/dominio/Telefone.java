package br.com.softnutri.dominio;

import java.util.Objects;

import br.com.softnutri.util.Security;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "telefone")
public class Telefone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTelefone;

	@Basic(optional = true)
	@Column(name = "numero", nullable = true)
	private String numero;

	@ManyToOne(fetch=FetchType.LAZY)
	private Pessoa pessoa;

	public Long getIdTelefone() {
		return idTelefone;
	}

	public void setIdTelefone(Long idTelefone) {
		this.idTelefone = idTelefone;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = Security.encode(numero);
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idTelefone, numero, pessoa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Telefone other = (Telefone) obj;
		return Objects.equals(idTelefone, other.idTelefone) && Objects.equals(numero, other.numero)
				&& Objects.equals(pessoa, other.pessoa);
	}

	@Override
	public String toString() {
		return "Telefone [idTelefone=" + idTelefone + ", numero=" + numero + ", pessoa=" + pessoa + "]";
	}

}
