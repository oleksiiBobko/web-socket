taskApp.controller("TaskController", function($http, $scope) {
   
    $scope.getTasks = function() {
        $http.get("http://127.0.0.1:8080/tasks")
            .success(function(data) {
                $scope.tasks = data;
        });
    };
    
    $scope.addTask = function() {
        $http.post("http://127.0.0.1:8080/tasks", $scope.task)
            .success(function() {
                $scope.resetTask();
        });
    };
    
    $scope.resetTask = function() {
        $scope.task = {
            title: "",
            description: "",
            duration: "",
            universal: true
        };
    };
    
    $scope.resetTask();
    $scope.getTasks();
    
    // WebSocket Initialization
    var taskSocket = new WebSocket("ws://127.0.0.1:8080/channel/task");
 
    taskSocket.onmessage = function(message) {
        $scope.tasks = JSON.parse(message.data);
        $scope.$apply();        
    };

    taskSocket.onclose = function() {
        $scope.message = {
            type: "danger",
            short: "Socket error",
            long: "An error occured with the WebSocket."
        };
        $scope.$apply();    
    }
    
});