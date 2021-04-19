package devops.ci

import devops.ci.*

class PrebuildScriptsDotnetCore implements Serializable {

  private Object applications
  private String version
  private String adminCredentials
  private String jenkinsInfo

  private def pipeline

  PrebuildScriptsDotnetCore(def pipeline){
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

  public PrebuildScriptsDocker setJenkinsJobInfo(String jobName, String jobBuildNumber){
    this.jenkinsInfo = "Created by Jenkins job: " + jobName + ':#' + jobBuildNumber
  }

  public void execute(){

    for(file in this.findFiles(glob: '**/version.props')){

      sh.println(file)

      // def file = this.pipeline.readFile file: docker_project.dockerfilePath

      // if(this.version){
      //   file = file.replaceAll('\\$\\{jenkins_include_version\\}', this.version)
      // }

      // if(this.jenkinsInfo){
      //   file = file.replaceAll('\\$\\{jenkins_include_jenkinsJob\\}',this.jenkinsInfo)
      // }

      // this.pipeline.writeFile(file: docker_project.dockerfilePath, text: file)
      // this.pipeline.println("Set version in file ${docker_project.dockerfilePath}")
    }
  }
}