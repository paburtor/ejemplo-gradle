/*
	forma de invocación de método call:
	def ejecucion = load 'script.groovy'
	ejecucion.call()
*/

def call()
{	
stage('Build Gradle')
 {            
            echo 'Building: Workspace -> [${env.WORKSPACE}]'                
            sh 'chmod -R 777 $WORKSPACE'
            sh './gradlew build'
  }    
}

return this;
