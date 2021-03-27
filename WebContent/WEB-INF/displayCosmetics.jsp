<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:base>
    <jsp:body>
        <form method="post">
        	<div class="container">
        		<br />

				<br />
                <br />

				<c:if test="${ empty session }">
					<div class="row">
						<div class="row">
							<c:forEach items="${ listCosmeticsDisplay }" var="cosmetic" varStatus="status">
								<h2>
									${cosmetic.name } <span>${cosmetic.price} coins</span>
								</h2>
							</c:forEach>
						</div>

						<a href="connexion" class="btn btn-warning btn-block">Pour profiter de ces cosmetiques, connectez-vous ici</a>
					</div>
				</c:if>
				<!-- Si l'utilisateur est connecté -->
				<c:if test="${ not empty session }">
					<div class="row">
						<div class="row">
							<form method="post">
								<c:forEach items="${ listCosmeticsDisplay }" var="cosmetic" varStatus="status">
									<h2>
										<label for="${cosmetic.id }">${cosmetic.name}</label>
										<span>${cosmetic.price} coins</span>
									</h2>
									 <button class="btn btn-warning btn-block" >Acheter</button>
								</c:forEach>
							</form>
						</div>
				 	</div>
				</c:if>
        	</div>
        </form>
        
    </jsp:body>
</t:base>