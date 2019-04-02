app.controller("TestController", function($scope,$http,$location){  
    $scope.getDataTest=function(){
        $scope.str=null;
        $http({
            method:"GET",
            url:"http://localhost:8080/MyUser/getDataTest"
        }).then(function(response){
            data=response.data;            
            $scope.str=data;
            console.log("str : " + $scope.str.return);
        },function (error){
            console.log(error, "can not get data");
        });
    }
});