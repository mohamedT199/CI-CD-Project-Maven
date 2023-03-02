pipeline {
    agent any 
    environment {
        NEW_VERSION = BUILD_NUMBER
        SERVER_CREDENTIALS = credentials("server-deployment")
    }
    stages {
        stage("build") {
            steps {
                echo 'building the application ...'
                echo "this is the new Verion of new Build ${NEW_VERSION}"
                echo "this is the Branch name that we use iit to Build ${BRANCH_NAME}"
            }
        }
        stage("test") {
            steps {
                echo 'testing the application ...'
            }
        }
        stage("deploy") {
            steps {
                echo 'deploying the applicstion ..'
                echo "this is global environment variable ${SERVER_CREDENTIALS}"
                withCredentials([
                    usernamePassword(credentials: 'server-deployment' , usernameVariable: USER , passwordVariable: PWD )
                    ]){
                        echo "${USER}"
                    
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
