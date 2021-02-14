def buildProjects(Object projects, String version){

  for(project in projects){
    sh("docker build -t ${project.name}:${version} -f ${project.dockerfilePath} --no-cache  .")
  }

}

def publishBaseImage(Object projects, String version, String dockerRepositoryUrl, String dockerRepositoryName, String dockerCredentialId){

  docker.withRegistry(dockerRepositoryUrl, dockerCredentialId) {

    String projectName
    String repositoryName

    for(project in projects){
      projectName = project.name
      repositoryName = dockerRepositoryName.replace("\${projectName}", projectName)

      sh("docker tag $projectName:$version $repositoryName:$version")
      sh("docker push $repositoryName:$version")
    }
  }
}

def cleanAfterBuild(Object projects, String version){
    
    String projectName
    
    for(project in projects){
      projectName = project.name
      // Clean after build
      sh("docker rmi $repositoryName:$version")
      sh("docker rmi $projectName:$version")
    }
}