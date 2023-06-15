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
                    sh 'ls -l'
                    sh 'sonar-scanner -Dsonar.projectKey=${component} -Dsonar.host.url=http://172.31.82.118:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.qualitygate.wait=true'

                }
            }

            stage('Unit Test Cases'){
                steps  {
                    sh 'echo Unit Test Cases'
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