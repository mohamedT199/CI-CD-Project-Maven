
pipeline {
    agent any 
    environment {
        NEW_VERSION = '1.4.1'
        SERVER_CRED = credentials('GitUser')
    }
    stages {
        stage("build") {
            steps {
                echo 'building the application ...'
                echo "this id define in environment of Jenkinsfile ${NEW_VERSION}"
		echo "BRANCH NAME ${env.BRANCH_NAME} "
            }
        }
        stage("test") {
            when {
                expression {
                    env.BRANCH_NAME == "jenkins-jobs"
                }
            }
            steps {
                echo 'testing the application ...'
            }
        }
        stage("deploy") {
            steps {
                echo 'deploying the applicstion ..'
		echo 'hello user deploy stage'
                echo "this is for publiic crediantials ${SERVER_CRED} "
                withCredentials([ usernamePassword(credentials: 'GitUser' , usernameVariable: USER , passwordVariable: PASS) ]){
                        echo "Credentials here are used in deploy stage only ${usernameVariable}"
                        echo "Credentials here are used in deploy stage only ${passwordVariable}"
                }
            }
        }
    }
    post {
        always {
            echo "this command always run and echoed "
        }
        failure {
            echo "this command in steps failuer "
        }
    }
}
