node('master'){
   def mvnhome = tool name:'maven-3' , type:'maven'
   def DOCKER_HOME = tool "docker"
   stage('ENV vars'){
      sh 'java -version'
      sh "${mvnhome}/bin/mvn -version"


   }
   stage('Checkout Code'){
       git 'https://github.com/akshaypaturkar07/OrderUpdate.git'
   }
   stage('clean'){
        sh "${mvnhome}/bin/mvn clean"
   }

   stage('compile code'){
         sh "${mvnhome}/bin/mvn compile"
   }
   stage('Build Docker Image'){
              sh "${mvnhome}/bin/mvn package  -DskipTests"
   }
   stage('Flyway Clean'){
        sh "${mvnhome}/bin/mvn flyway:clean"
   }

   stage('Flyway Migrate'){
        sh "${mvnhome}/bin/mvn flyway:migrate"
   }
   stage('Run Docker Container'){
             sh '${DOCKER_HOME}/bin/docker run --publish 8090:9090 --detach --name orderdetails orderdetails:latest'
      }
   stage("Unit Test"){
       sh "${mvnhome}/bin/mvn test"
   }



   stage('Push Docker Image'){
        sh "${mvnhome}/bin/mvn deploy"
   }

   stage('Run Application'){

   }
   
   
   

}
