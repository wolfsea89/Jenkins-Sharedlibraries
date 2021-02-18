package devops.ci

class GatheringFacts {

    String branchName
    String repositoryUrl
    String manualVersion
    String jobName
    String jobBuildNumber
    String nodeName
    String workspace
    String branchNamePrefix
    String artifactType 

    GatheringFacts(def params, def env){
        this.branchName = params.branch
        this.repositoryUrl = params.repositoryUrl
        this.manualVersion = params.manualVersion
        this.jobName = env.JOB_BASE_NAME
        this.jobBuildNumber = env.BUILD_NUMBER
        this.nodeName = env.NODE_NAME
        this.workspace = env.WORKSPACE
        isFeature()
        isEpicFeature()
        isDevelop()
        isReleaase()
        isHotfix()
        isMaster()
        setArifactType()
        this.branchNamePrefix != null ?: throw new IllegalArgumentException('ERROR: Branch name not compatible with gitflow. Expects value (feature/*, epicfeature/*, develop, release, release/X.Y, release/X.Y.0, hotfix, hotfix/X.Y.Z, master)')
        
    }

    public Boolean isFeature(){
        if (this.branchName ==~ /(.*\/feature)|(feature)\/.*$/){
            this.branchNamePrefix = 'feature'
            return true
        }
    }

    public Boolean isEpicFeature(){
        if (this.branchName ==~ /(.*\/epicfeature)|(epicfeature)\/.*$/){
            this.branchNamePrefix = 'epicfeature'
            return true
        }
    }

    public Boolean isDevelop(){
        if (this.branchName ==~ /(.*\/develop)|(develop)$/){
            this.branchNamePrefix = 'develop'
            return true
        }
    }

    public Boolean isReleaase(){
        if (this.branchName ==~ /(.*\/release|release)(\/([0-9]+\.[0-9]+|[0-9]+\.[0-9]+\.0)|)$/){
            this.branchNamePrefix = 'release'
            return true
        }
    }

    public Boolean isHotfix(){
        if (this.branchName ==~ /(.*\/hotfix|hotfix)(\/([0-9]+\.[0-9]+\.[0-9]+)|)$/){
            this.branchNamePrefix = 'hotfix'
            return true
        }
    }

    public Boolean isMaster(){
        if (this.branchName ==~ /(.*\/master)|(master)$/){
            this.branchNamePrefix = 'master'
            return true
        }
    }

    public String setArifactType(){
        this.artifactType = (this.branchNamePrefix ==~/^release|hotfix$/) ? 'release' : 'snapshot'
    }
}