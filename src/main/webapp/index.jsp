<!-- Pagina de Login do site de Administracao -->

<%@include file="./assets/includes/header.html"%>

<!-- Login Start -->
<section id="admin" class="pfblock">
	<div class="container">
		<div class="row">
			<div class="login-img">
				<p>
					<img src="./assets/images/logo-saas.gif" />
				</p>
			</div>
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Administração - SAAS</h3>
					</div>
					<div class="panel-body">
						<form accept-charset="UTF-8" role="form" action="./login-admin.saas">
							<fieldset>
								<%	String msgErro = request.getAttribute("MsgErro") == null ? "" : (String) request.getAttribute("MsgErro");
									if(!msgErro.isEmpty()){%>
										<div class="alert alert-danger">
											<span class="glyphicon glyphicon-alert"></span>
											<strong><%=msgErro %></strong>
										</div>
									<%} %>
								<div class="form-group">
									<input class="form-control" placeholder="Usuário" name="login"
										type="text">
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Senha" name="senha"
										type="password" value="">
								</div>
								<input name="comando" class="btn btn-lg btn-success btn-block" type="submit"
									value="entrar">
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<%@include file="./assets/includes/footer.html"%>

<!-- Scripts -->
<script src="./assets/js/jquery-1.11.1.min.js"></script>
<script src="./assets/bootstrap/js/bootstrap.min.js"></script>
<script src="./assets/js/imagesloaded.pkgd.js"></script>
<script src="./assets/js/jquery.sticky.js"></script>
<script src="./assets/js/smoothscroll.js"></script>
<script src="./assets/js/wow.min.js"></script>
<script src="./assets/js/jquery.easypiechart.js"></script>
<script src="./assets/js/waypoints.min.js"></script>
<script src="./assets/js/jquery.cbpQTRotator.js"></script>
<script src="./assets/js/custom.js"></script>
</body>
</html>