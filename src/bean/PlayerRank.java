package bean;

public class PlayerRank implements Comparable<PlayerRank> {
	private Long playerId;
	private String pseudo;
	private Integer totalScore;
	private String nationality;
	
	public PlayerRank(Long playerId, String pseudo, Integer totalScore, String nationality) {
		this.playerId = playerId;
		this.pseudo = pseudo;
		this.totalScore = totalScore;
		this.nationality = nationality;
	}

	public Long getPlayerId() {
		return playerId;
	}
	public void setPlayer_id(Long playerId) {
		this.playerId = playerId;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public Integer getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	@Override
	public int compareTo(PlayerRank o) {
		if (this.totalScore < o.getTotalScore()) {
			return 1;
		} else if (this.totalScore > o.getTotalScore()) {
			return -1;
		} else {
			return 0;
		}
	}
	
	@Override
	public String toString() {
		return "PlayerRank [playerId=" + playerId + ", pseudo=" + pseudo + ", totalScore=" + totalScore + ", nationality="
				+ nationality + "]";
	}
}
