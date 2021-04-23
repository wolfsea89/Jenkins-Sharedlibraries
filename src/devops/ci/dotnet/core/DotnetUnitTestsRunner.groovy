package devops.ci.dotnet.core
import devops.ci.*

class DotnetUnitTestsRunner implements Serializable {

  private Object projects
  private Object solutions
  private String resultsDirectory = "TestResults"
  private String parameters = '--verbosity normal --logger "trx" --collect "Code Coverage"'

  private def pipeline

  DotnetUnitTestsRunner(def pipeline){
    this.pipeline = pipeline
  }

  public DotnetUnitTestsRunner setProjects(Object projects){
    this.projects = projects
    return this
  }

  public DotnetBuildSolutions setSolutions(Object solutions){
    this.solutions = solutions
    return this
  }

  public DotnetUnitTestsRunner setResultsDirectory(String resultsDirectory){
    this.resultsDirectory = resultsDirectory
    return this
  }

  public DotnetUnitTestsRunner setParameters(String parameters){
    this.parameters = parameters
    return this
  }

  public String runUnitTest(){
    def unitTestProjects = this.solutions ? this.solutions : this.projects

      def command = ""

     for(unitTestProject in unitTestProjects){

          // test *.csproj or *.sln exist
          command += """
          if test -f \"${unitTestProject.path}\"; then \\
            dotnet test --results-directory ${this.resultsDirectory} \\
              ${unitTestProject.buildParameters ? unitTestProject.buildParameters : this.parameters} \\
              ${unitTestProject.path} ;\\
            if [ \$? -eq 0 ]; then \\
              echo \"FAILED: Unit test failed: ${unitTestProject.path}\" ;\\
              exit 1 ; \\
            else
              echo \"SUCCESS: Unit test success: ${unitTestProject.path}\" ;\\
            fi \\
          else \\
            echo \"FAILED: Unit test file not found: ${unitTestProject.path}\" ;\\
            exit 1 ;\\
          fi  && """
    }
    command += "echo \"OK\""
    this.pipeline.println('$> ' + command)
    def unitTestProjectUnitTestdStatus = this.pipeline.sh(
            script: command,
            returnStatus: true
    )
      return command
    }
  }
}