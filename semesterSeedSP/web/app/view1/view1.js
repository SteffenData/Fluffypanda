'use strict';

angular.module('myApp.view1', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view1', {
                    templateUrl: 'app/view1/view1.html',
                    controller: 'View1Ctrl',
                    controllerAs: 'ctrl'
                }).when('/reservation', {
                    templateUrl: 'app/view1/reservation.html',
                    controller: 'View1Ctrl',
                    controllerAs: 'ctrl'
                });
            }])

        .controller('View1Ctrl', function ($scope, $http) {

            $scope.passengerReservationList = [];

            $scope.getFlights = function () {
                $scope.MSG = "";
                $scope.data = "";
                var year = $scope.date.getFullYear();
                var month = $scope.date.getMonth();
                var day = $scope.date.getDate();
                $scope.jsdate = new Date(year, month, day, 2);
                var finaldate = $scope.jsdate.toISOString();

                var url = 'api/momondo/' + $scope.from + "/" + finaldate + "/" + $scope.seats;

                $http.get(url).then(function successCallback(res) {

                    console.log(res.data);
                    $scope.data = res.data;

                }, function errorCallback(res) {
                    $scope.MSG = "No results found";
                });
            };

            $scope.getFlights2 = function () {
                $scope.MSG = "";
                $scope.data = "";
                var year = $scope.date2.getFullYear();
                var month = $scope.date2.getMonth();
                var day = $scope.date2.getDate();
                $scope.jsdate2 = new Date(year, month, day, 2);
                var finaldate2 = $scope.jsdate2.toISOString();

                var url = 'api/momondo/' + $scope.from2 + "/" + $scope.to2 + "/" + finaldate2 + "/" + $scope.seats2;

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
                else if (min % 60 == 0) {
                    result = (min - min % 60) / 60 + "h";
                }
                else {
                    result = ((min - min % 60) / 60 + "h" + " " + min % 60 + "m");
                }
                return result;
            };

            $scope.prepareReservation = function (flightID) {
                $scope.reservationFlightID = flightID;
            };

            $scope.addPassenger = function (p) {
                var passenger = new Object();
                passenger.firstname = p.reservationFirstname;
                passenger.lastname = p.reservationLastname;
                $scope.passengerReservationList.push(passenger);
            };

            $scope.removePassenger = function () {
                $scope.passengerReservationList.pop();
            };

            $scope.reserve = function () {
                alert(hej);
                $scope.data = "";
                for (var i = 0; i < $scope.data.length; i++) {
                    var flightObject = $scope.data[i];
               
                    if (flightObject.flightID === $scope.reservationFlightID) {
                        $scope.oneFlight = angular.copy($scope.data[i]);
                   
                        return;
                    }
                }
                
            };


        });