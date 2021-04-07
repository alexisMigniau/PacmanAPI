<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:base>
    <jsp:body>
		<h1>Classement des joueurs</h1>
		<div class="rankingContainer">
			<div class="rankingPanel">
				<table>
					<thead>
				        <tr>
				            <th>Classement</th>
				            <th>Score total</th>
				            <th>Joueur</th>
				        	<th>Pays</th>
				        </tr>
				    </thead>
					<tbody>
						<c:forEach items="${ rankingList }" var="rank" varStatus="status">
							<tr>
								<td>
				    				<span>${ status.count }</span>
				    			</td>
				    			<td>
				    				<span>${ rank.totalScore }</span>
				    			</td>
				    			<td>
				    				<span>${ rank.pseudo }</span>
				    			</td>
				    			<td>
				    				<span>${ rank.nationality }</span>
				    			</td>
				    			<td>
				    				<button type="button" class="btnPlayer" id=${ rank.playerId }>Accéder au profil de ${ rank.pseudo }</button>
				    			</td>
				    		</tr>
				    	</c:forEach>
				    </tbody>
				</table>
			</div>
			<div class="sortingPanel"></div>
		</div>
    </jsp:body>
</t:base>

<script>
	const buttonList = document.getElementsByClassName("btnPlayer");
	for (let i=0; i<buttonList.length; i++){
		buttonList[i].onclick = () => {
			location.href = this.location.href.substring(this.location.href.lastIndexOf('/'), 0, 1) + "/profile?idPlayer=" + buttonList[i].id;
		}
	}
</script>

<style>
	h1{
		text-align: center;
		margin-top: 15px;
	}
	
	td {
		text-align: center;
		border-collapse: collapse;
		border-right: 2px solid #F9C328;
		padding: 5px 0;
	}
	
	td:last-child{
		text-align: left;
		border-right: 0;
	}
	
	th{
		color: #F9C328;
		text-align: center;
		padding: 0 30px;
	}
	
	span{
		color: white;
		text-align: center;
		padding: 0 30px;
	}
	
	button{
		text-align: left;
		margin-left: 15px;
		padding: 5px 20px;
		background-color: #F9C328;
		color: #212529;
		border: 2px solid #212529;
		border-radius: 10px;
	}
	
	.rankingContainer{
		width: 100%;
		display: flex;
		flex-wrap: nowrap;
		justify-content: center;
	}
	
	.rankingPanel{
		background-color: #212529;
		border: solid 3px #F9C328;
		padding: 15px;
		margin-top: 5px;
	}
	
	.sortingPanel{
		background-color: blue;
	}
</style>
