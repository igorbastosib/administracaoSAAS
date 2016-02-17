<jsp:include page="/saas-admin/assets/includes/header.html"></jsp:include>
<jsp:include page="/saas-admin/assets/includes/navbar.html"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="dao.*"%>
<%@ page import="admin.*"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.persistence.*"%>

<!-- Users Start -->
<section id="users" class="pfblock-admin pfblock-gray">

	<div class="container-fluid">
		<div class="col-md-2 sidebar">
			<jsp:include page="/saas-admin/assets/includes/sidebar.html"></jsp:include>
		</div>
		<div class="col-md-10 content">
			<div class="panel-heading">
				<h3 class="panel-title">Usuários cadastrados
					<td class="table-button button-delet"><a
						href="javascript:printTableFromHTMLtoPDF('tableUsers', 'Usuários cadastrados')" title="Imprimir"><span
							style="margin-left:2em"class="glyphicon glyphicon-print"></span></a></td>
				</h3>
			</div>
			<div id="tableUsers" class="row">
				<div class="container-fluid table-users">
					<table cellpadding="2" cellspacing="2" border="1">
						<tr>
							<th class="title-column">Id</th>
							<th class="title-column">Login</th>
							<th class="title-column">Senha</th>
						</tr>
						<c:forEach var="p" items="${listaUsuarios}">
							<tr>
								<td class="tupla-element">${p.id}</td>
								<td class="tupla-element">${p.login}</td>
								<td class="tupla-element">${p.senha}</td>
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
<script src="./assets/js/jspdf.debug.js"></script>
<script src="./assets/js/print.table.to.pdf.js"></script>

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