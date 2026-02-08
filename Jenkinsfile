pipeline {
    agent any
    parameters {
        string(name: 'baseUrl', defaultValue: 'https://rahulshettyacademy.com', description: 'Base URL for API tests')
    }
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/spy235/RestAssured-Framework', branch: 'main'
            }
        }
        stage('Run Tests') {
            steps {
                sh "mvn clean test -DbaseUrl=${params.baseUrl}"
            }
        }
        stage('Publish Report') {
            steps {
                publishHTML([reportDir: 'target', reportFiles: 'ExtentReport.html', reportName: 'API Test Report'])
            }
        }
    }
}
