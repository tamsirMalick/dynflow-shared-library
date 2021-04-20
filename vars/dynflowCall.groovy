static def call(String env) {
    postJenkinsFileInfo(env)
}

def postJenkinsFileInfo(String environment) {
    println "Information du build en cours d'envois..."
    int statusCode
    String postUrl = "http://gateway-dacdynflow-dev.k8s-test.orange-sonatel.com/api/build-microservice/builds"
    URL gatewayUrl = new URL(postUrl)
    HttpURLConnection connection = (HttpURLConnection) gatewayUrl.openConnection()
    connection.setRequestMethod("POST")
    connection.setDoOutput(true)
    connection.setDoInput(true)
    connection.setRequestProperty("Content-Type", "application/json")

    String response = ""
    OutputStream os
    try {
        os = connection.getOutputStream()
        byte[] input = environment.getBytes("utf-8");
        os.write(input, 0, input.length);

        statusCode = connection.responseCode
        println "Connection status code: $statusCode"
        if (statusCode == 200) {
            println "Authentication succeeded"
            println "Server response:"
            println "-----"
            response = displayServerResponse(connection)
            println "-----"
        }
        if (statusCode == 400) {
            println "Bad request"
            println "Server response:"
            println "-----"
            response = displayServerResponse(connection)
            println "-----"
        }
    } catch (Exception e) {
        println "Un problem est survenu lors de l'envois des informations"
        println e.getMessage()
    } finally {
        if (connection != null) {
            connection.disconnect();
        }
        if (os != null) {
            os.close()
        }
    }
    return response
}

def displayServerResponse(HttpURLConnection connection) {
    InputStream is;
    if (connection.getResponseCode() == 200) {
        is = connection.getInputStream();
    } else {
        is = connection.getErrorStream();
    }
    println "Response Content-Type:" + connection.getContentType()
    if (connection.getContentType().contains("application/json")) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();
        println sb
        return sb.toString()
    }
}
