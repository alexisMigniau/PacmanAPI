<%@taglib prefix="t" tagdir="/WEB-INF/tags" %> <%@taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:base>
	<jsp:body>
		<c:if test="${ empty session }">
			<h1>Historique</h1>
			<div class="alertBox">
				<p class="alertMessage">Veuillez vous connecter pour accéder à votre historique de parties</p>
			</div>
		</c:if>
		<c:if test="${ not empty session }">
			<h1>Historique de ${ player.pseudo }</h1>
			<div class="gamesContainer">
				<div class="gamesTable">
					<table>
							<thead>
								<tr>
									<th>N°</th>
									<th>Score</th>
									<th>Durée (s)</th>
									<th>Date</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${ games }" var="game" varStatus="status">
									<tr>
										<td>
											<span>${ status.count }</span>
										</td>
										<td>
											<span>${ game.score }</span>
										</td>
										<td>
											<span>${ game.time }</span>
										</td>
										<td>
											<span>${ game.date }</span>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
				</div>
			</div>
		</c:if>
	</jsp:body>
</t:base>

<style>
	h1 {
		text-align: center;
		margin-top: 15px;
	}

	th {
		color: #f9c328;
		text-align: center;
		padding: 0 50px;
		padding-bottom: 3px;
	}
	
	tr{
		border-bottom: 1px dotted #f9c328;
	}
	
	tr:last-child {
		border-bottom: 0;
	}
	
	td {
		text-align: center;
		border-collapse: collapse;
		border-right: 2px solid #f9c328;
		padding: 15px 0;
	}

	td:last-child {
		text-align: left;
		border-right: 0;
	}

	span {
		color: white;
		text-align: center;
		padding: 0 30px;
	}

	.gamesContainer {
		width: 100%;
		display: flex;
		justify-content: center;
	}
	
	.gamesTable{
		background-color: #212529;
		border: solid 3px #f9c328;
		padding: 15px 80px;
		margin-top: 5px;
	}
	
	.alertBox{
		width: 100%;
		text-align: center;
		display: flex;
		justify-content: center;
	}
	
	.alertMessage{
		text-align: center;
		align-self: center;
		color: #f9c328;
		font-weight: bold;
		background-color: #212529;
		border: dashed 3px #f9c328;
		padding: 15px 60px;
	}
</style>
