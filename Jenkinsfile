def gv
pipeline {
    agent any
    
    tools {
        maven 'maven-basic'
    }
    stages {
        stage('InitSteps') {
            steps {
                script {
                    gv = load 'BuildSteps.groovy'
                }
            }
        }
        stage('BuildJar'){
            steps {
                script {
                    gv.BuildJar()
                }
                
            }
            
        }
        stage('BuildImage'){
            steps {
                script {
                    gv.BuildImage()
                }
                
            }
            
        }
        stage('ProdDeploy'){
            steps {
            steps {
                script {
                    gv.DeployArtifact()
         	}
            }

            
        }
    }
}

