'use strict';

/* Place your global Factory-service in this file */

angular.module('myApp.factories', []).
        factory('InfoFactory', function () {
            var info = "Hello World from a Factory";
            var getInfo = function getInfo() {
                return info;
            };
            return {
                getInfo: getInfo
            };
        })
        .factory('flightFactory', function () {

            var reservationFlightID = "";
            var airid = {};
            airid.getreservationFlightID = function () {
                return reservationFlightID;
            };

            airid.setreservationFlightID = function (id) {
                reservationFlightID = id;
            };

            return airid;

        });
  