#!/usr/bin/env groovy

@Library('Jenkiins-Shared-Library')
def gv
pipeline {
    agent any
    parameters{
        choice(name:'deploy' , choices:['Yes' , 'No'] , description:'you want t deploy new version or not')
    }
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
        stage('Version Increment'){
            steps{
                script{
                    bumpVersion()
                }
            }
        }
        stage('Build Artifact'){
            steps {
                script {
                    buildArtifact()
                }
                
            }
            
        }
        stage('Docker Login'){
            steps {
                script {
                    logintoRepo('Docker-Hub')
                }
            }
        }
        stage('Build Docker Image'){
            steps {
                script {
                    buildDockerImage('Docker-Hub')
                }
                
            }
            
        }
        stage('Deploy'){
            steps {
                script {
                    gv.DeployArtifact()
         	    }
            }

            
        }
    }
    post {
    
        success {

            sh "git add . "
            sh "git commit -m 'CI: version bump' "
            sh "git push origin HEAD:jenkins-jobs"
        }

    }
   
}

