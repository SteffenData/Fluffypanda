'use strict';

angular.module('myApp.view1', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view1', {
                    templateUrl: 'app/view1/view1.html',
                    controller: 'View1Ctrl',
                    controllerAs: 'ctrl'
                });
            }])

        .controller('View1Ctrl', function ($scope, $http) {
            
                $scope.getFlights = function () {
                   
                  var url = 'api/flightinfo/' + $scope.from + "/" + $scope.date + "/" + $scope.seats;
                    alert("jeg er inde i metoden");
                    $http.get(url).then(function successCallback(res) {
                        alert("før res.data" + res.airline); 
                        console.log(res.data);
                        $scope.data = res.data;
                     
                    }, function errorCallback(res) {
                         alert("er jeg en fejl");
                    });
                };
                
                
            });