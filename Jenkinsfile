node('master'){
   def mvnhome = tool name:'maven-3' , type:'maven'
   def DOCKER_HOME = tool "docker"
   withEnv(['mvnhome = tool name:'maven-3' , type:'maven','DOCKER_HOME = tool "docker"','-v /var/run/docker.sock:/var/run/docker.sock']){
        stage('ENV vars'){
        sh """
            java -version
            ${mvnhome}/bin/mvn -version
            ls ${DOCKER_HOME}/bin/
            ${DOCKER_HOME}/bin/docker images
            ${DOCKER_HOME}/bin/docker ps -a
        """

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

   
   
   

}
