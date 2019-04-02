var app=angular.module("myApp",["ui.router"]);

app.config(function($stateProvider,$urlRouterProvider){

$stateProvider.state('home',{
    url:'/home',
    templateUrl:'home/home.html',
    controller:'HomeController',
    controllerAs:'home'
});
$stateProvider.state('myspace',{
    url:'/myspace',
    templateUrl:'myspace/myspace.html',
    controller:'MySpaceController',
    controllerAs:'myspace'
});
$stateProvider.state('search',{
    url:'/search',
    templateUrl:'search/search.html',
    controller:'SearchController',
    controllerAs:'search'
});
$stateProvider.state('test',{
    url:'/test',
    templateUrl:'test/test.html',
    controller:'TestController',
    controllerAs:'test'
});
$stateProvider.state('connection',{
    url:'/connection',
    templateUrl:'connection/connection.html',
    controller:'ConnectionController',
    controllerAs:'connection'
});
});
