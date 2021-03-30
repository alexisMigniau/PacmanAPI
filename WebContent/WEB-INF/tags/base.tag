<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Pacman</title>
		
		<!-- Bootstrap CSS -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
		<link rel="stylesheet" href="//use.fontawesome.com/releases/v5.0.7/css/all.css">
	</head>
	<body>
		<nav class="navbar navbar-dark bg-dark">
			<div class="container justify-content-center">
				<img src="https://upload.wikimedia.org/wikipedia/fr/thumb/a/a2/Pac-Man_Logo.svg/1280px-Pac-Man_Logo.svg.png" height="250px"/>
		    </div>
			<div class="container justify-content-around">
				<div class="row">
					<div class="col">
						<a class="btn btn-outline-warning" href="./exemple.jar" role="button">Télécharger</a>
					</div>
					<div class="col">
						<a class="btn btn-outline-warning" href="ranking" role="button">Classement</a>
					</div>
					<div class="col">
						<a class="btn btn-outline-warning" href="#" role="button">Parties</a>
					</div>
					<div class="col">
						<a class="btn btn-outline-warning" href="cosmetics" role="button">Cosmétiques</a>
					</div>
					
					<!-- Si l'utilisateur n'est pas connecté -->
					<c:if test="${empty sessionScope.player }">
						<div class="col">
							<a class="btn btn-outline-warning" href="inscription" role="button">Inscription</a>
						</div>
						<div class="col">
							<a class="btn btn-outline-warning" href="connexion" role="button">Connexion</a>
						</div>
					</c:if>
					<!-- Si l'utilisateur est connecté -->
					<c:if test="${not empty sessionScope.player }">
						<div class="col">
							<a class="btn btn-outline-warning" href="#" role="button">Profil</a>
						</div>
						<div class="col">
							<a class="btn btn-outline-warning" href="logout" role="button">Déconnexion</a>
						</div>
						<div class="col">
							<p class="text-warning"><strong>Login</strong> ${sessionScope.player.login }</a>
						</div>
						<div class="col">
							<p class="text-warning">${sessionScope.player.solde } <i class="fab fa-bitcoin"></i></a>
						</div>
					</c:if>
				 </div>
		    </div>
		</nav>
		
		<div id="body">
	  		<jsp:doBody/>
		</div>
		
		<div id="pagefooter">
	  		<jsp:invoke fragment="footer"/>
	  	</div>
	  	
	  	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	</body>
</html>