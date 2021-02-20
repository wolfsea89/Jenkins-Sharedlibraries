package devops.ci

import devops.ci.*

class PprebuildScriptsDocker{

  private def pipeline

  PprebuildScriptsDocker(def pipeline){
    this.pipeline = pipeline
  }

  def setVersion(List applications){
    for(docker_project in applications){
      def file = readFile file: docker_project.dockerfilePath
      file = file.replaceAll('\\$\\{jenkins_include_version\\}', facts.version.semanticVersionWithBuildNumber)
      writeFile(file: docker_project.dockerfilePath, text: file)
      println("Set version in file ${docker_project.dockerfilePath}")
    }
  }
}