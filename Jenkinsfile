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
                    def version = matcher[0][1]
                    Image_Name = "${version}-$BUILD_NUMBER"
                
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
    post {
    
        success {
        
            sh " git add . "
            sh "git commit -m 'change version' "
            sh "git push origin HEAD:jenkins-jobs"
        }
        failure {
        
            sh "git restore . "
        }
    }
   
}

