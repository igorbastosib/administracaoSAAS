<jsp:include page="/saas-admin/assets/includes/header.html"></jsp:include>
<jsp:include page="/saas-admin/assets/includes/navbar.html"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="admin.ReportPagesMostAccess"%>


<!-- Relatorios Start -->
<section id="relatorios" class="pfblock-admin pfblock-gray">

	<div class="container-fluid">
		<div class="col-md-2 sidebar">
			<jsp:include page="/saas-admin/assets/includes/sidebar.html"></jsp:include>
		</div>
		<div class="col-md-10 content">
			<div class="panel-heading">
				<h3 class="panel-title">
					P�ginas mais acessadas
					<td class="table-button button-delet"><a
						href="javascript:printTableFromHTMLtoPDF('tableMoreAccess', 'P�ginas mais acessadas')"
						title="Imprimir"><span style="margin-left: 2em"
							class="glyphicon glyphicon-print"></span></a></td>
				</h3>
			</div>
			<div id="tableMoreAccess" class="row">
				<div class="container-fluid table-relatorios-mais-acesso">
					<table cellpadding="2" cellspacing="2" border="1">
						<tr>
							<th class="title-column">P�gina (URL)</th>
							<th class="title-column">Total Acessos</th>
							<th class="title-column">Monitorar</th>
						</tr>
						<c:forEach var="p" items="${accessReport}">
							<tr>
								<td class="tupla-element">${p.url}</td>
								<td class="tupla-element">${p.qtdAcessos}</td>
								<td class="table-button button-delet" align="center"><a
									href="./AdminServlet.saas?comando=pararMonitoraURL&url=${p.url}"
									onclick="return confirm('Voc� tem certeza?')"
									title="Deixar de monitorar"><span
										class="glyphicon glyphicon-remove"></span></a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<div class="panel-heading">

				<h3 class="panel-title">
					�ltimas p�ginas acessadas
					<td class="table-button button-delet"><a
						href="javascript:printTableFromHTMLtoPDF('tableRealAccess', '�ltimas p�ginas acessadas')"
						title="Imprimir"><span style="margin-left: 2em"
							class="glyphicon glyphicon-print"></span></a></td>
				</h3>

			</div>
			<div id="tableRealAccess" class="row">
				<div class="container-fluid table-relatorios-acesso-real">
					<table cellpadding="2" cellspacing="2" border="1">
						<tr>
							<th class="title-column">P�gina (URL)</th>
							<th class="title-column">IP</th>
							<th class="title-column">Data/Hora</th>
						</tr>
						<c:forEach var="p" items="${realAccessReport}">
							<tr>
								<td class="tupla-element">${p.url}</td>
								<td class="tupla-element">${p.ip}</td>
								<td class="tupla-element">${p.dateTime}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>

			<div class="panel-heading">

				<h3 class="panel-title">
					P�ginas n�o monitoradas
					<td class="table-button button-delet"><a
						href="javascript:printTableFromHTMLtoPDF('tableDontMonitoredPages', 'P�ginas n�o monitoradas')"
						title="Imprimir"><span style="margin-left: 2em"
							class="glyphicon glyphicon-print"></span></a></td>
				</h3>

			</div>
			<div id="tableDontMonitoredPages" class="row">
				<div class="container-fluid table-relatorios-mais-acesso">
					<table cellpadding="2" cellspacing="2" border="1">
						<tr>
							<th class="title-column">P�gina (URL)</th>
							<th class="title-column">Monitorar</th>
						</tr>
						<c:forEach var="p" items="${dontMonitoredPages}">
							<tr>
								<td class="tupla-element">${p.url}</td>
								<td class="table-button button-delet" align="center"><a
									href="./AdminServlet.saas?comando=iniciarMonitoraURL&url=${p.url}"
									onclick="return confirm('Voc� tem certeza?')" title="Monitorar"><span
										class="glyphicon glyphicon-ok"></span></a></td>
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