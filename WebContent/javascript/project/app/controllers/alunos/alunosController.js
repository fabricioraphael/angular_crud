midasApp.controller('alunosConfiguration', 
				['$scope', '$http', '$rootScope', '$cookieStore', '$location', '$filter', '$routeParams', 'modalService', 'profileService',
				 function ($scope, $http, $rootScope,$cookieStore, $location, $filter, $routeParams, modalService, profileService) {
		  
	$scope.init = function(){
		$scope.getAllAlunos();
	};
	
	$scope.initEdit = function(){
		$scope.aluno = $location.search().aluno;
	};
	
	$scope.initNew = function(){
		$scope.aluno = {};
	};
	
	$scope.edit = function(aluno){
		$location.path('/midas/alunos/' + aluno.id + '/edit').search({'aluno': aluno});
	}
	
	$scope.new = function(){
		$location.path('/midas/alunos/new');
	}
	
	$scope.getAllAlunos = function(){
		$http({
			method: 'GET',
			url: "/mob_vaga/rest/alunos",
			headers: {'Content-Type': 'application/json'},
		}).success(function(response, status, headers, config) {
			
			if (response.has_error) {
				$scope.has_error = response.has_error;
				$scope.errors = response.operation_error.erros;
				
			} else {
				console.log(response.data);

				$scope.alunos = response.data;
			}
		}).error(function (data, status, headers, config) {
			console.log("status: " + status);
			console.log(data);
			console.log(headers);
			console.log(config);
		});
	};

	$scope.updateAluno = function(aluno){		
		console.log(aluno);

		$http({
			method: 'POST',
			url: "/mob_vaga/rest/alunos/" + aluno.id,
			data: aluno,
			headers: {
			       "Accept": "application/json;charset=utf-8",
			       "Accept-Charset":"charset=utf-8"},
		}).success(function(response, status, headers, config) {
			
			console.log(response);
			
			if(response.has_error){
				$scope.has_error = response.has_error;
				$scope.errors = response.operation_error.erros;
				
			} else{
				$rootScope.addSuccessMessage("Aluno editado com sucesso!");
			}
		}).error(function (data, status, headers, config) {
			console.log("status: " + status);
			console.log(data);
			console.log(headers);
			console.log(config);
		});
	}
	
	$scope.createAluno = function(aluno){
		if(aluno.name == undefined || aluno.name == "" ||
				aluno.age == undefined || aluno.age == ""){
			
			alert("Campos obrigatórios");
			return;
		}
		
		$http({
			method: 'POST',
			url: "/mob_vaga/rest/alunos",
			data: aluno,
			headers: {
			       "Accept": "application/json;charset=utf-8",
			       "Accept-Charset":"charset=utf-8"},
		}).success(function(response, status, headers, config) {
			
			console.log(response);
			
			if(response.has_error){
				$scope.has_error = response.has_error;
				$scope.errors = response.operation_error.erros;
				
			} else{
				$rootScope.addSuccessMessage("Aluno criado com sucesso!");
			}
			
			
		}).error(function (data, status, headers, config) {
			console.log("status: " + status);
			console.log(data);
			console.log(headers);
			console.log(config);
		});
	}
}]);