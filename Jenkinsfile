def responseStatus = ''

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
        
        stage('Build Gradle')
        {
            steps
            {
                if(params.Tool == 'gradle') {
                    echo 'Build Gradle'
                }
                else
                {
                    echo 'Build Maven'
                }
            }
            post {
                success {
                    echo 'Build Success $params.Tool'
                    //slackSend color: "good", message: "Build Success"
                }
                failure {
                    echo 'Build Failed $params.Tool'
                    //slackSend color: "danger", message: "Build Failed"
                }
            }
        }
        
        stage('Build Gradle')
        {
            when { expression { return params.Tool == 'gradle'} }
            steps 
            { 
                echo 'Building: Workspace -> [${env.WORKSPACE}]'                
                sh 'chmod -R 777 $WORKSPACE'
                sh './gradlew build'
            }                                               
       
            post {
                success {
                    echo 'Build Success Gradle'
                    //slackSend color: "good", message: "Build Success"
                }
                failure {
                    echo 'Build Failed Gradle'
                    //slackSend color: "danger", message: "Build Failed"
                }
            }
        }

        stage('Build Maven')
        {
            when { expression { return params.Tool == 'maven'} }
            steps 
            { 
                echo 'Building: Workspace -> [${env.WORKSPACE}]'                
                sh 'chmod -R 777 $WORKSPACE'
                sh './gradlew build'
            }                                               
       
            post {
                success {
                    echo 'Build Success Maven'
                    //slackSend color: "good", message: "Build Success"
                }
                failure {
                    echo 'Build Failed Maven'
                    //slackSend color: "danger", message: "Build Failed"
                }
            }
        }
    
        stage('Sonar'){
            steps{
                echo 'Sonar...'
                withSonarQubeEnv('MySonarQubeServer') { // If you have configured more than one global server connection, you can specify its name
                    sh './mvnw clean package -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build'
                }
            }
            post {
                success {
                    echo 'SONAR Success'
                    //slackSend color: "good", message: "Sonar Success. Branch: ${GIT_BRANCH}"
                }
                failure {
                    echo 'SONAR Failed'
                    //slackSend color: "danger", message: "Sonar Failed."
                }
            }
        }

        stage('Run & Test'){
            steps{
                //No es necesario hacer Kill del proceso
                echo 'Running Jar...'
                //slackSend color: "warning", message: "Running Jar..."
                //sh 'java -jar ./build/libs/DevOpsUsach2020-0.0.1.jar &'
                sh './gradlew bootRun&'
                sleep(60)
                sh 'curl -X GET http://localhost:8081/rest/mscovid/test?msg=testing'
            }
            post {
                success {
                    echo 'Run & Test Success'
                    //slackSend color: "good", message: "Run Success"
                }
                failure {
                    echo 'Run & Test Failed'
                    //slackSend color: "danger", message: "Run Failed"
                }
            }
        }

        stage('uploadNexus'){
            steps{
                echo 'Uploading to Nexus...'
                //slackSend color: "warning", message: "Uploading to Nexus..."
                sh './mvnw clean install -e'
                nexusPublisher nexusInstanceId: 'nexusserverid', nexusRepositoryId: 'devops-usach-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: './build/DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]]
            }
            post {
                success {
                    echo 'Upload Success'
                    //slackSend color: "good", message: "Upload Success"
                }
                failure {
                    echo 'Upload Failed'
                    //slackSend color: "danger", message: "Upload Failed"
                }
            }
         }

        // stage('downloadNexusArtefact'){
        //     steps{
        //         // cleanWs()    
        //         echo 'Download...'
        //         slackSend color: "warning", message: "Download..."
        //         sh 'curl -X GET -u admin:1qazxsw2 https://nexus.danilovidalm.com/repository/devops-usach-nexus/com/devopsusach2020/DevOpsUsach2020/0.0.1/DevOpsUsach2020-0.0.1.jar -O'
        //         // sh 'curl -X GET -u admin:admin https://nexus.danilovidalm.com/repository/com/devopsusach2020/DevOpsUsach2020/0.0.1/DevOpsUsach2020-0.0.1.jar -O'
        //     }
        //     post {
        //         success {
        //             echo 'Download Success'
        //             slackSend color: "good", message: "Deploy Success"
        //         }
        //         failure {
        //             echo 'Download Failed'
        //             slackSend color: "danger", message: "Deploy Failed"
        //         }
        //     }
        // }
        // stage('sleep'){
        //     steps{
        //         echo 'Sleeping...'
        //         sleep time: 20, unit: 'SECONDS'
        //         sh 'ps -ef | grep java'
        //     }
        // }
        //updaload nexus jar
        // stage('Upload jar to nexus')
        // {
        //     steps{
        //         echo 'Uploading to Nexus...'
        //         slackSend color: "warning", message: "Uploading to Nexus..."
        //         // sh 'cp ./DevOpsUsach2020-0.0.1.jar DevOpsUsach2020-1.0.0.jar'
        //         nexusPublisher nexusInstanceId: 'nexus01', nexusRepositoryId: 'devops-usach-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: './DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '1.0.0']]]
        //     }
        //     post {
        //         success {
        //             echo 'Upload Success'
        //             slackSend color: "good", message: "Upload Success"
        //         }
        //         failure {
        //             echo 'Upload Failed'
        //             slackSend color: "danger", message: "Upload Failed"
        //         }
        //     }
        // }
        //download jar
        //run jar
        //curl localhost:8081
        // stage('Stop Jar'){
        //     steps{
        //         echo 'Stopping Jar...'
        //         slackSend color: "warning", message: "Stopping Jar..."
        //         sh 
        //     }
        //     post {
        //         success {
        //             echo 'Stop Success'
        //             slackSend color: "good", message: "Stop Success"
        //         }
        //         failure {
        //             echo 'Stop Failed'
        //             slackSend color: "danger", message: "Stop Failed"
        //         }
        //     }
        // }
    }
}

