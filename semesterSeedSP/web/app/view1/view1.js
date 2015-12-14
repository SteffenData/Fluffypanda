'use strict';

var app = angular.module('myApp.view1', ['ngRoute']);

app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/view1', {
            templateUrl: 'app/view1/view1.html',
            controller: 'View1Ctrl',
            controllerAs: 'ctrl'
        }).when('/reservation', {
            templateUrl: 'app/view1/reservation.html',
            controller: 'View1Ctrl',
            controllerAs: 'ctrl'
        });
    }]);

app.controller('View1Ctrl', function ($scope, $http, flightFactory) {

    $scope.passengerReservationList = [];
    $scope.reservationFlightID = flightFactory.getreservationFlightID();

    $scope.getFlights = function () {
        $scope.loadImg = true;
        $scope.MSG = "";
        $scope.data = "";
        var year = $scope.date.getFullYear();
        var month = $scope.date.getMonth();
        var day = $scope.date.getDate();
        $scope.jsdate = new Date(year, month, day, 2);
        var finaldate = $scope.jsdate.toISOString();

        var url = 'api/momondo/' + $scope.from + "/" + finaldate + "/" + $scope.seats;

        $http.get(url).then(function successCallback(res) {
            $scope.loadImg = false;
            console.log(res.data);
            $scope.data = res.data;
            
        }, function errorCallback(res) {
            $scope.loadImg = false;
            $scope.MSG = "No results found";
        });
    };

    $scope.getFlights2 = function () {
        $scope.loadImg = true;
        $scope.MSG = "";
        $scope.data = "";
        var year = $scope.date2.getFullYear();
        var month = $scope.date2.getMonth();
        var day = $scope.date2.getDate();
        $scope.jsdate2 = new Date(year, month, day, 2);
        var finaldate2 = $scope.jsdate2.toISOString();

        var url = 'api/momondo/' + $scope.from2 + "/" + $scope.to2 + "/" + finaldate2 + "/" + $scope.seats2;

        $http.get(url).then(function successCallback(res) {
            $scope.loadImg = false;
            console.log(res.data);
            $scope.data = res.data;

        }, function errorCallback(res) {
            $scope.loadImg = false;
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

    $scope.prepareReservation = function (flightID, url) {
        flightFactory.setreservationFlightID(flightID);
        flightFactory.setreservationUrl(url);
    };

    $scope.addPassenger = function (p) {
        var passenger = new Object();
        passenger.firstName = p.reservationFirstname;
        passenger.lastName = p.reservationLastname;
        if(passenger.firstName!="" && passenger.lastName!=""){
        $scope.passengerReservationList.push(passenger);
        $scope.passenger.reservationFirstname = "";
        $scope.passenger.reservationLastname = "";
        } 
    };

    $scope.removePassenger = function () {
        $scope.passengerReservationList.pop();
    };

    $scope.makeReservation = function () {
        
        
        if ($scope.reservationSeats == $scope.passengerReservationList.length) {
            $scope.loadImgReservation = true;
            var flightUrl = flightFactory.getreservationUrl() + "api/flightreservation";
            var finalUrl = "api/momondo";
            var jsonObject = JSON.stringify({flightID: flightFactory.getreservationFlightID(),
                url: flightUrl,
                numberOfSeats: $scope.reservationSeats,
                ReserveeName: $scope.reservationName,
                ReservePhone: $scope.reservationPhone,
                ReserveeEmail: $scope.reservationEmail,
                Passengers: $scope.passengerReservationList});

            $http.post(finalUrl, jsonObject).then(function successCallback(res) {
                $scope.loadImgReservation = false;
                console.log(res.data);
                $scope.reservationMsg = "Your reservation was successful";
                $scope.reservation = "Reservation";
                $scope.line1 = "You fly from: "+res.data.Origin+"   To: "+res.data.Destination;
                $scope.line2 = "Your flightnumber is: "+res.data.flightID;
                $scope.line3 = "The date of your departure is: ";
                $scope.line4 = res.data.Date;
                $scope.line5 = "The time of your departure is: ";
                $scope.line6 = "Passengers: ";
                $scope.line7 = res.data.Passengers;

            }, function errorCallback(res) {
                $scope.loadImgReservation = false;
                $scope.reservationMsg = "Failed to complete the reservation";
            });
        }else{
            $scope.reservationMsg = "Your reservation was not successful, the number of seats didn´t match the amount of passengers";
        }
    };

});

      