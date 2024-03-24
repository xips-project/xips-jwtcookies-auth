

pipeline {
    agent any
    tools {
            maven 'Maven'
            jdk 'jdk'
        }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -f ./xips-main-project-master/pom.xml -B -DskipTests verify sonar:sonar'
            }
        }
    }
}

