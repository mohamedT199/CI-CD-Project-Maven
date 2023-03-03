pipeline {
    agent any 
    parameters {
        string(name: 'version' ,  defaultValue: '1.0.1' , description: 'value of version')
        booleanParam(name: 'runTest' , defaultValue: true , description: 'is run test or no ')
        choice(name: 'versionChoice' , choices: ['1.0.1' , '1.0.2'] , description: 'chloise what version you need')
    }
    tools  {
        maven 'maven-basic'
        gradle 'gradel-basic'
    }
    environment {
        NEW_VERSION = "${env.BUILD_NUMBER}"
        SERVER_CREDENTIALS = credentials("server-deployment")
    }
    stages {
        stage("build") {
            steps {
                sh 'mvn install'
                sh 'mvn package'
                echo 'building the application ...'
                echo "this is the new Verion of new Build ${NEW_VERSION}"
                echo "this is the Branch name that we use iit to Build ${env.BRANCH_NAME}"
            }
        }
        stage("test") {
            when {
                expression {
                    "${env.BUILD_NUMBER}" == "12" || params.runTest
                }
            }
            steps {
                echo 'testing the application ...'
            }
        }
        stage("deploy") {
            steps {
                echo 'deploying the applicstion ..'
                echo "this is global environment variable ${SERVER_CREDENTIALS}"
                withCredentials([
                    usernamePassword(credentialsId: 'server-deployment' , usernameVariable: 'USER' , passwordVariable: 'PWD' )
                    ]){
                        echo "${USER}"
			echo "${PWD}"
                        echo "deploying version ${params.version} .... "
                    
                }
            }
        }
    }
    post {
        success{
            echo "process successfully build new artifacts "
        }
        failure{
            echo "Build is Failed to Build new wars "
        }
    }
}
