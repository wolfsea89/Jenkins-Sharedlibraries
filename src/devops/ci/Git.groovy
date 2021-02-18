package devops.ci


class Git {

  private pipeline

  public Git(pipeline){
    this.pipeline = pipeline
  }

  def checkoutApplicationRepository(String branchName, String repositoryUrl, String gitCredentialId){
    checkout([
      $class: 'GitSCM',
      branches: branchName,
      userRemoteConfigs: [
        [
          url: repositoryUrl,
          credentialsId: gitCredentialId
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