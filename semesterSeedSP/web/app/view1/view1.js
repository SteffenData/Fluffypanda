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
                $scope.MSG = "";
                $scope.data = "";
                var year = $scope.date.getFullYear();
                var month = $scope.date.getMonth();
                var day = $scope.date.getDate();
                $scope.jsdate = new Date(year, month, day, 1);
                var finaldate = $scope.jsdate.toISOString();

                var url = 'api/momondo/' + $scope.from + "/" + finaldate + "/" + $scope.seats;

                $http.get(url).then(function successCallback(res) {

                    console.log(res.data);
                    $scope.data = res.data;

                }, function errorCallback(res) {
                    $scope.MSG = "No results found";
                });
            };

            $scope.minToHours = function (min) {
                var result;
                if (min < 60) {
                    result = min + "m";
                }
                else if (min % 60==0) {
                    result = (min - min % 60) / 60 + "h";
                }
                else {
                    result = ((min-min%60)/60+"h"+" "+min%60+"m");
                }
                return result;
//                   var minutes = min%60;
//                   var hours = (min - minutes)/60;
//                   return hours + ":"+ minutes;
            };

        });