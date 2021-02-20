package devops.ci

import devops.ci.*

class PrebuildScriptsDocker implements Serializable {

  private Object applications
  private String version

  private def pipeline

  PrebuildScriptsDocker(def pipeline){
    this.pipeline = pipeline
  }

  public setApplications(Object applications){
    this.applications = applications
  }

  public setVersion(String version){
    this.version = version
  }


  public void execute(){
    
    for(docker_project in this.applications){

      def file = this.pipeline.readFile file: docker_project.dockerfilePath
      
      if(this.version){
        file = file.replaceAll('\\$\\{jenkins_include_version\\}', this.version)
      }
      
      this.pipeline.writeFile(file: docker_project.dockerfilePath, text: file)
      this.pipeline.println("Set version in file ${docker_project.dockerfilePath}")
    }
  }
}