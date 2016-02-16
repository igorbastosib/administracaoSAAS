<jsp:include page="/saas-admin/assets/includes/header.html"></jsp:include>
<jsp:include page="/saas-admin/assets/includes/navbar.html"></jsp:include>

<!-- Admin Panel Start -->
<section id="admin-panel">

	<div class="container-fluid main-container">
		<div class="col-md-2 sidebar">
			<jsp:include page="./assets/includes/sidebar.html"></jsp:include>
		</div>
		
		<div class="col-md-10 content">
			<div class="panel panel-default buffer-line">
				<div class="panel-heading">Sobre o Sistema</div>
				<div class="panel-body">SAAS - Sistema de Análise de Acesso a Sites
					é uma ferramenta de análise que visa prover um meio do usuário
					analisar a frequencia do acesso e as páginas mais acessadas.
					<br/><span style = "margin-left:2em">Clique no menu Usuários para visualizar todos usuários cadastrados;</span>
					<br/><span style = "margin-left:2em">Clique em Relatórios para visualizar o Relaório de acesso às páginas.</span>
				</div>
			</div>
		</div>
	</div>
</section>
<jsp:include page="../assets/includes/footer.html"></jsp:include>

<!-- Scripts -->
<script src="./assets/js/jquery-1.11.1.min.js"></script>
<script	src="./assets/bootstrap/js/bootstrap.min.js"></script>
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