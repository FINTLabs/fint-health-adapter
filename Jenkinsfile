pipeline {
    agent { label 'docker' }
    stages {
        stage('Build') {
            steps {
                withDockerRegistry([credentialsId: 'dtr-fintlabs-no', url: 'https://dtr.fintlabs.no']) {
                    sh "docker build --tag ${GIT_COMMIT} ."
                }
            }
        }
        stage('Publish') {
            when { branch 'master' }
            steps {
                withDockerRegistry([credentialsId: 'dtr-fintlabs-no', url: 'https://dtr.fintlabs.no']) {
                    sh "docker tag ${GIT_COMMIT} dtr.fintlabs.no/beta/health-adapter:latest"
                    sh "docker push 'dtr.fintlabs.no/beta/health-adapter:latest'"
                }
            }
        }
        stage('Publish PR') {
            when { changeRequest() }
            steps {
                withDockerRegistry([credentialsId: 'dtr-fintlabs-no', url: 'https://dtr.fintlabs.no']) {
                    sh "docker tag ${GIT_COMMIT} dtr.fintlabs.no/beta/health-adapter:${BRANCH_NAME}"
                    sh "docker push 'dtr.fintlabs.no/beta/health-adapter:${BRANCH_NAME}'"
                }
            }
        }
        stage('Publish Tag') {
            when { buildingTag() }
            steps {
                withDockerRegistry([credentialsId: 'dtr-fintlabs-no', url: 'https://dtr.fintlabs.no']) {
                    sh "docker tag ${GIT_COMMIT} dtr.fintlabs.no/beta/health-adapter:${TAG_NAME}"
                    sh "docker push 'dtr.fintlabs.no/beta/health-adapter:${TAG_NAME}'"
                }
            }
        }
    }
}
