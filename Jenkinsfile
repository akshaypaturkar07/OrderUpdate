node('master'){
   def mvnhome = tool name:'maven-3' , type:'maven'
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
       sh "${mvnhome}/bin/mvn verify"
   }

   stage('Run Docker Container'){
          sh 'docker run --publish 8090:9090 --detach --name orderdetails orderdetails:latest'
      }

   stage('Flyway Clean'){
        sh "${mvnhome}/bin/mvn flyway:clean"
   }

   stage('Flyway Migrate'){
        sh "${mvnhome}/bin/mvn flyway:migrate"
   }
   stage("Unit Test"){
       sh "${mvnhome}/bin/mvn test"
   }

   stage('Build Docker Image'){
        sh "${mvnhome}/bin/mvn package"
   }

   stage('Push Docker Image'){
        sh "${mvnhome}/bin/mvn deploy"
   }

   stage('Run Application'){

   }
   
   
   

}