

pipeline {
    agent any
    tools {
            maven 'Maven 3.8.6'
            jdk 'jdk17'
        }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests verify sonar:sonar'
            }
        }
    }
}

