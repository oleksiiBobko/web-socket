taskApp.controller("TaskController", function($http, $scope) {
   
    $scope.getTasks = function() {
        $http.get(window.location.href + "tasks")
            .success(function(data) {
                $scope.tasks = data;
        });
    };
    
    $scope.addTask = function() {
    		console.log(window.location.origin);
        $http.post(window.location.href + "tasks", $scope.task)
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
    
    var loc = window.location, new_uri;
    if (loc.protocol === "https:") {
        new_uri = "wss:";
    } else {
        new_uri = "ws:";
    }
    new_uri += "//" + loc.host;
    new_uri += loc.pathname + "channel/task";
    
    console.log("new = " + new_uri);
    
    // WebSocket Initialization
    var taskSocket = new WebSocket(new_uri);
 
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