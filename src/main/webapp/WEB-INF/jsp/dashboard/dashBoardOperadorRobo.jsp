<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<c:import url="../shared/header.jsp" />
<div id="wrapper" ng-controller="DashBoardController">
		<c:import url="../shared/top.jsp" />
		<c:import url="../menu/menuPrincipal.jsp" />	
		<div id="page-wrapper" >
				<div id="page-inner">
					<div class="row">
							<div class="col-md-12">
							 <h2>Dashboard</h2>   
								<h5>Utilize esta área para gerenciar o robô</h5>
							</div>
						</div>              
						 <!-- /. ROW  -->
						  <hr />					
						 <!-- /. ROW  -->
						<div class="col-md-9 col-sm-12 col-xs-12">
							<div class="chat-panel panel panel-default chat-boder chat-panel-head" >
								<div class="panel-heading">
									<i class="fa fa-comments fa-fw"></i>
									Controle dos lotes de geração de boletos - Filtrando lotes pela data:&nbsp;{{dataFiltro}}
									<div class="btn-group pull-right">
										<button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
											<i class="fa fa-chevron-down"></i>
										</button>
										<ul class="dropdown-menu slidedown">											
											<li>
												<a href="javascript:void(0);" ng-click="abrirModalFiltrarLotesPorData()">
													<i class="fa fa-refresh fa-fw"></i>Filtrar lotes por data
												</a>
											</li>
											<li class="divider"></li>
											<li>
												<a href="javascript:void(0);" ng-click="abrirModalGeracaoLote()" data-toggle="modal" data-target="#modalGeracaoNovoLote">
													<i class="fa fa-sign-out fa-fw"></i>Gerar novo lote de envio
												</a>												
											</li>
											<li class="divider"></li>
											<li>
												<a href="javascript:void(0);" data-toggle="modal" data-target="#modalLegendaStatusLote">
													<i class="fa fa-sign-out fa-fw"></i>Exibir Legenda de status dos lotes
												</a>												
											</li>											
										</ul>
									</div>
								</div>

								<div class="panel-body">
									<ul class="chat-box">
										<li class="left clearfix" ng-repeat="lot in listaLotes">
											<span class="chat-img pull-left">
												<img src="assets/img/{{lot.statusId}}.png" alt="User" class="img-circle" />
											</span>
											<div class="chat-body">                                        
												<strong class="chat-text">Lote {{lot.idLote}}</strong><br />
													<p class="chat-info">
													Status:&nbsp;{{lot.status}}<br />
													Erros:&nbsp;{{lot.erros}}<br />
													Nº Contratos:&nbsp;{{lot.numeroContratos}}<br />
													Boletos Gerados:&nbsp;{{lot.numeroBoletosGerados}}
												</p>												
											</div>
											<div class="chat-body-float-left">
												<span class="chat-img pull-left">Controle do lote:</span>&nbsp;&nbsp;
												<div class="btn-group pull-right">
													<button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
														<i class="fa fa-chevron-down"></i>
													</button>
													<ul class="dropdown-menu slidedown">
														<li>
															<a href="javascript:void(0);" ng-click="pausarLote(lot.idLote)">
																<i class="fa fa-times fa-fw"></i>Pausar lote
															</a>
														</li>
														<li>
															<a href="javascript:void(0);" ng-click="retomarLote(lot.idLote)">
																<i class="fa fa-refresh fa-fw"></i>Retomar lote
															</a>
														</li>														
														<li>
															<a href="javascript:void(0);" ng-click="exibirErrosDoLote(lot.idLote,1)" data-toggle="modal" data-target="#modalErroLote">
																<i class="fa fa-sign-out fa-fw"></i>Visualizar Erros no lote
															</a>												
														</li>
													</ul>
												</div>
											</div>					
										</li>																				
									</ul>
								</div>								
							</div>                    
						</div>     
					 <!-- /. ROW  -->
				</div>
				 <!-- /. PAGE INNER  -->
		</div>
			 <!-- /. PAGE WRAPPER  -->

						<!--  Modals-->
                        <div class="modal fade" id="modalGeracaoNovoLote" tabindex="-1" role="dialog" aria-labelledby="modalGeracaoNovoLoteLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="modalGeracaoNovoLoteLabel">Geração de novo lote</h4>
                                        </div>
                                        <div class="modal-body">
											<div class="row">
												<div class="col-md-11">												  
												  <div class="panel panel-default">
														<div class="panel-heading">
															Clique nas abas abaixo para definir as configurações do novo lote
														</div>
														<div class="panel-body">
															<ul class="nav nav-tabs">
																<li class="active"><a href="#dadosParaGeracaoBoletos" data-toggle="tab">Dados para geração dos boletos</a></li>
																<li class=""><a href="#selecaoCampanha" data-toggle="tab">Seleção de campanha</a></li>
															</ul>
															<div class="tab-content">
																<div class="tab-pane fade active in" id="dadosParaGeracaoBoletos">
                                                                    <br />																
																	<div class="col-md-5">
																			<form role="form">
																				 <div style="width:60%">
																					 <div class="form-group">
																						<label>Desconto Percentual</label>
																						<input id="lot_descontopercentual" maxlength="6" class="form-control" ng-model="lot_descontopercentual" />
																					 </div>
																				 </div>
																				 <div style="width:60%">
																					 <div class="form-group">
																						<label>Desconto Honorários</label>
																						<input id="lot_descontohonorarios" maxlength="6" class="form-control" ng-model="lot_descontohonorarios" />
																					 </div>
																				 </div>																				 
																			</form>																	
																	</div>
																	<div class="col-md-6">
																			<form role="form">
																				 <div style="width:100%">
																					 <div class="form-group">
																						 <div class='input-group date'>
																							<label>Data de vencimento</label>
																							<input ng-model="lot_datavencimento" type='text' class="form-control" id='datetimepicker1' datepicker />									
																						</div>
																					</div>
																				 </div>
																				 <div style="width:100%">
																					 <div class="form-group">
																						<div class='input-group date'>
																							<label>Data de pagamento</label>
																							<input ng-model="lot_datapagamento" type='text' class="form-control" id='datetimepicker2' datepicker />									
																						</div>
																					 </div>
																				 </div>
																			</form>
																	</div>
																</div>
																<div class="tab-pane fade" id="selecaoCampanha">
																	<!--   Kitchen Sink -->
																		<div class="panel panel-default">
																			<div class="panel-heading">
																				Selecione a campanha a ser importada
																			</div>
																			<div class="panel-body">
																				<div class="table-responsive">
																					<table id="tabelaServicoBuscaCampanhas" class="table table-striped table-bordered table-hover">
																						<thead>
																							<tr>									
																								<th>Produto</th>
																								<th>Nome da campanha</th>
																								<th>Selecionar</th>
																							</tr>
																						</thead>
																						<tbody>
																							<tr ng-repeat="cam in tabelaCampanhas">
																								<td>{{cam.fil_pro_cod}}</td>
																								<td>{{cam.fil_nome}}</td>
																								<td align="center"><input ng-model="$parent.lot_campanhacodigo" ng-value="{{cam.fil_cod}}" type="radio" name="fila" /></td>
																							</tr>																															
																						</tbody>
																					</table>
																				</div>
																			</div>
																		</div>
																	<!-- End  Kitchen Sink -->
																</div>                               
															</div>
														</div>
													</div>
												</div>
											</div>
										    <div id="modalGeracaoLoteErrorBox" class="app_error" ng-show="exibirErros">
											   <i class="fa fa-times-circle"></i>
											   Existem os seguintes erros no formulário:
											   <ul>
											   		<li ng-repeat="err in listaErrosValidacao">{{err.message}}</li>											   		
											   </ul>											   
											</div>											
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                            <button type="button" ng-click="gerarLote()" class="btn btn-primary">Gerar</button>
                                        </div>
                                    </div>                                    
                                </div>                                
                        </div>
                        <div class="modal fade" id="modalFiltrarLotesPorData" tabindex="-1" role="dialog" aria-labelledby="modalFiltrarLotesPorDataLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="modalFiltrarLotesPorDataLabel">Filtrar lotes por data</h4>
                                        </div>
                                        <div class="modal-body">
											<div class="row">
												<div class="col-md-12">
													<div class='input-group date'>
														<label>Informe a data</label>
														<input ng-model="dataFiltro" type='text' class="form-control" id='datetimepicker3' datepicker />									
													</div>																										
												</div>
											</div>
										</div>
                                        <div class="modal-footer">
                                        	<button type="button" ng-click="filtrarLotesPorData()" class="btn btn-primary">Filtrar</button>
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                        </div>
                                    </div>
                                </div>                                
                        </div>
                        <div class="modal fade" id="modalLegendaStatusLote" tabindex="-1" role="dialog" aria-labelledby="modalLegendaStatusLoteLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="modalLegendaStatusLoteLabel">Legenda para status dos lotes</h4>
                                        </div>
                                        <div class="modal-body">
											<div class="row">
												<div class="col-md-12">
													<!-- Advanced Tables -->
													<div class="panel panel-default">														
														<div class="panel-body">
															<div class="table-responsive">
																<table class="table table-striped table-bordered table-hover" id="tabelaErrosLote">
																	<thead>
																		<tr>
																			<th>Cor</th>
																			<th>Status</th>																																						
																		</tr>
																	</thead>
																	<tbody>
																		<tr>																		
																			<td align="center"><img height="20" width="20" src="assets/img/1.png" alt="User" class="img-circle" /></td>
																			<td class="center">Em andamento</td>
																		</tr>
																		<tr>																		
																			<td align="center"><img height="20" width="20" src="assets/img/2.png" alt="User" class="img-circle" /></td>
																			<td class="center">Falha no lote</td>
																		</tr>
																		<tr>																		
																			<td align="center"><img height="20" width="20" src="assets/img/3.png" alt="User" class="img-circle" /></td>
																			<td class="center">Lote finalizado com sucesso</td>
																		</tr>
																		<tr>																		
																			<td align="center"><img height="20" width="20" src="assets/img/4.png" alt="User" class="img-circle" /></td>
																			<td class="center">Pausado</td>
																		</tr>
																	</tbody>
																</table>
															</div>
															 <ul class="pagination">
															  	<li class="{{page.active}}" ng-repeat="page in listaErrosLotePaginationList.numbers"><a href="javascript:void(0);" ng-click="exibirErrosDoLote(listaErrosLotePaginationList.idLote,page.value)">{{page.value}}</a></li>															  
															</ul>															
														</div>
													</div>
													<!--End Advanced Tables -->																										
												</div>
											</div>
										</div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                                        </div>
                                    </div>
                                </div>                                
                        </div>
						<div class="modal fade" id="modalErroLote" tabindex="-1" role="dialog" aria-labelledby="modalGeracaoNovoLoteErroLoteLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="modalGeracaoNovoLoteErroLoteLabel">Visualizar erros no lote</h4>
                                        </div>
                                        <div class="modal-body">
											<div class="row">
												<div class="col-md-12">
													<!-- Advanced Tables -->
													<div class="panel panel-default">
														<div class="panel-heading">
															 Lista de erros que ocorreram no lote
														</div>
														<div class="panel-body">
															<div class="table-responsive">
																<table class="table table-striped table-bordered table-hover" id="tabelaErrosLote">
																	<thead>
																		<tr>
																			<th>Descrição</th>
																			<th>Data/Hora</th>
																			<th>Processo</th>																			
																		</tr>
																	</thead>
																	<tbody>
																		<tr ng-repeat="erro in listaErrosLote">
																			<td class="center">{{erro.clt_descricao}}</td>
																			<td class="center">{{erro.clt_datahora}}</td>
																			<td class="center">{{erro.clt_processo}}</td>																			
																		</tr>
																	</tbody>
																</table>
															</div>
															 <ul class="pagination">
															  	<li class="{{page.active}}" ng-repeat="page in listaErrosLotePaginationList.numbers"><a href="javascript:void(0);" ng-click="exibirErrosDoLote(listaErrosLotePaginationList.idLote,page.value)">{{page.value}}</a></li>															  
															</ul>															
														</div>
													</div>
													<!--End Advanced Tables -->
												</div>
											</div>
										</div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                                        </div>
                                    </div>
                                </div>                                
                        </div>
                        <div class="modal fade" id="modalDevedoresJaProcessados" tabindex="-1" role="dialog" aria-labelledby="modalDevedoresJaProcessadosLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">                                            
                                            <h4 class="modal-title" id="modalDevedoresJaProcessadosLabel">Filtrar lotes por data</h4>
                                        </div>
                                        <div class="modal-body">
											<div class="row">
												<div class="col-md-12">
													<div class='input-group date'>
														<label>Ação a ser realizada:</label>
														<select ng-model="comboAcaoDevedoresProcessados">
															<option value="">Selecione</option>
															<option value="1">Remover devedores selecionados do lote</option>
															<option value="2">Remover devedores não selecionados do lote</option>
															<option value="3">Manter todos os devedores no lote</option>
														</select>			
													</div>																										
												</div>
											</div>
											<div class="row">
												<div class="col-md-12">
													<!-- Advanced Tables -->
													<div class="panel panel-default">
														<div class="panel-heading">
															 Lista de devedores já processados neste mês
														</div>
														<div class="panel-body">
															<div class="table-responsive">
																<table class="table table-striped table-bordered table-hover" id="tabelaErrosLote">
																	<thead>
																		<tr>
																			<th>&nbsp;</th>
																			<th>CPF</th>																																						
																		</tr>
																	</thead>
																	<tbody>
																		<tr ng-repeat="ldp in listaDevedoresProcessados">
																			<td class="center"><input type="checkbox" ng-checked="ldp.dlt_valido" ng-model="checkDevedorMarcado" ng-click="marcarDevedor(ldp.dev_cod,checkDevedorMarcado,listaDevedoresProcessados[0].lot_cod)" /></td>
																			<td class="center">{{ldp.dev_cpf_cnpj}}</td>																																						
																		</tr>
																	</tbody>
																</table>
															</div>
															<ul class="pagination">
															  	<li class="{{page.active}}" ng-repeat="page in listaDevedoresProcessadosPaginationList.numbers"><a href="javascript:void(0);" ng-click="exibirDevedoresJaProcessadosDoLote(listaDevedoresProcessados[0].lot_cod,page.value)">{{page.value}}</a></li>															  
															</ul>															 															
														</div>
													</div>
													<!--End Advanced Tables -->
												</div>
											</div>
										</div>
                                        <div class="modal-footer">
                                        	<button type="button" ng-click="confirmarGeracaoLote(listaDevedoresProcessados[0].lot_cod)" class="btn btn-primary">Gerar lote</button>
                                            <button type="button" class="btn btn-default" ng-click="cancelarGeracaoLote(listaDevedoresProcessados[0].lot_cod)">Cancelar lote</button>
                                        </div>
                                    </div>
                                </div>                                
                        </div>
						<!-- End Modals-->
     <!-- /. WRAPPER  -->
     <div style="display:none;" id="divTriggerListarLotes" ng-click="listarLotes()"></div>     
     <div style="display:none;" id="divTriggerThreadListarLotes" ng-click="iniciarThreadListarLotes()"></div>
</div>     

<c:import url="../shared/JSFiles.jsp" />

	<script>
		$(function () {
			angular.element('#divTriggerListarLotes').trigger('click');
			angular.element('#divTriggerThreadListarLotes').trigger('click');
			
			$('#lot_descontopercentual').maskMoney();
			$('#lot_descontohonorarios').maskMoney();
			
			$('#datetimepicker1').on("input", function(e) {
			    $(this).val("");
			});
			
			$('#datetimepicker2').on("input", function(e) {
			    $(this).val("");
			});
			
			$('#datetimepicker3').on("input", function(e) {
			    $(this).val("");
			});			
        });		
	</script>

<!-- CUSTOM SCRIPTS -->
<script src="assets/js/controllers/DashBoardController.js"></script>
	
<c:import url="../shared/footer.jsp" />