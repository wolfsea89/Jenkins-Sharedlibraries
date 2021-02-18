package devops.ci

class Git implements Serialized {

  private pipeline

  public Git(pipeline){
    this.pipeline = pipeline
  }

  def checkoutApplicationRepository(String branchName, String repositoryUrl){
    checkout([
      $class: 'GitSCM',
      branches: branchName,
      userRemoteConfigs: [
        [
          url: repositoryUrl
        ]
      ]
    ])
  }

  def checkoutJenkinsSripts(String tagetDirectory){
    checkout([
      $class: 'GitSCM',
      branches: scm.branches,
      doGenerateSubmoduleConfigurations: scm.doGenerateSubmoduleConfigurations,
      userRemoteConfigs: scm.userRemoteConfigs,
      extensions: [
        [
          $class: 'RelativeTargetDirectory', 
          relativeTargetDir: tagetDirectory
        ]
      ], 
    ])
  }
}