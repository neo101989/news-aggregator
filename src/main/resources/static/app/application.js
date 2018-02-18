var NewsAggregator = angular.module('NewsAggregator', []);
var TabTypes = {
    Main: 'main',
    Configuration: 'configuration'
};

var Formats = {
    Rss: 'rss',
    Html: 'html'
};


NewsAggregator.controller('MainController',['$scope','backgroundService',
    function ($scope, backgroundService) {
        $scope.tabs = [
            {
                type: TabTypes.Main,
                title: 'Main',
                state: false
            },
            {
                type: TabTypes.Configuration,
                title: 'Configuration',
                state: true
            }
        ];

        $scope.formats = [
            Formats.Rss,
            Formats.Html
        ];

        $scope.title = '';

        $scope.selectTab = function(type){
            $scope.tabs.forEach(function (tab) {
                tab.state = tab.type === type;
                if(tab.state){
                    $scope.currentTab = tab;
                }
            });
            if(type === TabTypes.Main){
                if($scope.title) {
                    backgroundService.searchNewsByTitle($scope.title).then(function (response) {
                        $scope.newsList = response.data;
                    })
                }
                else{
                    backgroundService.getNews().then(function (response) {
                        $scope.newsList = response.data;
                    })
                }
            }
        };

        $scope.addParser = function (parser) {
            backgroundService.addParser(parser).then(function () {
                alert('Successfully!');
            }, function () {
                alert('Error!')
            })
        };

        $scope.search = function(){
            backgroundService.searchNewsByTitle($scope.title).then(function (response) {
                $scope.newsList = response.data;
            })
        };

        $scope.selectTab(TabTypes.Configuration);

    }]);