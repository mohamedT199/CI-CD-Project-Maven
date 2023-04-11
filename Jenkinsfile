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
        stage('Increment Version'){
            steps{
                script{
                    sh 'mvn build-helper:parse-version versions:set \
                    -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                    versions:commit'
                    
                    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    Image_Name = matcher[0][1]
                
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
                script {
                    gv.DeployArtifact()
         	}
            }

            
        }
    }
}

