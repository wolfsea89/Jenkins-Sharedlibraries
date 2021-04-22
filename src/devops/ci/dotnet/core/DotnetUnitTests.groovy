package devops.ci.dotnet.core
import devops.ci.*

class DotnetUnitTests implements Serializable {

  private Object projects
  private Object solutions
  private String resultsDirectory = "TestResults"
  private String parameters = '--verbosity normal --logger "trx" --collect "Code Coverage"'

  private def pipeline

  DotnetUnitTests(def pipeline){
    this.pipeline = pipeline
  }

  public DotnetUnitTests setProjects(Object projects){
    this.projects = projects
    return this
  }

  public DotnetBuildSolutions setSolutions(Object solutions){
    this.solutions = solutions
    return this
  }

  public DotnetUnitTests setResultsDirectory(String resultsDirectory){
    this.resultsDirectory = resultsDirectory
    return this
  }

  public DotnetUnitTests setParameters(String parameters){
    this.parameters = parameters
    return this
  }

  public void runUnitTest(){
    def unitTestProjects = this.solutions ? this.solutions : this.projects

     for(unitTestProject in unitTestProjects){

      if(this.pipeline.fileExists(unitTestProject.path)){

          def command = "dotnet test"
          command += " --results-directory " + this.resultsDirectory + " "
          command += unitTestProject.buildParameters ? unitTestProject.buildParameters : this.parameters
          command += unitTestProject.path


          this.pipeline.println('$> ' + command)

          def unitTestProjectUnitTestdStatus = this.pipeline.sh(
            script: command,
            returnStatus: true
          )

          try{
            this.pipeline.mstest testResultsFile:"${this.resultsDirectory}/*.trx", keepLongStdio: true
          } catch (Exception e){
            this.pipeline.unstable("WARNING: No Unit test: ${unitTestProject.path}")
          }

          if(unitTestProjectUnitTestdStatus != 0){
            this.pipeline.error("FAILED: Unit test failed: ${unitTestProject.path}")
          } else {
            this.pipeline.println("SUCCESS: Unit test success: ${unitTestProject.path}")
          }

      } else {
          this.pipeline.error("FAILED: Unit test file not found: ${unitTestProject.path}")
      }
    }
  }
}