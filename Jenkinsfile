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
                buildAndPushDockerImage('car-service', 'Dockerfile')
            }
        }

        stage('Build Customer Service') {
            steps {
                buildAndPushDockerImage('customer-service', 'Dockerfile')
            }
        }

//        stage('Build Email Service') {
//            steps {
//                buildAndPushDockerImage('email-service', 'Dockerfile')
//            }
//        }
//
//        stage('Build Payment Service') {
//            steps {
//                buildAndPushDockerImage('payment-service', 'Dockerfile')
//            }
//        }
//
//        stage('Build Ticket Service') {
//            steps {
//                buildAndPushDockerImage('ticket-service', 'Dockerfile')
//            }
//        }
    }
}

def buildAndPushDockerImage(serviceName, dockerfilePath) {
    sh "docker build -t ${DOCKER_REGISTRY}/${serviceName}:${DOCKER_TAG} -f ${serviceName}/${dockerfilePath} ."
    sh "docker push ${DOCKER_REGISTRY}/${serviceName}:${DOCKER_TAG}"
}
