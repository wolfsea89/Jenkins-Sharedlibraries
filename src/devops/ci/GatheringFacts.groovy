package devops.ci

class GatheringFacts {

    private String branchName

    GatheringFacts(){ }

    public setParams(def params){
        
    }

}

    // private String branchNamePrefix
    // private String Version
    // private String repositoryUrl
    // private String jobName
    // private String buildNumber
    // private String artifactType
    // private String nodeName
    // private String workspace
    //     // 'jobName':        env.JOB_BASE_NAME,
    //     // 'jobBuildNumber': env.BUILD_NUMBER,
    //     // 'artifactType':   artifactType,
    
    // GatheringFacts(){
    //     // this.nodeName  = env.NODE_NAME
    //     // this.workspace = env.WORKSPACE
    // }
    // // GatheringFacts setRepositoryUrl(String repositoryUrl){
    // //     this.repositoryUrl = repositoryUrl
    // }

    // GatheringFacts setBranchFromForm(String branchName){
    //     this.branchName = branchName
    //     this.setBranchPrefix(branchName)
    //     this.setArifactType(this.branchNamePrefix)
    //     return this
    // }

    // void setBranchPrefix(String branchName){
    //     if (branchName ==~ /(.*\/feature)|(feature)\/.*$/) {
    //         this.branchNamePrefix = 'feature'
    //     } else if (branchName ==~ /(.*\/epicfeature)|(epicfeature)\/.*$/) {
    //         this.branchNamePrefix = 'epicfeature'
    //     } else if (branchName ==~ /(.*\/develop)|(develop)$/) {
    //         this.branchNamePrefix = 'develop'
    //     } else if (branchName ==~ /(.*\/release|release)(\/([0-9]+\.[0-9]+|[0-9]+\.[0-9]+\.0)|)$/) {
    //         this.branchNamePrefix = 'release'
    //     } else if (branchName ==~ /(.*\/hotfix|hotfix)(\/([0-9]+\.[0-9]+\.[0-9]+)|)$/) {
    //         this.branchNamePrefix = 'hotfix'
    //     } else if (branchName ==~ /(.*\/master)|(master)$/) {
    //         this.branchNamePrefix = 'master'
    //     } else {
    //         throw new IllegalArgumentException('ERROR: Branch name not compatible with gitflow. Expects value (feature/*, epicfeature/*, develop, release, release/X.Y, release/X.Y.0, hotfix, hotfix/X.Y.Z, master)')
    //     }
    // }

    // void setArifactType(String branchNamePrefix){
    //     this.artifactType = (branchNamePrefix ==~/^release|hotfix$/) ? 'release' : 'snapshot'
    // }
// }   