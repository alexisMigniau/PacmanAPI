package bean;

import java.sql.Timestamp;

public class Player {
	private Long id;
	private String pseudo;
	private String login;
	private String password;
	private String nationality;
	private Timestamp dateInscription;
	private Integer solde;
	
	@Override
	public String toString() {
		return "Player [id=" + id + ", pseudo=" + pseudo + ", login=" + login + ", password=" + password
				+ ", nationality=" + nationality + ", dateInscription=" + dateInscription + ", solde=" + solde + "]";
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Timestamp getDateInscription() {
		return dateInscription;
	}
	public void setDateInscription(Timestamp dateInscription) {
		this.dateInscription = dateInscription;
	}
	
	public Integer getSolde() {
		return solde;
	}
	public void setSolde(Integer solde) {
		this.solde = solde;
	}
}
