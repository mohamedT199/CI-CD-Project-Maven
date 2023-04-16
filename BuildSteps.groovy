#!/usr/bin/env groovy
def BuildJar(){
    echo "Building Artifact Start ...."
    sh "mvn clean package"
    
}
def BuildImage(){
    echo "Build Docker Image Start ...."
    withCredentials([usernamePassword(credentialsId: 'Docker-Hub' , usernameVariable: 'USER' , passwordVariable: 'PASS')]){
        sh "docker login --username=$USER --password=$PASS"
        sh "docker build -t talat345/demo-repo:jma-${Image_Name} . "
        sh "docker push talat345/demo-repo:jma-${Image_Name} "
    }
}
def DeployArtifact(){
    echo "deploying... this is message from groovy script"
}
def getRetMessage(){
    return "test Return Message from groovy script..."
}

return this; 
//stepsLogic.groovy
