def call() {
    def post = new URL("http://gateway-dacdynflow-dev.k8s-test.orange-sonatel.com/api/build-microservice/builds").openConnection();
    def message = '{\n' +
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
            '    "gitAuthorName": "Diom 3-4"\n' +
            '}'
    post.setRequestMethod("POST")
    post.setDoOutput(true)
    post.setRequestProperty("Content-Type", "application/json")
    post.getOutputStream().write(message.getBytes("UTF-8"));
}
