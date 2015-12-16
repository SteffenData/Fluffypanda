'use strict';

angular.module('myApp.view2', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view2', {
                    templateUrl: 'app/view2/view2.html',
                    controller: 'View2Ctrl'
                });
            }])

        .controller('View2Ctrl', function ($http, $scope, userFactory) {
            $scope.MSG = "";
            $scope.loadImg = true;
            var userName = userFactory.getUsername();
            $http({
                method: 'GET',
                url: 'api/momondo/getreservation/' + userName
            }).then(function successCallback(res) {
                $scope.loadImg = false;
                $scope.data = res.data;

            }, function errorCallback(res) {
                $scope.loadImg = false;
                $scope.MSG = "Your reservations were not found";
            });
            
            $scope.minToHours = function (min) {
                var result;
                if (min < 60) {
                    result = min + "m";
                }
                else if (min % 60 == 0) {
                    result = (min - min % 60) / 60 + "h";
                }
                else {
                    result = ((min - min % 60) / 60 + "h" + " " + min % 60 + "m");
                }
                return result;
            };
        });