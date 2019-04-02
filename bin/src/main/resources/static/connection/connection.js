app.controller("ConnectionController", function($scope,$http,$location){

    $scope.credential={};
    $scope.login=null;
    $scope.password=null;  
    $scope.myuser={};
    $scope.error=null;

    $scope.getConnect=function(){
        console.log("getConnect");
        console.log("$scope.credential.login : " + $scope.credential.login);
        console.log("$scope.credential.password : " + $scope.credential.password);
        $http.post("http://localhost:8080/MyUser/getConnect", $scope.credential).then(function(response){
            console.log("success");
            data = response.data;
            console.log("data.login : " + data.login);
            $scope.goToMySpace();
            $scope.myuser = data;
        },function(error){
            data = error.data;
            console.log("error : " + data.return);
            alert("error : " + data.return);
        });
    }

$scope.goToMySpace=function(){
    console.log("goToMySpace()");
    $location.path("/myspace");

};
});
