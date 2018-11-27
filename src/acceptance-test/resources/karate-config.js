function() {
    karate.configure('connectTimeout', 5000);
    karate.configure('readTimeout', 5000);
    karate.configure('logPrettyRequest', true);
    karate.configure('logPrettyResponse', true);
    var port = karate.properties['server.port'];
    if (!port) {
        port = karate.env == 'web' ? 8090 : 8080;
    }

    var config = {
        baseUrl: 'http://localhost:8000'
    };

    return config;
}