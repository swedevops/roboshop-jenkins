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
                   // sh 'ls -l'
                //  sh 'sonar-scanner -Dsonar.projectKey=${component} -Dsonar.host.url=http://172.31.82.118:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.qualitygate.wait=true'
                 sh 'echo Code Quality'
                }
            }

            stage('Unit Test Cases'){
                steps  {
                   sh 'echo Unit Test Cases'
                 // sh 'npm test'
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
            stage('Release Application'){
                when{
                    expression{
                       env.TAG_NAME ==~ ".*"
                    }
                }
                steps  {
                    sh 'env'
                    sh 'curl -v -u admin:admin --upload-file server.js http://172.31.86.184:8081/repository/cart/1.0/server.js'
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