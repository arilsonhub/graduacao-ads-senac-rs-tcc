app.controller('DashBoardController', function($scope,$http) {
	
  $scope.exibirErros = false;
  $scope.dataFiltro  = DateObjectTOBrDateString(new Date());
	
  $scope.abrirModalGeracaoLote = function() {
	  	$scope.tabelaCampanhas        = [];
		$scope.lot_campanhacodigo     = null;
		$scope.lot_descontopercentual = null;
		$scope.lot_descontohonorarios = null;		  
		$scope.lot_datapagamento      = null;
		$scope.lot_datavencimento     = null;
		$scope.exibirErros = false;
		$scope.buscarCampanhas();
  }
	
  $scope.buscarCampanhas = function() {
	  
	  showHideLoader(true,function(){
		  $http.post('buscar-campanhas', {})
		  .success(function(jsonResponse) {
			  checkSession(jsonResponse,function(){
				  if(isNull(jsonResponse['intranetException'])){
					  $scope.tabelaCampanhas = jsonResponse['list'];
				  }else{
					  showUIAlert(jsonResponse['intranetException']['detailMessage'],function(){
						  $('#modalGeracaoNovoLote').modal('hide');
					  });  
				  }
				  showHideLoader(false);
			  });
		  })
		  .error(function() {
			  showUIAlert("Houve um erro ao tentar buscar as campanhas");
			  showHideLoader(false);
		  }); 
	  });	  
  }
  
  $scope.gerarLote = function() {
	  
	  var dadosRequest = {
			  "dadosGeracaoLoteVO":{
				  "lot_campanhacodigo": $scope.lot_campanhacodigo,
				  "lot_descontopercentual": $scope.lot_descontopercentual,
				  "lot_descontohonorarios": $scope.lot_descontohonorarios,				  
				  "lot_datapagamento": BrDateStringTOISODateString($scope.lot_datapagamento),
				  "lot_datavencimento": BrDateStringTOISODateString($scope.lot_datavencimento)
			  }			  
	  };
	  
	  showHideLoader(true,function(){
		  $http.post('gerar-lote', dadosRequest)
		  .success(function(jsonResponse) {
			  checkSession(jsonResponse,function(){
				  console.log(jsonResponse);
				  if(isNull(jsonResponse['intranetException'])){
					  if(!isNull(jsonResponse['devedorJaFoiProcessadoException'])){						  
						  $scope.exibirDevedoresJaProcessadosDoLote(jsonResponse['devedorJaFoiProcessadoException']['lote']['lot_cod'],1);
						  $('#modalGeracaoNovoLote').modal('hide');
						  $('#modalDevedoresJaProcessados').modal('show');
					  }else{
						  if(isNull(jsonResponse['list'])){
							  showUIAlert(jsonResponse['string'],function(){
								  $scope.listarLotes();
								  $('#modalGeracaoNovoLote').modal('hide');
							  });
						  }else{
							  $scope.listaErrosValidacao = jsonResponse['list'];
							  $scope.exibirErros = true;					  
						  }
					  }
				  }else{				  
					  showUIAlert(jsonResponse['intranetException']['detailMessage'],function(){
						  $('#modalGeracaoNovoLote').modal('hide');
					  });
				  }
			  });
			  showHideLoader(false);
		  })
		  .error(function() {
			  showUIAlert("Houve um erro ao tentar gerar o lote");
			  showHideLoader(false);
		  }); 
	  });  
  }
  
  $scope.listarLotes = function(showLoader,callbackSuccess,callbackNoResultFound) {
	  
	  if(!isNull($scope.dataFiltro) && $scope.dataFiltro.length > 0){
		  dataObject = BrDateStringTODateObject($scope.dataFiltro);
	  }else{
		  dataObject = new Date();
	  }
	  
	  showHideLoader(showLoader,function(){
		  $http.post('listar-lotes',{"lot_datainclusao": DateObjectTOISODateString(dataObject)})
		  .success(function(jsonResponse) {
			  if(!isNull(showLoader)) showHideLoader(false);
			  checkSession(jsonResponse,function(){			  
				  if(isNull(jsonResponse['intranetException'])){				  
					  $scope.listaLotes = jsonResponse['list'];
					  if(jsonResponse['list'].length > 0){
						  if(!isNull(callbackSuccess)){
							  callbackSuccess();
						  }
					  }else{
						  if(!isNull(callbackNoResultFound)){
							  callbackNoResultFound();
						  }
					  }					  
				  }else{
					  showUIAlert(jsonResponse['intranetException']['detailMessage']);
				  }		  
			  });
		  })
		  .error(function() {
			  if(!isNull(showLoader)) showHideLoader(false);
			  showUIAlert("Houve um erro ao tentar listar os lotes");
		  });
	  });
  }
  
  $scope.iniciarThreadListarLotes = function() {
	  setInterval(function(){ 		  
		  angular.element('#divTriggerListarLotes').trigger('click');
	  }, DASHBOARD_THREAD_LISTAR_LOTES_TEMPO);
  }
  
  $scope.exibirErrosDoLote = function(idLote,page){
	  
	  $scope.listaErrosLote = [];
	  $scope.listaErrosLotePaginationList = {'numbers':[],'idLote':null};
	  
	  var dadosRequest = {			  
			  "lote":{"lot_cod":idLote},
			  "page": (isNull(page) ? 1 : page)
	  };	  
	  
	  showHideLoader(true,function(){
		  $http.post('lote-erros', dadosRequest)
		  .success(function(jsonResponse) {
			  showHideLoader(false);
			  checkSession(jsonResponse,function(){			  
				  if(isNull(jsonResponse['intranetException'])){
					  
					  var arrayErros = jsonResponse['paginatorVO']['lista'];				  
					  for(var k in arrayErros)
						  arrayErros[k]['clt_datahora'] = ISODateStringTOBRDateString(arrayErros[k]['clt_datahora']);
					  
					  $scope.listaErrosLote = arrayErros;
					  
					  var pages = {'numbers':[],'idLote':idLote};
					  for(var i = 0; i < jsonResponse['paginatorVO']['pages']; i++){
						  pages['numbers'][i] = {'value':(i + 1),'active':(page == (i + 1) ? "active" : "")};
					  }
					  
					  if(pages['numbers'].length > 1){
						  $scope.listaErrosLotePaginationList = pages;
					  }
					  
				  }else{
					  showUIAlert(jsonResponse['intranetException']['detailMessage']);
				  }		  
			  });
		  })
		  .error(function() {
			  showHideLoader(false);
			  showUIAlert("Houve um erro ao tentar listar os erros do lote");
		  });
	  });	  
  }
  
  $scope.pausarLote = function(idLote) {
	  
	  showHideLoader(true,function(){
		  $http.post('parar-lote',{"lote":{"lot_cod":idLote}})
		  .success(function(jsonResponse) {
			  showHideLoader(false);
			  checkSession(jsonResponse,function(){		  
				  if(isNull(jsonResponse['intranetException'])){
					  $scope.listarLotes();
					  showUIAlert(jsonResponse['string']);		  
				  }else{
					  showUIAlert(jsonResponse['intranetException']['detailMessage']);
				  }		  
			  });
		  })
		  .error(function() {
			  showHideLoader(false);
			  showUIAlert("Houve um erro ao tentar pausar o lote");
		  });
	  });
  }
  
  $scope.retomarLote = function(idLote) {
	  
	  showHideLoader(true,function(){
		  $http.post('retomar-lote',{"lote":{"lot_cod":idLote}})
		  .success(function(jsonResponse) {
			  showHideLoader(false);
			  checkSession(jsonResponse,function(){		  
				  if(isNull(jsonResponse['intranetException'])){
					  $scope.listarLotes();
					  showUIAlert(jsonResponse['string']);		  
				  }else{
					  showUIAlert(jsonResponse['intranetException']['detailMessage']);
				  }		  
			  });
		  })
		  .error(function() {
			  showHideLoader(false);
			  showUIAlert("Houve um erro ao tentar retomar a geração do lote");
		  });
	  });
  }
  
  $scope.abrirModalFiltrarLotesPorData = function() {
	  $('#modalFiltrarLotesPorData').modal('show');
  }
  
  $scope.filtrarLotesPorData = function() {
      try {
		  if(!isNull($scope.dataFiltro) && $scope.dataFiltro.length > 0){		  	  
			  $scope.listarLotes(true,function(){
				  $('#modalFiltrarLotesPorData').modal('hide');
			  },function(){
				  showUIAlert("Nenhum lote foi encontrado para a data selecionada");
			  });
		  }else{
			  showUIAlert("Por favor, informe uma data para filtrar os lotes");
		  }
	  }
	  catch(err) {
		  showUIAlert("Falha ao realizar o filtro");
	  }	  
  }
  
  $scope.marcarDevedor = function(dev_cod, isChecked, lot_cod){
	  
	  var dadosRequest = {
			  "lote":{"lot_cod":lot_cod},
			  "devedor":{"dev_cod":dev_cod},
			  "dlt_valido":(isChecked == true ? 1 : 0)
	  };
	  
	  $http.post('marcar-devedor',dadosRequest)
	  .success(function(jsonResponse) {		  
		  checkSession(jsonResponse,function(){		  
			  if(isNull(jsonResponse['intranetException'])){
				  if(jsonResponse['boolean'] != true) showUIAlert("Houve um erro ao selecionar o devedor");		  
			  }else{
				  showUIAlert(jsonResponse['intranetException']['detailMessage']);
			  }		  
		  });
	  })
	  .error(function() {		  
		  showUIAlert("Houve um erro ao selecionar o devedor");
	  });
  }
  
  $scope.confirmarGeracaoLote = function(lot_cod){
	  
	  var dadosRequest = {
			  "lote": {"lot_cod":lot_cod},
			  "acao": $scope.comboAcaoDevedoresProcessados
	  };
	  
	  showHideLoader(true,function(){
		  $http.post('confirmar-geracao-lote',dadosRequest)
		  .success(function(jsonResponse) {  
			  showHideLoader(false);
			  checkSession(jsonResponse,function(){
				  if(!isNull(jsonResponse['loteNaoPossuiDevedoresParaProcessarException'])){
					  showUIAlert(jsonResponse['loteNaoPossuiDevedoresParaProcessarException']['detailMessage'],function(){
						  $('#modalDevedoresJaProcessados').modal('hide');
					  });					  
				  }else if(!isNull(jsonResponse['intranetException'])){					  
					  showUIAlert(jsonResponse['intranetException']['detailMessage']);
				  }else{					  
					  showUIAlert(jsonResponse['string'],function(){
						  $scope.listarLotes();
						  $('#modalDevedoresJaProcessados').modal('hide');
					  });
				  }		  
			  });
		  })
		  .error(function() {
			  showHideLoader(false);
			  showUIAlert("Houve um erro ao gerar o lote");
		  });
	  });
  }
  
  $scope.cancelarGeracaoLote = function(lot_cod){
	  
	  showConfirmUIMessage("Você realmente quer cancelar o lote?",function(){
		  
		  var dadosRequest = {
				  "lote": {"lot_cod":lot_cod}			  
		  };  
		  
		  showHideLoader(true,function(){
			  $http.post('cancelar-lote',dadosRequest)
			  .success(function(jsonResponse) {
				  showHideLoader(false);
				  checkSession(jsonResponse,function(){		  
					  if(isNull(jsonResponse['intranetException'])){
						  showUIAlert(jsonResponse['string'],function(){							  
							  $('#modalDevedoresJaProcessados').modal('hide');
						  });		  
					  }else{
						  showUIAlert(jsonResponse['intranetException']['detailMessage'],function(){
							  $('#modalDevedoresJaProcessados').modal('hide');
						  });
					  }		  
				  });
			  })
			  .error(function() {
				  showHideLoader(false);
				  showUIAlert("Houve um erro ao tentar cancelar o lote");
			  });
		  }); 
	  });	  
  }
  
  $scope.exibirDevedoresJaProcessadosDoLote = function(lot_cod,page){
	  
	  $scope.listaDevedoresProcessados = [];
	  $scope.listaDevedoresProcessadosPaginationList = {'numbers':[],'idLote':null};
	  
	  var dadosRequest = {			  
			  "lote":{"lot_cod":lot_cod},
			  "page": (isNull(page) ? 1 : page)
	  };	  
	  
	  showHideLoader(true,function(){
		  $http.post('exibir-devedores-ja-processados', dadosRequest)
		  .success(function(jsonResponse) {
			  showHideLoader(false);
			  checkSession(jsonResponse,function(){			  
				  if(isNull(jsonResponse['intranetException'])){					  
					  $scope.listaDevedoresProcessados = jsonResponse['paginatorVO']['lista'];
					  var pages = {'numbers':[],'idLote':lot_cod};
					  for(var i = 0; i < jsonResponse['paginatorVO']['pages']; i++){
						  pages['numbers'][i] = {'value':(i + 1),'active':(page == (i + 1) ? "active" : "")};
					  }
					  if(pages['numbers'].length > 1){
						  $scope.listaDevedoresProcessadosPaginationList = pages;
					  }
				  }else{
					  showUIAlert(jsonResponse['intranetException']['detailMessage']);
				  }		  
			  });
		  })
		  .error(function() {
			  showHideLoader(false);
			  showUIAlert("Houve um erro ao tentar listar os devedores");
		  });
	  });
  }
});