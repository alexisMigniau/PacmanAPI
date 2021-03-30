package bean;

import java.sql.Timestamp;

public class Game {
	private Long id;
	private Player player;
	private Integer score;
	private Integer time;
	private Timestamp date;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Game [id=" + id + ", player=" + player + ", score=" + score + ", time=" + time + ", date=" + date + "]";
	}

}
