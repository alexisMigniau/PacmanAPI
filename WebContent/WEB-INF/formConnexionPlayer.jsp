<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:base>
    <jsp:body>
        <form method="post">
        	<div class="container-sm">
        		<br />
        		<div class="row ">
        			<div class="form-group col">
		                <label for="login">Login</label>
		                <input class="form-control" type="text" id="login" name="login" value="${utilisateur.login}" size="20" maxlength="20"/>
		                
	                </div>
                </div>
				<br />
				<div class="row">
					<div class="form-group col">
		                <label for="motdepasse">Mot de passe</label>
		                <input class="form-control" type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
		            </div>
                </div>
        
                <br />
                <div class="row">
               		<button type="submit" class="btn btn-warning btn-block">Connexion</button>
                </div>
                <br />
           
                <c:if test="${erreur}">
               		<div class="row alert alert-danger" role="alert">
               			Aucune compte trouver avec ce couple login, mot de passe.
               		</div>
               	</c:if>
        	</div>
        </form>
        
    </jsp:body>
</t:base>