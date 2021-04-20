def call(Closure body) {
    stage('windows'){
        body()
    }
}
