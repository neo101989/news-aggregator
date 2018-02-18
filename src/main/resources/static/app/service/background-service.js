NewsAggregator.service('backgroundService', ['$http', function ($http) {

    return {
        addParser: function (parser) {
            return $http.post('/parser/add', parser);
        },
        getNews: function () {
            return $http.get('/news/all');
        },
        searchNewsByTitle: function (title) {
            return $http.get('/news/search/title', {params: {title: title}});
        }
    }
}]);