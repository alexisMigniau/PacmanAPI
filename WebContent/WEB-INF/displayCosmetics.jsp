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
							<h2>-- Vos cosmetics -- </h2>
							<c:forEach items="${ listCosmeticsDisplayPossessed }" var="cosmetic" varStatus="status">
								<p>
									<label for="${cosmetic.id }">${cosmetic.name}</label>
									<span>${cosmetic.price} coins</span>
									<input type="hidden" name="id_cosmetic" value="${cosmetic.id}" />
									<input type="hidden" name="id_player" value="${player.id}" />
									<input type="hidden" name="price" value="${cosmetic.price}" />
									<input type="hidden" name="solde" value="${player.solde}" />
								</p>
							</c:forEach>
							<h2> -- Les cosmetics disponibles a l'achat -- </h2>
							<form method="post">
								<c:forEach items="${ listCosmeticsDisplay }" var="cosmetic" varStatus="status">
									<h3>
										<label for="${cosmetic.id }">${cosmetic.name}</label>
										<span>${cosmetic.price} coins</span>
										<input type="hidden" name="id_cosmetic" value="${cosmetic.id}" />
										<input type="hidden" name="id_player" value="${player.id}" />
										<input type="hidden" name="price" value="${cosmetic.price}" />
										<input type="hidden" name="solde" value="${player.solde}" />
									</h3>
									 <button type="submit" class="btn btn-warning btn-block">Acheter</button>
								</c:forEach>
								<h2> -- Crediter son compte de coin -- </h2>
								<button type="submit" class="btn btn-warning btn-block">Crediter +500 coins</button>
							</form>
						</div>
				 	</div>
				</c:if>
				<c:if test="${not empty form.resultat}">
					<div class="row alert alert-${empty form.erreurs ? 'success' : 'danger'}" role="alert">
						${form.resultat} 
					</div>
				</c:if>
        	</div>
        </form>
        
    </jsp:body>
</t:base>