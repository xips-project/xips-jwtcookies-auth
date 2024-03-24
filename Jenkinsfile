

pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests verify sonar:sonar'
            }
        }
    }
}

