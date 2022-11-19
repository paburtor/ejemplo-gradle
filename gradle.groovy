/*
	forma de invocación de método call:
	def ejecucion = load 'script.groovy'
	ejecucion.call()
*/

def call()
{  
  stage()
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
}

return this;
