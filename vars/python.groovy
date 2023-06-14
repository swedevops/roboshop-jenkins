def call() {
    pipeline {

        agent {
            node {
                label 'workstation'
            }
        }

        options {
            ansiColor('xterm')
        }



        stages {

            stage('Code Quality'){
                steps  {
                  sh 'sonar-scanner -Dsonar.projectKey=${component}'
                }

            }

            stage('Unit Test Cases'){
                steps  {
                    sh 'Unit Test Cases'
                }

            }
            stage('CheckMarx SAST Scan'){
                steps  {
                    sh 'echo checkmarx Scan'
                }

            }
            stage('CheckMarx SCA Scan'){
                steps  {
                    sh 'echo checkmarx SCA Scan'
                }

            }


        }

        post {
            always {
                cleanWs()
            }
        }

    }


}