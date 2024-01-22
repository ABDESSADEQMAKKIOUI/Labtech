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
                bat 'mvn clean compile'
            }
        }


          stage('Test FournisseurService') {
                    steps {
                        bat 'mvn test -Dtest=FournisseurServiceTest'
                    }

       }
        stage('Test EchantillonService') {
                   steps {
                       bat 'mvn test -Dtest=EchantillonServiceTest '
                   }
               }


         stage('Test PatientService') {
                   steps {
                       bat 'mvn test -Dtest=PatientServiceTest'
                   }
       }

         stage('Test ResponsableService') {
                   steps {
                       bat 'mvn test -Dtest=ResponsableServiceTest'
                   }
       }
        stage('Test TechnitienService') {
                          steps {
                              bat 'mvn test -Dtest=TechnitienServiceTest'
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