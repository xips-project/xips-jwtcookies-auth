

pipeline {
    agent any
    tools {
            maven 'Maven'
            jdk 'jdk'
        }
    stages {
        stage('Build') {
            steps {

                sh 'mvn -f ./xips-main-project-master/pom.xml -B -DskipTests -Dsonar.token=74cdc42cdd4df1b51751770f42edcbcb13ccc34a verify sonar:sonar'
            }
        }
    }
}

