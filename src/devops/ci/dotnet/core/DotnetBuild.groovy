package devops.ci.dotnet.core
import devops.ci.*

import java.nio.file.Files

class DotnetBuild implements Serializable {

  private Object solutions
  private String parameters
  private def pipeline

  DotnetBuild(def pipeline){
    this.pipeline = pipeline
  }

  public DotnetBuild setSolutions(Object solutions){
    this.solutions = solutions
    return this
  }

  public DotnetBuild setParameters(String parameters){
    this.parameters = parameters
    return this
  }

  public void buildSolutions(){

    for(solution in solutions){

        if(this.pipeline.fileExists(solution.path)){
            this.pipeline.println("wsk ok")
        } else {
            this.pipeline.println("wsk not ok")
        }

        this.pipeline.println("Set version in file ${solution}")
      }
  }
}