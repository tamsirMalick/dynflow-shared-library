def call() {
        String getResult = new URL('http://dynflow-gateway-dacdynflow-dev.k8s-test.orange-sonatel.com/api/build-microservice/builds').text
        println(getResult);
}
