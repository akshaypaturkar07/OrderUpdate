node('master'){
        def dockerHome = tool 'mydocker'
        def mavenHome  = tool 'maven-3'
        env.PATH = "${dockerHome}/bin:${mavenHome}/bin:${env.PATH}"
        stage('ENV vars'){
        sh """
            java -version
            mvn -version
            docker -v
            sudo docker images
            sudo docker ps -a
        """

           }
           stage('Checkout Code'){
               git 'https://github.com/akshaypaturkar07/OrderUpdate.git'
           }
           stage('clean'){
                sh "mvn clean"
           }

           stage('compile code'){
                 sh "${mvnhome}/bin/mvn compile"
           }
           stage('Build Docker Image'){
                      sh "mvn package  -DskipTests"
           }
           stage('Flyway Clean'){
                sh "mvn flyway:clean"
           }

           stage('Flyway Migrate'){
                sh "mvn flyway:migrate"
           }
           stage('Run Docker Container'){
                     sh 'docker run --publish 8090:9090 --detach --name orderdetails orderdetails:latest'
              }
           stage("Unit Test"){
               sh "mvn test"
           }



           stage('Push Docker Image'){
                sh "${mvnhome}/bin/mvn deploy"
           }

           stage('Run Application'){

           }


   
   
   

}
