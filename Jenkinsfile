

pipeline {
    agent any
    tools {
            maven 'Maven'
            jdk 'jdk'
        }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests verify sonar:sonar'
            }
        }
    }
}

