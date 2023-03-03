def BuildJar(){
    echo "Building Artifact Start ...."
    sh "mvn package"
    
}
def BuildImage(){
    echo "Build Docker Image Start ...."
    withCredentials([userPassword(credentialsId: 'Docker-Hub' , usernameVariable: 'USER' , passwordVariable: 'PASS')]){
        sh "echo ${PASS} | docker login -u ${USER} --password-stdin "
        sh "docker build -t talat345/demo-repo:jma-3.0.1 . "
        sh "docker push talat345/demo-repo:jma-3.0.1 "
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