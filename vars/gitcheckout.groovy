def application(String branchName, String repositoryUrl, String gitCredentialId){
  checkout([
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

def jenkinsSripts(String tagetDirectory){
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