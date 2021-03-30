package bean;

import java.util.ArrayList;
import java.util.List;

public class RankingList {
	
	private List<PlayerRank> rankingList;
	
	public RankingList() {
		this.rankingList = new ArrayList<>();
	}

	public List<PlayerRank> getRankingList() {
		return rankingList;
	}

	public void setRankingList(List<PlayerRank> rankingList) {
		this.rankingList = rankingList;
	}
	
	public void addPlayerRank(PlayerRank playerRank) {
		this.rankingList.add(playerRank);
	}
	
	public boolean isPlayerInRankingList(Long idPlayer) {
		for(PlayerRank playerRank : this.rankingList) {
			if (playerRank.getPlayerId() == idPlayer) {
				return true;
			}
		}
		return false;
	}
	
	public PlayerRank getPlayerRank(Long idPlayer) {
		for(PlayerRank playerRank : this.rankingList) {
			if (playerRank.getPlayerId() == idPlayer) {
				return playerRank;
			}
		}
		return null;
	}
	
	public void modifyPlayerRankTotalRank(Long idPlayer, Integer scoreToAdd) {
		PlayerRank playerRank = this.getPlayerRank(idPlayer);
		if (playerRank != null) {
			playerRank.setTotalScore(playerRank.getTotalScore() + scoreToAdd);
		}
	}

	@Override
	public String toString() {
		return "RankingList = " + rankingList.toString();
	}
	
}
