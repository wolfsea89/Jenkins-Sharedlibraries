package devops.ci.dotnet
import devops.ci.*

class DotnetAssemblyVersion implements Serializable {

  private Object applications
  private String version
  private String jenkinsInfo

  private def pipeline

  DotnetAssemblyVersion(def pipeline){
    this.pipeline = pipeline
  }

  public DotnetAssemblyVersion setApplications(Object applications){
    this.applications = applications
    return this
  }

  public DotnetAssemblyVersion setVersion(String version){
    this.version = version
    return this
  }

  public DotnetAssemblyVersion setJenkinsJobInfo(String jobName, String jobBuildNumber){
    this.jenkinsInfo = "Created by Jenkins job: " + jobName + ':#' + jobBuildNumber
    return this
  }

  public void execute(){

    def assemblyInfoFiles = [
      '**/AssemblyInfo.props',
      '**/AssemblyInfo.cs',
      '**/AssemblyInfo.vb',
    ]
    for(assemblyInfoFile in assemblyInfoFiles){
      for(version_file in this.pipeline.findFiles(glob: "${assemblyInfoFile}")){

        def file = this.pipeline.readFile file: "${version_file}"

        if(this.version){
          file = file.replaceAll('1.0.0.0', this.version)
        }

        if(this.jenkinsInfo){
          file = file.replaceAll('<Description><\\/Description>','<Description>' + this.jenkinsInfo + '</Description>')
        }

        this.pipeline.writeFile(file: "${version_file}", text: file, encoding: "UTF-8")
        this.pipeline.println("Set version in file ${version_file}")
      }
    }
  }
}