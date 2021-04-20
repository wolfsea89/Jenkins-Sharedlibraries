package devops.ci

import devops.ci.*

class GatheringFacts implements Serializable {

    public String branchName
    public String branchNamePrefix
    public String repositoryUrl
    public String manualVersion
    public String version
    public String jobName
    public String jobBuildNumber
    public String workspace
    public String gitCredentialId
    public String applicationJsonFile
    public String versionWithBuildNumber
    public String jenkinsScriptDirectory
    public Object applicationConfiguration
    public Object publishRepositories
    public String artifactType
    public String baseImagesAdminCredentialsInService
    public String binaryDirectory
    public String publishDirectory
    public String dotnetCoreRuntimes

    GatheringFacts(){ }

    public GatheringFacts setParametersFromForm (
        String branchName,
        String repositoryUrl,
        String manualVersion
    ){
        this.branchName = branchName
        this.repositoryUrl = repositoryUrl
        if(manualVersion == "" || manualVersion ==~ /[0-9]+\.[0-9]+\.[0-9]+$/){
            this.manualVersion = manualVersion
        } else {
            throw new IllegalArgumentException('ERROR: Invalid set manual version')
        }

        // Set branchNamePrefix
        String branchNamePrefix = new CheckBranch(this.branchName).branchNamePrefix
        if(branchNamePrefix){
            this.branchNamePrefix = branchNamePrefix
        } else {
            throw new IllegalArgumentException('ERROR: Branch invalid with GitFlow')
        }

        // Set version
        this.version = new Version(this.branchNamePrefix, this.branchName, this.manualVersion).version

        // Set artifact type
        this.artifactType = (this.branchNamePrefix  ==~ /release|hotfix/) ? "release" : "snapshot"

        return this
    }

    public GatheringFacts setEnvironments(
        String jobName,
        String jobBuildNumber,
        String workspace,
        String jenkinsScriptDirectory,
        String gitCredentialId,
        String applicationJsonFile,
        String baseImagesAdminCredentialsInService,
        Object publishRepositories,
        String binaryDirectory,
        String publishDirectory,
        String dotnetCoreRuntimes
    ){
        this.jobName = jobName
        this.jobBuildNumber = jobBuildNumber
        this.workspace = workspace
        this.jenkinsScriptDirectory = jenkinsScriptDirectory
        this.gitCredentialId = gitCredentialId
        this.applicationJsonFile = applicationJsonFile
        this.baseImagesAdminCredentialsInService = baseImagesAdminCredentialsInService
        this.publishRepositories = publishRepositories
        this.binaryDirectory = binaryDirectory
        this.publishDirectory = publishDirectory
        this.dotnetCoreRuntimes = dotnetCoreRuntimes
        return this
    }

    public GatheringFacts setApplicationConfiguration(Object json){
        this.applicationConfiguration = json
        return this
    }

    public GatheringFacts createVersionWithBuildNumber(){
        this.versionWithBuildNumber = this.version + '.' + this.jobBuildNumber
    }
}
