package devops.ci

import devops.ci.*

class DockerPublish implements Serializable {

  private Object applications
  private String version
  private def pipeline

  DockerPublish(def pipeline){
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

  public void publish(String dockerRepositoryUrl, String dockerRepositoryName, String dockerCredentialId){

    this.pipeline.docker.withRegistry(dockerRepositoryUrl, dockerCredentialId) {

      String projectName
      // String repositoryName

      for(project in this.applications){
        projectName = project.name
        // repositoryName = dockerRepositoryName.replace("\${projectName}", projectName)

        this.pipeline.sh("docker tag $projectName:$version $dockerRepositoryName:$version")
        this.pipeline.sh("docker push $dockerRepositoryName:$version")

      }
    }
  }

  public void clean(String dockerRepositoryName = ""){

    String projectName
    // String repositoryName

    for(project in this.applications){
      projectName = project.name
      // repositoryName = dockerRepositoryName.replace("\${projectName}", projectName)
      if(dockerRepositoryName){
        this.pipeline.sh("docker rmi $dockerRepositoryName:$version")
      } else {
        this.pipeline.sh("docker rmi $projectName:$version")
      }
    }
  }
}