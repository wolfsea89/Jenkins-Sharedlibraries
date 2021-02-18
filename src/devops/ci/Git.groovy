package devops.ci

class Git implements Serializable {

  private pipeline

  public Git(pipeline){
    this.pipeline = pipeline
  }

  def checkoutApplicationRepository(String branchName, String repositoryUrl, String gitCredentialId){
    this.pipeline.checkout([
      $class: 'GitSCM',
      branches: [
        [ name: branchName ]
      ],
      userRemoteConfigs: [
        [
          url: repositoryUrl,
          credentialsId: gitCredentialId
        ]
      ]
    ])
  }

  def checkoutJenkinsSripts(String tagetDirectory){
    this.pipeline.checkout([
      $class: 'GitSCM',
      branches: this.pipeline.scm.branches,
      doGenerateSubmoduleConfigurations: this.pipeline.scm.doGenerateSubmoduleConfigurations,
      userRemoteConfigs: this.pipeline.scm.userRemoteConfigs,
      extensions: [
        [
          $class: 'RelativeTargetDirectory', 
          relativeTargetDir: tagetDirectory
        ]
      ], 
    ])
  }
}