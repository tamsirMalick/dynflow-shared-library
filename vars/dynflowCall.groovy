static def call() {
    int statusCode
    HttpURLConnection connection
    URL newUrl
    newUrl = new URL("http://gateway-dacdynflow-dev.k8s-test.orange-sonatel.com/api/build-microservice/builds")
    connection = (HttpURLConnection) newUrl.openConnection()
    connection.setRequestMethod("POST")
    connection.setDoOutput(true)
    connection.setRequestProperty("Content-Type", "application/json")
    String message = '{\n' +
            '    "buildID": "Gateway",\n' +
            '    "projectID": "MID",\n' +
            '    "groupID": "sn.sonatel.dsi.dac.coe",\n' +
            '    "artefactID": "OEM",\n' +
            '    "pipelineName": "jenkins",\n' +
            '    "appVersion": "2",\n' +
            '    "stageName": "Build and Push Dokcer",\n' +
            '    "stageType": "DEPLOYMENT",\n' +
            '    "status": "SUCCESS",\n' +
            '    "stageCommand": "sh docker -t build .",\n' +
            '    "gitBranch": "65456dsbhjs",\n' +
            '    "gitRevision": "lundi",\n' +
            '    "gitResume": "12hj",\n' +
            '    "changeAuthor": "Bitty Diouf",\n' +
            '    "gitCommit": "THis is a test",\n' +
            '    "gitAuthorName": "MBENGUE 3-5"\n' +
            '}'
    OutputStream os = connection.getOutputStream()
    byte[] input = message.getBytes("utf-8");
    os.write(input, 0, input.length);
    statusCode = connection.responseCode
    os.close()
}
