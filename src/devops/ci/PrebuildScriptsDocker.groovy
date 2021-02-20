package devops.ci

import devops.ci.*

class PrebuildScriptsDocker{

  private def pipeline

  PrebuildScriptsDocker(def pipeline){
    this.pipeline = pipeline
  }

  def setVersionFiles(Object applications, String version){
    for(docker_project in applications){

      def file = readFile file: docker_project.dockerfilePath
      
      file = file.replaceAll('\\$\\{jenkins_include_version\\}', version)
      
      writeFile(file: docker_project.dockerfilePath, text: file)
      
      println("Set version in file ${docker_project.dockerfilePath}")
    }
  }
}