

pipeline {
    agent any
    stages {
        tools {
            maven 'Maven 3.8.6'
            jdk 'Java 17.0.4.1'
        }
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests verify sonar:sonar'
            }
        }
    }
}

