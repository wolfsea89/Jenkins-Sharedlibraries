def setVersion(Object facts){
  if(facts.applicationConfiguration.DOCKER_PROJECTS){
      for(docker_project in facts.applicationConfiguration.DOCKER_PROJECTS){
        def file = readFile file: docker_project.dockerfilePath
        file = file.replaceAll('\\$\\{jenkins_include_version\\}', facts.version.semanticVersionWithBuildNumber)
        writeFile(file: docker_project.dockerfilePath, text: file)
        println("Set version in file ${docker_project.dockerfilePath}")
      }
  } else {
    error("Error: Can't find project")
  }
}

def setCredentials(Object facts, String credentialId){
  withCredentials([usernamePassword(credentialsId: credentialId, usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
    if(facts.applicationConfiguration.DOCKER_PROJECTS){
        for(docker_project in facts.applicationConfiguration.DOCKER_PROJECTS){
          def file = readFile file: docker_project.dockerfilePath
          file = file.replaceAll('\\$\\{jenkins_include_admin_username\\}',"$USERNAME")
          file = file.replaceAll('\\$\\{jenkins_include_admin_password\\}',"$PASSWORD")
          writeFile(file: docker_project.dockerfilePath, text: file)
          println("Set Administrator credential in file ${docker_project.dockerfilePath}")
        }
    } else {
      error("Error: Can't find project")
    }
  }
}

def setJenkinsJobInfo(Object facts){
    if(facts.applicationConfiguration.DOCKER_PROJECTS){
        for(docker_project in facts.applicationConfiguration.DOCKER_PROJECTS){
          def file = readFile file: docker_project.dockerfilePath
          file = file.replaceAll('\\$\\{jenkins_include_jenkinsJob\\}',"Created by Jenkins job: ${facts.jobName}:#${facts.jobBuildNumber}")
          writeFile = writeFile(file: docker_project.dockerfilePath, text: file)
          println("Set jenkins information in file ${docker_project.dockerfilePath}")
        }
    } else {
      error("Error: Can't find project")
    }
}