package devops.ci

import devops.ci.*

class DotnetAssembyVersion implements Serializable {

  private Object applications
  private String version
  private String adminCredentials
  private String jenkinsInfo

  private def pipeline

  DotnetAssembyVersion(def pipeline){
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

    for(version_file in this.pipeline.findFiles(glob: '**/AssemblyInfo.props')){

      def file = this.pipeline.readFile file: "${version_file}"

      if(this.version){
        file = file.replaceAll('1.0.0.0', this.version)
      }

      if(this.jenkinsInfo){
        file = file.replaceAll('<Description><\\/Description>','<Description>' + this.jenkinsInfo + '</Description>')
      }

      this.pipeline.writeFile(file: "${version_file}", text: file)
      this.pipeline.println("Set version in file ${version_file}")
    }
  }
}