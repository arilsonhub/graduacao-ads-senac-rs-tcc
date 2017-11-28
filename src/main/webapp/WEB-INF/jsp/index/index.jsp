<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../shared/header.jsp" />
<div class="container">
	<div class="row text-center ">
		<div class="col-md-12">
			<br />
			<br />
			<h2>Robô KeyStone</h2>

			<h5>( Faça login para acessar o sistema )</h5>
			<br />
		</div>
	</div>
	<form id="frmLogin" action="autenticar" method="post">
		<div class="row">
			<div
				class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong> Entre com suas credenciais para acessar </strong>
					</div>
					<div class="panel-body">
						<br />
						<div class="form-group input-group">
							<span class="input-group-addon"><i class="fa fa-tag"></i></span>
							<input name="usuario.usu_usuario" type="text"
								class="form-control" value="${usuario.usu_usuario}"
								placeholder="Usuário" />
						</div>
						<c:if test="${errors.from('usuario.usu_usuario') != null}">
							<div class="errorText">${errors.from('usuario.usu_usuario')}</div>
							<br />
						</c:if>
						<div class="form-group input-group">
							<span class="input-group-addon"><i class="fa fa-lock"></i></span>
							<input name="usuario.usu_senha" type="password"
								class="form-control" value="${usuario.usu_senha}"
								placeholder="Senha" />
						</div>
						<c:if test="${errors.from('usuario.usu_senha') != null}">
							<div class="errorText">${errors.from('usuario.usu_senha')}</div>
							<br />
						</c:if>
						<c:if test="${autenticacao_resultado != null}">
							<div class="errorText">${autenticacao_resultado}</div>
							<br />
						</c:if>
						<a href="javascript:enviarForm();" class="btn btn-primary">Acessar</a>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
<script>
        function enviarForm(){
			document.getElementById("frmLogin").submit();        	
        }
    </script>
<c:import url="../shared/JSFiles.jsp" />    
<c:import url="../shared/footer.jsp" />