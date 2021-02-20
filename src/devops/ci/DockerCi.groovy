package devops.ci

import devops.ci.*

class DockerCi implements Serializable {

  private Object applications
  private String version
  private def pipeline

  DockerCi(def pipeline){
    this.pipeline = pipeline
  }

  public PrebuildScriptsDocker setApplications(Object applications){
    this.applications = applications
    return this
  }

  public PrebuildScriptsDocker setVersion(String version){
    this.version = version
    return this
  }

  public void buildProjects(){

    for(project in this.applications){
      this.pipeline.sh("docker build -t ${project.name}:${this.version} -f ${project.dockerfilePath} --no-cache .")
    }

  } 

  public void publishBaseImage(String dockerRepositoryUrl, String dockerRepositoryName, String dockerCredentialId){

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

}