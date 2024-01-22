pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/ABDESSADEQMAKKIOUI/Labtech.git'
            }
        }

        stage('Clean') {
            steps {
                script {
                    // Use 'clean install' instead of 'clean compile' to also compile the code
                    bat 'mvn clean install'
                }
            }
        }

        stage('Test FournisseurService') {
            steps {
                script {
                    // Use 'clean test' instead of 'test' to ensure a clean test run
                    bat 'mvn clean test -Dtest=FournisseurServiceTest'
                }
            }
        }

        stage('Test EchantillonService') {
            steps {
                script {
                    bat 'mvn clean test -Dtest=EchantillonServiceTest'
                }
            }
        }

        stage('Test PatientService') {
            steps {
                script {
                    bat 'mvn clean test -Dtest=PatientServiceTest'
                }
            }
        }

        stage('Test ResponsableService') {
            steps {
                script {
                    bat 'mvn clean test -Dtest=ResponsableServiceTest'
                }
            }
        }

        stage('Test TechnitienService') {
            steps {
                script {
                    bat 'mvn clean test -Dtest=TechnitienServiceTest'
                }
            }
        }
    }

    post {
        success {
            echo 'Build succeeded!'
        }

        failure {
            echo 'Build failed!'
        }
    }
}
