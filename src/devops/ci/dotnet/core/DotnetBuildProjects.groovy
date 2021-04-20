package devops.ci.dotnet.core
import devops.ci.*

class DotnetBuildProjects implements Serializable {

  private Object projects
  private String binaryDirectory = 'b'
  private String publishDirectory = 'p'
  private Object runtimes = '[ "linux-x64" ]'
  private String parameters = "-configuration Release --verbosity normal"
  private def pipeline

  DotnetBuildProjects(def pipeline){
    this.pipeline = pipeline
  }

  public DotnetBuildProjects setProjects(Object projects){
    this.projects = projects
    return this
  }

  public DotnetBuildProjects setBinaryDirectory(String binaryDirectory){
    this.binaryDirectory = binaryDirectory
    return this
  }

  public DotnetBuildProjects setPublishDirectory(String publishDirectory){
    this.publishDirectory = publishDirectory
    return this
  }

  public DotnetBuildProjects setRuntimes(Object runtimes){
    this.runtimes = runtimes
    return this
  }

  public DotnetBuildProjects setParameters(String parameters){
    this.parameters = parameters
    return this
  }

  public void buildProjects(){

    for(project in this.projects){

      if(this.pipeline.fileExists(project.path)){

        def command = "dotnet build ${project.path} "
        command += project.buildParameters ? project.buildParameters : this.parameters
        command += " --output ${ this.binaryDirectory}"

        def buildRuntimes = project.runtimes ? project.runtimes : this.runtimes
        for( buildRuntime in buildRuntimes){

          command += " --runtime " + buildRuntime

          this.pipeline.println('$> ' + command)

          def projetcBuildStatus = this.pipeline.sh(
            script: command,
            returnStatus: true
          )

          if(projetcBuildStatus != 0){
            this.pipeline.error("FAILED: Build project failed: ${project.path}")
          } else {
            this.pipeline.println("SUCCESS: Build project success: ${project.path}")
          }

        }

      } else {
          this.pipeline.error("FAILED: Build project file not found: ${project.path}")
      }
    }
  }
}