package devops.ci.docker

import devops.ci.*

class DockerBuild implements Serializable {

  private Object applications
  private String version
  private def pipeline

  DockerBuild(def pipeline){
    this.pipeline = pipeline
  }

  public DockerBuild setApplications(Object applications){
    this.applications = applications
    return this
  }

  public DockerBuild setVersion(String version){
    this.version = version
    return this
  }

  public void buildProjects(){

    for(project in this.applications){
      this.pipeline.sh("docker build -t ${project.name}:${this.version} -f ${project.dockerfilePath} --no-cache .")
    }

  }

}