<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:base>
    <jsp:body>
        <form method="post">
        	<div class="container">
        		<br />
        		<div class="row">
					<c:forEach items="${ listCosmeticsDisplay }" var="cosmetic" varStatus="status">
						<h2>N°
						<c:out value="${ status.count }" /> :
						${cosmetic.name } à <span>${cosmetic.price} coins</span></h2>
					</c:forEach>
                </div>
				<br />

                <div class="row">
               		<button type="submit" class="btn btn-warning btn-block">Inscription</button>
                </div>
                <br />
           
                <c:if test="${not empty form.resultat}">
               		<div class="row alert alert-${empty form.erreurs ? 'success' : 'danger'}" role="alert">
               			${form.resultat} 
               		</div>
               	</c:if>
        	</div>
        </form>
        
    </jsp:body>
</t:base>