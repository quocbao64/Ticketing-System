pipeline {
    agent any

    environment {
        DOCKER_REGISTRY = 'quocbao64'
        DOCKER_TAG = 'latest'
    }

    options {
        skipDefaultCheckout()
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Car Service') {
            steps {
                buildDockerImage('car-service', 'Dockerfile')
                pushDockerImage('car-service')
            }
        }

        stage('Build Customer Service') {
            steps {
                buildDockerImage('customer-service', 'Dockerfile')
            }
        }

//        stage('Build Email Service') {
//            steps {
//                buildDockerImage('email-service', 'Dockerfile')
//            }
//        }
//
//        stage('Build Payment Service') {
//            steps {
//                buildDockerImage('payment-service', 'Dockerfile')
//            }
//        }
//
//        stage('Build Ticket Service') {
//            steps {
//                buildDockerImage('ticket-service', 'Dockerfile')
//            }
//        }
    }
}

def buildDockerImage(serviceName, dockerfilePath) {
    sh "docker build -t ${DOCKER_REGISTRY}/${serviceName}:${DOCKER_TAG} -f ${serviceName}/${dockerfilePath} ."
}

def pushDockerImage(serviceName) {
    withDockerRegistry([
        credentialsId: 'dockerhub',
        url: "",
        bat "docker push ${DOCKER_REGISTRY}/${serviceName}:${DOCKER_TAG}"
    ])
}