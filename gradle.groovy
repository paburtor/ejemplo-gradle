/*
	forma de invocación de método call:
	def ejecucion = load 'script.groovy'
	ejecucion.call()
*/

def call()
{  
  stage('Build Gradle')
  {            
        steps 
        { 
            echo 'Building: Workspace -> [${env.WORKSPACE}]'                
            sh 'chmod -R 777 $WORKSPACE'
            sh './gradlew build'
        }                                               

        post 
        {
            success 
            {
                echo 'Build Success Gradle'
                //slackSend color: "good", message: "Build Success"
            }
            failure 
            {
                echo 'Build Failed Gradle'
                //slackSend color: "danger", message: "Build Failed"
            }
        }
    }
    
        stage('Sonar')
        {
            steps
            {
                echo 'Sonar...'
                withSonarQubeEnv('MySonarQubeServer') 
                { // If you have configured more than one global server connection, you can specify its name
                    sh './mvnw clean package -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build'
                }
            }
            post 
            {
                success 
                {
                    echo 'SONAR Success'
                    //slackSend color: "good", message: "Sonar Success. Branch: ${GIT_BRANCH}"
                }
                failure 
                {
                    echo 'SONAR Failed'
                    //slackSend color: "danger", message: "Sonar Failed."
                }
            }
        }

        stage('Run & Test')
        {
            steps
          {
                //No es necesario hacer Kill del proceso
                echo 'Running Jar...'
                //slackSend color: "warning", message: "Running Jar..."
                //sh 'java -jar ./build/libs/DevOpsUsach2020-0.0.1.jar &'
                sh './gradlew bootRun&'
                sleep(60)
                sh 'curl -X GET http://localhost:8081/rest/mscovid/test?msg=testing'
            }
            post 
            {
                success 
                {
                    echo 'Run & Test Success'
                    //slackSend color: "good", message: "Run Success"
                }
                failure 
                {
                    echo 'Run & Test Failed'
                    //slackSend color: "danger", message: "Run Failed"
                }
            }
        }

        stage('uploadNexus')
        {
            steps{
                echo 'Uploading to Nexus...'
                //slackSend color: "warning", message: "Uploading to Nexus..."
                sh './mvnw clean install -e'
                nexusPublisher nexusInstanceId: 'nexusserverid', nexusRepositoryId: 'devops-usach-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: './build/DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]]
            }
            post 
            {
                success 
                {
                    echo 'Upload Success'
                    //slackSend color: "good", message: "Upload Success"
                }
                failure 
                {
                    echo 'Upload Failed'
                    //slackSend color: "danger", message: "Upload Failed"
                }
            }
         }
}

return this;
