<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:base>
    <jsp:body>
		<h1>Classement des joueurs</h1>
		<div class="container">
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
				    		</tr>
				    	</c:forEach>
				    </tbody>
				</table>
			</div>
			<div class="sortingPanel"></div>
		</div>
    </jsp:body>
</t:base>