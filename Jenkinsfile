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

        stage('Delete old Docker images') {
            steps {
                sh "docker rmi -f ${oldImageID}"
            }
        }

        stage('Build and Deploy Car Service') {
            steps {
                buildDockerImage('car-service', 'Dockerfile')
                pushDockerImage('car-service')
            }
        }

        stage('Build and Deploy Email Service') {
            steps {
                buildDockerImage('email-service', 'Dockerfile')
                pushDockerImage('email-service')
            }
        }

        stage('Build and Deploy Payment Service') {
            steps {
                buildDockerImage('payment-service', 'Dockerfile')
                pushDockerImage('payment-service')
            }
        }

        stage('Build and Deploy Order Service') {
            steps {
                buildDockerImage('order-service', 'Dockerfile')
                pushDockerImage('order-service')
            }
        }

        stage('Build and Deploy Api Gateway Service') {
            steps {
                buildDockerImage('api-gateway', 'Dockerfile')
                pushDockerImage('api-gateway')
            }
        }

        stage('Build and Deploy Discovery Service') {
            steps {
                buildDockerImage('discovery-server', 'Dockerfile')
                pushDockerImage('discovery-server')
            }
        }
    }
}

def buildDockerImage(serviceName, dockerfilePath) {
    sh "docker build -t ${DOCKER_REGISTRY}/${serviceName}:${DOCKER_TAG} -f ${serviceName}/${dockerfilePath} ."
}

def pushDockerImage(serviceName) {
    withDockerRegistry([
        credentialsId: 'dockerhub',
        url: ""
    ]) {
        sh "docker push ${DOCKER_REGISTRY}/${serviceName}:${DOCKER_TAG}"
    }
}
