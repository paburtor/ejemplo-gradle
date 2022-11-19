def responseStatus = ''

pipeline {
    agent any
    stages {
        stage('INFO'){
            steps{
                echo 'Info...'
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
        stage('Build'){
            steps{
                echo 'Building: Workspace -> [${env.WORKSPACE}]'
                //slackSend color: "warning", message: "Building..."                                                
                sh 'chmod -R 777 $WORKSPACE'
                sh './gradlew build'

            }
            post {
                success {
                    echo 'Build Success'
                    //slackSend color: "good", message: "Build Success"
                }
                failure {
                    echo 'Build Failed'
                    //slackSend color: "danger", message: "Build Failed"
                }
            }
        }
/*         stage('Test'){
            steps{
                echo 'Testing...'
                //slackSend color: "warning", message: "Testing..."
                sh './mvnw test -e'
            }
            post {
                success {
                    echo 'Test Success'
                    //slackSend color: "good", message: "Test Success"
                }
                failure {
                    echo 'Test Failed'
                    //slackSend color: "danger", message: "Test Failed"
                }
            }
        }
 */  

//   stage('Package')
//   {
//             steps{
//                 echo 'Packaging...'
//                 //slackSend color: "warning", message: "Packaging..."
//                 sh './gradlew assemble'
//             }
//             post {
//                 success {
//                     echo 'Package Success'
//                     //slackSend color: "good", message: "Package Success"
//                 }
//                 failure {
//                     echo 'Package Failed'
//                     //slackSend color: "danger", message: "Package Failed"
//                 }
//             }
//         }

//         stage('Sonar'){
//             steps{
//                 echo 'Sonar...'
//                 withSonarQubeEnv('MySonarQubeServer') { // If you have configured more than one global server connection, you can specify its name
//                     sh './gradlew sonarqube -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build'
//                 }
//             }
//             post {
//                 success {
//                     echo 'SONAR Success'
//                     //slackSend color: "good", message: "Sonar Success. Branch: ${GIT_BRANCH}"
//                 }
//                 failure {
//                     echo 'SONAR Failed'
//                     //slackSend color: "danger", message: "Sonar Failed."
//                 }
//             }
//         }
        // stage('uploadNexus'){
        //     steps{
        //         echo 'Uploading to Nexus...'
        //         slackSend color: "warning", message: "Uploading to Nexus..."
        //         sh './mvnw clean install -e'
        //         nexusPublisher nexusInstanceId: 'nexus01', nexusRepositoryId: 'devops-usach-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: './build/DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]]
        //         // nexusPublisher nexusInstanceId: 'nexus01', nexusRepositoryId: 'devops-usach-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: './build/DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'fancyWidget', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]]
        //         // nexusPublisher nexusInstanceId: 'nexus01', nexusRepositoryId: 'devops-usach-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: '${WORKSPACE}/build/DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'fancyWidget', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]]
        //         // nexusPublisher nexusInstanceId: 'nexus', nexusRepositoryId: 'devops-usach-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: '${WORKSPACE}/build/DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'fancyWidget', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]]
        //         //, tagName: 'build-125'

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
        stage('Run Jar'){
            steps{
                echo 'Running Jar...'
                //slackSend color: "warning", message: "Running Jar..."
                sh 'nohup java -jar ./build/libs/DevOpsUsach2020-0.0.1.jar &'
                sleep(10)
                sh 'curl -I GET http://localhost:8081/rest/mscovid/test?msg=testing > response.txt'
            }
            post {
                success {
                    echo 'Run Success'
                    //slackSend color: "good", message: "Run Success"
                }
                failure {
                    echo 'Run Failed'
                    //slackSend color: "danger", message: "Run Failed"
                }
            }
        }
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

