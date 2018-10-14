var demoApplication = angular.module('demoApp');

demoApplication.controller('newsController', function($http) {

        var vm = this;

        vm.getNews = function(cat) {
            $http({method: 'GET', url: 'api/news/pl/' + cat})
                .then(function successCallback(response) {
                    vm.localNews = response.data;
                    console.log("getting news from category: " + cat);
                }, function errorCallback(response) {
                    console.log("error dbg")
                });
        };

        vm.getNews('technology');

    });

