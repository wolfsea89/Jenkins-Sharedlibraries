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

      for(project in this.applications){
        projectName = project.name

        this.pipeline.sh("docker tag $projectName:$version $dockerRepositoryName:$version")
        this.pipeline.sh("docker push $dockerRepositoryName:$version")

      }
    }
  }

  public void clean(String dockerRepositoryName = ""){

    String projectName

    for(project in this.applications){
      projectName = project.name
      if(dockerRepositoryName){
        this.pipeline.sh(script: "docker rmi $dockerRepositoryName:$version", returnStdout: true)
      } else {
        this.pipeline.sh(script: "docker rmi $projectName:$version", returnStdout: true)
      }
    }
  }
}