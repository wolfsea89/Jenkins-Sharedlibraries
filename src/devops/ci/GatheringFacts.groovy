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
    String version

    GatheringFacts(def params, def env){
        // Params
        this.branchName = params.branch
        this.repositoryUrl = params.repositoryUrl
        if(params.manualVersion == "" || params.manualVersion ==~ /[0-9]+\.[0-9]+\.[0-9]+$/){
            this.manualVersion = params.manualVersion
        } else {
            throw new IllegalArgumentException('ERROR: Invalid set manual version')
        }

        // Env
        this.jobName = env.JOB_BASE_NAME
        this.jobBuildNumber = env.BUILD_NUMBER
        this.nodeName = env.NODE_NAME
        this.workspace = env.WORKSPACE

        // Set variables
        isFeature()
        isEpicFeature()
        isDevelop()
        isRelease()
        isHotfix()
        isMaster()
        setArifactType()
        semanticVersion()

        if (this.branchNamePrefix == null) {
            throw new IllegalArgumentException('ERROR: Branch name not compatible with gitflow. Expects value (feature/*, epicfeature/*, develop, release, release/X.Y, release/X.Y.0, hotfix, hotfix/X.Y.Z, master)')
        }

        
    }

    @NonCPS
    public Boolean isFeature(){
        if (this.branchName ==~ /(.*\/feature)|(feature)\/.*$/){
            this.branchNamePrefix = 'feature'
            return true
        }
    }
    
    @NonCPS
    public Boolean isEpicFeature(){
        if (this.branchName ==~ /(.*\/epicfeature)|(epicfeature)\/.*$/){
            this.branchNamePrefix = 'epicfeature'
            return true
        }
    }

    @NonCPS
    public Boolean isDevelop(){
        if (this.branchName ==~ /(.*\/develop)|(develop)$/){
            this.branchNamePrefix = 'develop'
            return true
        }
    }

    @NonCPS
    public Boolean isRelease(){
        if (this.branchName ==~ /(.*\/release|release)(\/([0-9]+\.[0-9]+|[0-9]+\.[0-9]+\.0)|)$/){
            this.branchNamePrefix = 'release'
            return true
        }
    }

    @NonCPS
    public Boolean isHotfix(){
        if (this.branchName ==~ /(.*\/hotfix|hotfix)(\/([0-9]+\.[0-9]+\.[0-9]+)|)$/){
            this.branchNamePrefix = 'hotfix'
            return true
        }
    }

    @NonCPS
    public Boolean isMaster(){
        if (this.branchName ==~ /(.*\/master)|(master)$/){
            this.branchNamePrefix = 'master'
            return true
        }
    }

    @NonCPS
    public String setArifactType(){
        this.artifactType = (this.branchNamePrefix ==~/^release|hotfix$/) ? 'release' : 'snapshot'
    }

    @NonCPS
    public String semanticVersion(){

        def now = new Date()
        version = "${now.format('yyyy.M.d', TimeZone.getTimeZone('UTC'))}"

        if (this.branchNamePrefix ==~ /release|hotfix|master/) {
            
            if(this.manualVersion){
                version = "${manualVersion}"
            } else {
                version = (this.manualVersion =~ /[0-9]+\.[0-9]+\.[0-9]+$/)
                version = (this.manualVersion =~ /[0-9]+\.[0-9]+$/) + ".0"
            }
        }
        this.version = version
    }
}