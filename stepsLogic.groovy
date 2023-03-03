def getBuildMassage(){
    echo "building ... this is message from groovy script"
}
def getTestMassage(){
    echo "Testing.... this is message from groovy script"
}
def getDeploymentMessage(){
    echo "deploying... this is message from groovy script"
}
def getRetMessage(){
    return "test Return Message from groovy script..."
}

return this; 
