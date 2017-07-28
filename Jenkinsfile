#!/usr/bin/env groovy
pipeline {
    agent any
    triggers {
        pollSCM('* * * * *')
    }

    stages {
        stage('Build artifact') {
            steps {
                sh './gradlew clean build -x test'
                archiveArtifacts artifacts: '**/build/libs/*.jar', fingerprint: true
            }
        }
        stage('Test artifact') {
            steps {
                sh './gradlew cleanTest test'
            }
        }
        stage('Build docker image') {
            steps {
                sh 'docker build -t dtr.rogfk.no/fint/health-adapter .'
            }
        }
        stage('Push docker image') {
            steps {
                sh 'docker push dtr.rogfk.no/fint/health-adapter'
            }
        }
    }
}