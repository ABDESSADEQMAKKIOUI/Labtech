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

        stage('Test AnalyseService') {
            steps {
                bat 'mvn test -Dtest=AnalyseServiceTestAnalyse'
            }
        }
        stage('Test EchantillonService') {
            steps {
                bat 'mvn test -Dtest=EchantillonServiceTestAnalyse '
            }
        }
        stage('Test EnormService') {
            steps {
                bat 'mvn test -Dtest=EnormServiceTest'
            }
        }
          stage('Test FournisseurService') {
                    steps {
                        bat 'mvn test -Dtest=FournisseurServiceTestAnalyse'
                    }
       }
         stage('Test MaterielService') {
                   steps {
                       bat 'mvn test -Dtest=MaterielServiceTest'
                   }
       }
         stage('Test PatientService') {
                   steps {
                       bat 'mvn test -Dtest=PatientServiceTestAnalyse'
                   }
       }
         stage('Test ReactifService') {
                   steps {
                       bat 'mvn test -Dtest=ReactifServiceTestAnalyse'
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
         stage('Test TypeAnalyseService') {
                           steps {
                               bat 'mvn test -Dtest=TypeAnalyseServiceTest'
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