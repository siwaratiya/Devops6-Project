pipeline {
    agent any

        environment {
            NEXUS_VERSION = "nexus3"
            NEXUS_PROTOCOL = "http"
            NEXUS_URL = "192.168.33.10:8081"
            NEXUS_REPOSITORY = "maven-releases"
            NEXUS_CREDENTIAL_ID = "nexus-credentials"
            }
    
    stages {




          stage('Nettoyage et compilation Maven') {
                    steps {
                         // Cette étape va nettoyer et compiler le projet avec Maven
                         sh 'mvn clean compile'
                     }
                 }


          stage('Compile with Maven') {
               steps {
                          script {
                                 sh 'mvn clean install'
                                 }
                     }


            }


            stage('Mockito test') {
                                 steps {
                                     script {
                                         sh 'mvn test'
                                     }
                                 }
                   post {
                always {
                    junit '**/target/surefire-reports/TEST*.xml'
                }
            }
                           }

          stage('SonarQube analyse') {
                                 steps {
                                   script {
                                    jacoco()
                                 withSonarQubeEnv(installationName: 'sq') {
                                     sh 'mvn sonar:sonar'
                                                                           }
                                          }
                                        }
                                       }


          /* stage("publish to nexus") {
                                   steps {
                                       script {
                                           artifactPath = "target/gestion-station-ski-1.0.jar";
                                                           nexusArtifactUploader(
                                                                   nexusVersion: NEXUS_VERSION,
                                                                   protocol: NEXUS_PROTOCOL,
                                                                   nexusUrl: NEXUS_URL,
                                                                   groupId: 'tn.esprit',
                                                                   version: '1.0',
                                                                   repository: NEXUS_REPOSITORY,
                                                                   credentialsId: NEXUS_CREDENTIAL_ID,
                                                                   artifacts: [
                                                                           // Artifact generated such as .jar, .ear and .war files.
                                                                           [artifactId: 'gestion-station-ski',
                                                                            classifier: '',
                                                                            file      : artifactPath,
                                                                            type      : 'jar']
                                                                   ]
                                                           );

                                                       }
                                                   }
                                               }*/


stage('Build Docker Image') {
                     steps {
                         echo 'Construction de l\'image Docker'
                         script {
                             sh 'docker build -t siwaratiya/gestion-station-ski-1.0 .'
                         }
                     }
                 }
                stage('Push vers DockerHub') {
                 steps {
                    script {
                        sh 'docker login -u siwaratiya -p dockerdocker'
                        sh 'docker push siwaratiya/gestion-station-ski-1.0'
                  }
              }
          }
          stage('Docker Compose') {
              steps {
                   script {
                        sh 'docker compose down'
                        sh 'docker compose up -d'
                  }
               }
           }



       /*   stage('mail') {
              steps {
                   script {

            always {
                emailext (
                    subject:"Pipeline Status: ${currentBuild.result}",
                    body: '''<html>
                                       <body>
                                          <p> Build Status: ${currentBuild.result} </p>
                                          <p> Build Number: ${currentBuild.number} </p>
                                          <p>Check the: <a href="${env.BUILD_URL}">cosol output </a>.</p>
                                       </body>

                                   </html>''',
                       to: 'siwar.atiya@esprit.tn',
                       from: 'jenkins@example.com',
                       replyTo:'jenkins@example.com',
                       mimeType : 'text/html'
                )

            }

                  }
               }
           }*/








    }
         post {
   always {
                emailext (
                    subject:"Pipeline Status: ${BUILD_NUMBER}",
                    body: '''<html>
                                       <body>
                                          <p> Build Status: ${BUILD_STATUS} </p>
                                          <p> Build Number: ${BUILD_NUMBER} </p>
                                          <p>Check the: <a href="${BUILD_URL}">cosol output </a>.</p>
                                       </body>

                                   </html>''',
                       to: 'siwar.atiya@esprit.tn',
                       from: 'jenkins@example.com',
                       replyTo:'jenkins@example.com',
                       mimeType : 'text/html'
                )

            }

}
}