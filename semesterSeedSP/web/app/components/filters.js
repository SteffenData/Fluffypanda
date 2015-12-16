'use strict';

/* Place your Global Filters in this file */

angular.module('myApp.filters', []).
  filter('checkmark', function () {
    return function(input) {
      return input ? '\u2713' : '\u2718';
    };
  })
          .filter('minToHours',function (min) {
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
    });
