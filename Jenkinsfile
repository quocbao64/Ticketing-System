pipeline {
    agent any

    environment {
        DOCKER_REGISTRY = 'quocbao64'
        DOCKER_TAG = 'latest'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Push Docker Images') {
            steps {
                buildAndPushDockerImage('car-service', 'Dockerfile')

                buildAndPushDockerImage('customer-service', 'Dockerfile')

                buildAndPushDockerImage('email-service', 'Dockerfile')

                buildAndPushDockerImage('payment-service', 'Dockerfile')

                buildAndPushDockerImage('ticket-service', 'Dockerfile')

            }
        }
    }
}

def buildAndPushDockerImage(serviceName, dockerfilePath) {
    dir(serviceName) {
        sh "docker build -t ${DOCKER_REGISTRY}/${serviceName}:${DOCKER_TAG} -f ${dockerfilePath} ."
        sh "docker push ${DOCKER_REGISTRY}/${serviceName}:${DOCKER_TAG}"
    }
}
