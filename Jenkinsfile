def responseStatus = ''
def script

pipeline {
    agent any
    
     parameters 
    {        
        choice(name: "Tool", choices: ["gradle", "maven"], description: "Seleccione la herramienta de construcción")
        booleanParam(name: "upload", defaultValue: true, description: "¿Actualizar repositorio Nexus?")
    }
        
    stages {
        stage('INFO'){
            steps{
                echo 'Info...........'
                //cleanWs()
                //slackSend color: "warning", message: "INFO: Prueba Taller 3 - Modulo 4 Branch: ${GIT_BRANCH}"
            }
            post {
                success {
                    echo 'INFO Success'
                    //slackSend color: "good", message: "Info Success. commit ${GIT_COMMIT}"
                }
                failure {
                    echo 'INFO Failed'
                    //slackSend color: "danger", message: "Info Failed."
                }
            }
        }
        
        stage('Build')
        {
            steps
            {
                script
                {
                    if(params.Tool == 'gradle') 
                    {
                        echo 'Invocando script gradle.groovy'
                        script = load 'gradle.groovy'                                            
                        script.call()   
                    }
                    else
                    {
                        echo 'Invocando script maven.groovy'
                        script = load 'maven.groovy'                        
                        script.call()   
                    }
                }                                 
            }
            post {
                success {
                    echo 'Build Success ' + params.Tool
                    //slackSend color: "good", message: "Build Success"
                }
                failure {
                    echo 'Build Failed ' + params.Tool
                    //slackSend color: "danger", message: "Build Failed"
                }
            }
        }        
    }
}

