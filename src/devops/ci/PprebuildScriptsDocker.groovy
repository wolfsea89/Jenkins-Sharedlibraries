package devops.ci

import devops.ci.*

class PprebuildScriptsDocker{

  private def pipeline

  PprebuildScriptsDocker(def pipeline){
    this.pipeline = pipeline
  }

  def setVersion(List applications, String version){
    for(docker_project in applications){

      def file = readFile file: docker_project.dockerfilePath
      
      file = file.replaceAll('\\$\\{jenkins_include_version\\}', version)
      
      writeFile(file: docker_project.dockerfilePath, text: file)
      
      println("Set version in file ${docker_project.dockerfilePath}")
    }
  }
}