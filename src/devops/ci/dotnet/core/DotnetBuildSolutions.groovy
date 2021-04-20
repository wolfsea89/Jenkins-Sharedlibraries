package devops.ci.dotnet.core
import devops.ci.*

class DotnetBuildSolutions implements Serializable {

  private Object solutions
  private String parameters = "-configuration Release --verbosity normal"
  private def pipeline

  DotnetBuildSolutions(def pipeline){
    this.pipeline = pipeline
  }

  public DotnetBuildSolutions setSolutions(Object solutions){
    this.solutions = solutions
    return this
  }

  public DotnetBuildSolutions setParameters(String parameters){
    this.parameters = parameters
    return this
  }

  public void buildSolutions(){

    for(solution in this.solutions){

      if(this.pipeline.fileExists(solution.path)){

          def command = "dotnet build ${solution.path} "
          command += solution.buildParameters ? solution.buildParameters : this.parameters

          this.pipeline.println('$> ' + command)

          def solutionBuildStatus = this.pipeline.sh(
            script: command,
            returnStatus: true
          )

          if(solutionBuildStatus != 0){
            this.pipeline.error("FAILED: Build solution failed: ${solution.path}")
          } else {
            this.pipeline.println("SUCCESS: Build solution success: ${solution.path}")
          }

      } else {
          this.pipeline.error("FAILED: Build solution file not found: ${solution.path}")
      }
    }
  }
}