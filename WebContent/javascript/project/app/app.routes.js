midasApp.config(['$routeProvider', '$locationProvider', 
     function($routeProvider, $locationProvider) {  
	
	        $routeProvider.

			//AVISOS DE IRREGULARIDADE

			.when("/raiz/alunos",{
				templateUrl: "../javascript/project/app/views/alunos/index.html", 
				controller: "alunoController",
				manager: true
			})
			.when("/raiz/alunos/:aluno_id/edit",{
				templateUrl: "../javascript/project/app/views/alunos/edit.html",
				controller: "alunoController",
				manager: true
			})
			.when("/raiz/alunos/new",{
				templateUrl: "../javascript/project/app/views/alunos/new.html",
				controller: "alunoController",
				manager: true
			})
			
			
			.otherwise({ redirectTo: '/midas'});
     }
]);