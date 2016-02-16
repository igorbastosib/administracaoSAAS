<jsp:include page="/saas-admin/assets/includes/header.html"></jsp:include>
<jsp:include page="/saas-admin/assets/includes/navbar.html"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="admin.RelatorioPaginasMaisAcessadas"%>


<!-- Relatorios Start -->
<section id="relatorios" class="pfblock-admin pfblock-gray">

	<div class="container-fluid">
		<div class="col-md-2 sidebar">
			<jsp:include page="/saas-admin/assets/includes/sidebar.html"></jsp:include>
		</div>
		<div class="col-md-10 content">
			<div class="panel-heading">
				<h3 class="panel-title">Relatório de Acesso</h3>
			</div>
			<div class="row">
				<div class="container-fluid table-relatorios-mais-acesso">
					<table cellpadding="2" cellspacing="2" border="1">
						<tr>
							<th class="title-column">Página (URL)</th> 
							<th class="title-column">Total Acessos</th>
						</tr>
						<c:forEach var="p" items="${relatorioAcesso}">
							<tr>
								 <td class="tupla-element">${p.url}</td>  
								<td class="tupla-element">${p.qtdAcessos}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<div class="panel-heading">
				<h3 class="panel-title">Últimas páginas acessadas</h3>
			</div>
			<div class="row">
				<div class="container-fluid table-relatorios-acesso-real">
					<table cellpadding="2" cellspacing="2" border="1">
						<tr>
							<th class="title-column">Página (URL)</th> 
							<th class="title-column">IP</th>
							<th class="title-column">Data/Hora</th>
						</tr>
						<c:forEach var="p" items="${relatorioAcessoReal}">
							<tr>
								 <td class="tupla-element">${p.url}</td>  
								<td class="tupla-element">${p.ip}</td>
								<td class="tupla-element">${p.dateTime}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</section>
<jsp:include page="../assets/includes/footer.html"></jsp:include>

<!-- Scripts -->
<script src="./assets/js/jquery-1.11.1.min.js"></script>
<script src="./assets/bootstrap/js/bootstrap.min.js"></script>
<script src="./assets/js/imagesloaded.pkgd.js"></script>
<script src="./assets/js/jquery.sticky.js"></script>
<script src="./assets/js/wow.min.js"></script>
<script src="./assets/js/jquery.easypiechart.js"></script>
<script src="./assets/js/waypoints.min.js"></script>
<script src="./assets/js/jquery.cbpQTRotator.js"></script>
<script src="./assets/js/custom.js"></script>
<script src="./assets/js/validate.form.js"></script>

</body>
</html>