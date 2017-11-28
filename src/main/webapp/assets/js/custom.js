(function ($) {
    "use strict";
    var mainApp = {

        main_fun: function () {
            /*====================================
            METIS MENU 
            ======================================*/
            $('#main-menu').metisMenu();

            /*====================================
              LOAD APPROPRIATE MENU BAR
           ======================================*/
            $(window).bind("load resize", function () {
                if ($(this).width() < 768) {
                    $('div.sidebar-collapse').addClass('collapse')
                } else {
                    $('div.sidebar-collapse').removeClass('collapse')
                }
            });     
        },

        initialization: function () {
            mainApp.main_fun();
        }
    }    

    $(document).ready(function () {
        mainApp.main_fun();
    });

}(jQuery));

var BASE_URL = "/roboKeyStone/";

var app = angular.module('roboKeyStone', []);

var dateISOStringPattern = "YYYY-MM-DDTHH:mm:ss-0300";

var DASHBOARD_THREAD_LISTAR_LOTES_TEMPO = 60000;

app.config(['$httpProvider', function($httpProvider) {
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
}]);

app.directive('datepicker', function() {
    return {
        restrict: 'A',
        require : 'ngModel',
        link : function (scope, element, attrs, ngModelCtrl) {
            $(function(){
                element.datepicker({
                    dateFormat:'dd/mm/yy',
                    onSelect:function (date) {
                        ngModelCtrl.$setViewValue(date);
                        scope.$apply();
                    },
                	beforeShow: function()
			        {
			           setTimeout(function() { $(".ui-datepicker").css("z-index", 1051); },10);
			        },
			        dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado','Domingo'],
			        dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
			        dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
			        monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
			        monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez']
                });
            });
        }
    }
});

function checkSession(jsonResponse,callbackSessionOK,callbackSessionFailure){	
	if(jsonResponse['sessionAuthErrorHandlerVO'] != undefined && jsonResponse['sessionAuthErrorHandlerVO'] != null){
		if(callbackSessionFailure != undefined && callbackSessionFailure != null){
			callbackSessionFailure();
		}else{
			showUIAlert(jsonResponse['sessionAuthErrorHandlerVO']['errorMessage'],function(){
				document.location.replace(BASE_URL);
			});			
			return;
		}
	}else{
		callbackSessionOK();
	}
}

function isNull(data){
	if(data == undefined || data == null) return true;
	return false;
}

function BrDateStringTOISODateString(data){
	if(isNull(data)) return null;
	var dataSplit = data.split("/");
	var data = dataSplit[2] + "-" + dataSplit[1] + "-" + dataSplit[0];
	return moment(data).format(dateISOStringPattern);	
}

function BrDateStringTODateObject(data){
	if(isNull(data)) return null;	
	var arrayDataParaFiltrar = (data).split("/");
	return new Date(arrayDataParaFiltrar[2], (arrayDataParaFiltrar[1]-1), arrayDataParaFiltrar[0], 0, 0, 0, 0);
}

function DateObjectTOISODateString(data){
	if(isNull(data)) return null;
	var mesContidoNaData = (data.getMonth() + 1);
	mesContidoNaData = (mesContidoNaData < 10 ? "0"+mesContidoNaData : mesContidoNaData);
	var data = data.getFullYear() + "-" + mesContidoNaData + "-" + (data.getDate() < 10 ? "0"+data.getDate() : data.getDate());
	return moment(data).format(dateISOStringPattern);
}

function DateObjectTOBrDateString(data){
	if(isNull(data)) return null;
	var mesContidoNaData = (data.getMonth() + 1);
	mesContidoNaData = (mesContidoNaData < 10 ? "0"+mesContidoNaData : mesContidoNaData);
	var data = (data.getDate() < 10 ? "0"+data.getDate() : data.getDate()) + "/" + mesContidoNaData + "/" + data.getFullYear();
	return data;
}

function ISODateStringTOBRDateString(data){	
	if(isNull(data)) return null;	
	var data = data.substring(0,data.indexOf("T")).split("-");
	return (data[2] + "/" + data[1] + "/" + data[0]);
}

function showHideLoader(showOrHide,callback){
	if(!isNull(showOrHide)){
		if(showOrHide){
			$('#gifLoader').show();
			var alturaTela = $(document).height();
	        var larguraTela = $(window).width();
	        $('#fundoEscuro').css({'width':larguraTela,'height':alturaTela});        
	        
	        $("#fundoEscuro").fadeTo("fast",0.8,function() {
	        	if(!isNull(callback)){
	        		callback();
	        	}
	        });
	        
		}else{
			if(!isNull(callback)){
				callback();
			}
			$('#gifLoader').hide();
			$("#fundoEscuro").hide();		
		}
	}else{
		if(!isNull(callback)){
    		callback();
    	}
	}
}

function showUIAlert(content,callback,params){
	 $('#fundoEscuro').show();
	 $("#messageDialog").dialog({		
		resizable : false,		
		modal : false,
		buttons : {
			"ok" : function() {
				if(!isNull(callback)){
					if(!isNull(params)){
						callback(params);
					}else{
						callback();
					}					
				}
				$('#fundoEscuro').hide();
				$('#messageDialog').dialog("close");				
			}
		},
	 	close: function(ev, ui){
	 		$('#fundoEscuro').hide();
	 	},
	});
	$("#messageDialogText").html(content);
}

function showConfirmUIMessage(content,confirmCallback,params){
	$('#fundoEscuro').show();
	 $("#messageConfirmDialog").dialog({		
		resizable : false,		
		modal : false,
		buttons : {
			"ok" : function() {				
				if(!isNull(params)){
					confirmCallback(params);
				}else{
					confirmCallback();
				}
				$('#fundoEscuro').hide();
				$('#messageConfirmDialog').dialog("close");				
			},
			"Cancelar" : function() {
				$('#fundoEscuro').hide();
				$('#messageConfirmDialog').dialog("close");				
			}
		},
	 	close: function(ev, ui){
	 		$('#fundoEscuro').hide();
	 		$('#messageConfirmDialog').dialog("close");
	 	},
	});
	$("#messageConfirmDialogText").html(content);
}