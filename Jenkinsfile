def stepsLogic 
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
        stage("Init"){
            steps {
                script {
                    stepsLogic = load "stepsLogic.groovy"
                }
            }
        }
        stage("build") {
            
            steps {
                script {
                    stepsLogic.getBuildMassage()
                }
                sh 'mvn install'
                sh 'mvn package'
                echo 'building the application ...'
                echo "this is the new Verion of new Build ${NEW_VERSION}"
                echo "this is the Branch name that we use iit to Build ${env.GIT_BRANCH}"
            }
        }
        stage("test") {
            when {
                expression {
                    "${env.BUILD_NUMBER}" == "12" || params.runTest
                }
            }
           
            steps {
                script{
                    stepsLogic.getTestMassage()
                    echo "${stepsLogic.getRetMessage()}"
                }
                echo 'testing the application ...'
            }
        }
        stage("deploy") {
            input {
                message "deploy Environment"
                ok  "Deploy" 
                parameters {
                    choice(name : "Environemnt" , choices: ["stage" , "development" , "production"] , description: "choice your Deployment Environment")
                }
            }
            
            steps {
                script{
                    stepsLogic.getDeploymentMessage()
                    env.Environments = input message: "deploy Environment" , ok : "Deploy" ,  parameters : [choice(name : "Environemntt" , choices: ["stage" , "development" , "production"] , description: "choice your Deployment Environment") , choice(name : "Environemnttt" , choices: ["stage" , "development" , "production"] , description: "choice your Deployment Environment")]
                    
                }
                echo 'deploying the applicstion ..'
                echo "this is global environment variable ${SERVER_CREDENTIALS}"
                withCredentials([
                    usernamePassword(credentialsId: 'server-deployment' , usernameVariable: 'USER' , passwordVariable: 'PWD' )
                    ]){
                        echo "${USER}"
                        echo "${PWD}"
                        echo "deploying version ${params.version} .... "

                }
                echo "-----------------------------------------------------------------------"
                echo "this is first variable only thhe first ${env.Environemnt}"
                echo "this is first variable assigned to other ${env.Environments}"
            }
        }
        stage("afterDeloyment"){
	    
            steps {
                script {
    	            def envtoText = ${env.Environments}
        	    def result = envtoText.eachLine { (key,value) = it.split('=', 2); map[key] = value }
                    println(result)
    	        }

                echo "thisis accessed to other stage from the assigned variable${env.Environments}"
		echo "thisis accessed to other stage from the assigned variable${env.Environments[1]}"
		echo "thisis accessed to other stage from the assigned variable${env.Environments[1]}"
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

