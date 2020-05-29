node('master'){
   stage('ENV vars'){
       sh 'java -version'
       //sh 'mvn -version'

   }
   stage('Checkout Code'){
       sh 'git clone https://github.com/akshaypaturkar07/OrderUpdate.git'
   }
   dir('/var/jenkins_home/workspace/OrderDetails/OrderUpdate'){
         stage('clean'){
        sh 'git clean -df'
   }

   stage('compile code'){
         sh 'mvn clean'
   }

   stage("Unit Test"){
       sh 'mvn test'
   }

   stage('Build Docker Image'){
        sh 'mvn package'
   }

   stage('Push Docker Image'){
        sh 'mvn deploy'
   }

   stage('Run Application'){

   }
   
   }
   

}
