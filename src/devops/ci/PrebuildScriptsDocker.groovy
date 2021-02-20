package devops.ci

import devops.ci.*

class PrebuildScriptsDocker implements Serializable {

  private Object applications
  private String version
  private String adminCredentials

  private def pipeline

  PrebuildScriptsDocker(def pipeline){
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

  public PrebuildScriptsDocker setAdminsCredentials(String adminsCredentials){
    this.adminCredentials = adminsCredentials
    return this
  }


  public void execute(){
    
    for(docker_project in this.applications){

      def file = this.pipeline.readFile file: docker_project.dockerfilePath
      
      if(this.version){
        file = file.replaceAll('\\$\\{jenkins_include_version\\}', this.version)
      }
      if(this.adminCredentials){
        this.pipeline.println(this.adminCredentials)
        this.pipeline.withCredentials([
          this.pipeline.usernamePassword(credentialsId: this.adminCredentials, usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')
        ]) {
          file = file.replaceAll('\\$\\{jenkins_include_admin_username\\}',this.pipeline.USERNAME)
          file = file.replaceAll('\\$\\{jenkins_include_admin_password\\}',this.pipeline.PASSWORD)
        }
      }
      
      this.pipeline.writeFile(file: docker_project.dockerfilePath, text: file)
      this.pipeline.println("Set version in file ${docker_project.dockerfilePath}")
    }
  }
}