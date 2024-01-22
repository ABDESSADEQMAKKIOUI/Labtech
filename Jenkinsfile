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
                bat 'mvn test -Dtest=AnalyseServiceTest'
            }
        }
        stage('Test EchantillonService') {
            steps {
                bat 'mvn test -Dtest=EchantillonServiceTest'
            }
        }

        stage('Test EnormService') {
            steps {
                bat 'mvn test -Dtest=EnormServiceTest'
            }
        }

        stage('Test FournisseurService') {
            steps {
                bat 'mvn test -Dtest=FournisseurServiceTest'
            }
        }

        stage('Test MaterielService') {
            steps {
                bat 'mvn test -Dtest=MaterielServiceTest'
            }
        }

        stage('Test PatientService') {
            steps {
                bat 'mvn test -Dtest=PatientServiceTest'
            }
        }

        stage('Test ReactifService') {
            steps {
                bat 'mvn test -Dtest=ReactifServiceTest'
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
