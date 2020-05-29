node('master'){
   def mvnhome = tool name:'maven-3' , type:'maven'
   stage('ENV vars'){
       sh 'java -version'
      sh "${mvnhome}/bin/mvn -version"

   }
   stage('Checkout Code'){
       git 'https://github.com/akshaypaturkar07/OrderUpdate.git'
   }
   dir('/var/jenkins_home/workspace/OrderDetails/OrderUpdate'){
         stage('clean'){
        sh 'git clean -df'
   }

   stage('compile code'){
         sh "${mvnhome}/bin/mvn clean"
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
   

}
