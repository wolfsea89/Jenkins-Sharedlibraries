package devops.ci

import devops.ci.*

class PrebuildScriptsDocker implements Serializable {

  private def applications
  private String Version

  private def pipeline

  PrebuildScriptsDocker(def pipeline){
    this.pipeline = pipeline
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