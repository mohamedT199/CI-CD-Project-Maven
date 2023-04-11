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
                    echo "${matcher}"
                    echo "${matcher[0]}"
                    Image_Name = matcher[0][1]
                
                }
            }
        }
        stage('BuildJar'){
            steps {
                script {
                    gv.BuildJar()
                    echo "${Image_Name}"
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

