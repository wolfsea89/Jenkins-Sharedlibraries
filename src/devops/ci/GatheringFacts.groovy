package devops.ci

import devops.ci.*

class GatheringFacts implements Serializable {

    public String branchName
    public String branchNamePrefix
    public String repositoryUrl
    public String manualVersion
    public String jobName
    public String jobBuildNumber
    public String workspace
    public String gitCredentialId
    public String applicationJsonFile
    public String jenkinsScriptDirectory
    public Object applicationConfiguration
    
    // String nodeName
    // String artifactType 
    // String version
    // String versionWithBuildNumber

    GatheringFacts(){

        // setArifactType()
        // semanticVersion()
        // this.versionWithBuildNumber = this.version + "." + this.jobBuildNumber

        // if (this.branchNamePrefix == null) {
        //     throw new IllegalArgumentException('ERROR: Branch name not compatible with gitflow. Expects value (feature/*, epicfeature/*, develop, release, release/X.Y, release/X.Y.0, hotfix, hotfix/X.Y.Z, master)')
        // }
    }

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
        String branchNamePrefix = new CheckBranch(this.branchName).branchNamePrefix
        if(branchNamePrefix){
            this.branchNamePrefix = branchNamePrefix
        } else {
            throw new IllegalArgumentException('ERROR: Branch invalid with GitFlow')
        // }

        return this
    }

    public GatheringFacts setEnvironments(
        String jobName,
        String jobBuildNumber,
        String workspace,
        String jenkinsScriptDirectory,
        String gitCredentialId,
        String applicationJsonFile
    ){
        this.jobName = jobName
        this.jobBuildNumber = jobBuildNumber
        this.workspace = workspace
        this.jenkinsScriptDirectory = jenkinsScriptDirectory
        this.gitCredentialId = gitCredentialId
        this.applicationJsonFile = applicationJsonFile
        return this
    }

    public GatheringFacts setApplicationConfiguration(Object json){
        this.applicationConfiguration = json
    }

    

    // @NonCPS
    // public String semanticVersion(){

    //     def now = new Date()
    //     def version = "${now.format('yyyy.M.d', TimeZone.getTimeZone('UTC'))}"

    //     if (this.branchNamePrefix ==~ /release|hotfix|master/) {
            
    //         if(this.manualVersion){
    //             version = "${manualVersion}"
    //         } else {
    //             def substring = this.branchName.split('/')
    //             if(substring.last() ==~ /[0-9]+\.[0-9]+$/){
    //                 version = "${substring.last()}.0"
    //             } else if(substring.last() ==~ /[0-9]+\.[0-9]+\.[0-9]+$/){
    //                 version = "${substring.last()}"
    //             } else {
    //                 throw new IOException('ERROR: I can\'t set the version')
    //             }
    //         }
    //     }
    //     this.version = version
    // }

    // public void setApplicationConfiguration(Object applicationConfiguration){
    //     this.applicationConfiguration = applicationConfiguration
    // }
}
