package devops.ci

class GatheringFacts implements IGatheringFacts {

    private static final defaultBranchPrefix = 'feature'

    private String branchName
    private String branchNamePrefix

    def pipeline
    
    GatheringFacts(pipeline){
        this.pipeline = pipeline
    }

    public GatheringFacts setBranchName(String branchName){
        this.branchName = branchName
        this.setBranchPrefix(branchName)
        return this
    }

    public String getBranchNamePrefix(){
        return this.branchNamePrefix
    }

    public String getBranchName(){
        return this.branchName
    }

    private void setBranchPrefix(String branchName){
        pipeline.echo"WSK-1"

        if (branchName ==~ /(.*\/feature)|(feature)\/.*$/) {
            this.branchNamePrefix = 'feature'
        } else if (branchName ==~ /(.*\/epicfeature)|(epicfeature)\/.*$/) {
            this.branchNamePrefix = 'epicfeature'
        } else if (branchName ==~ /(.*\/develop)|(develop)$/) {
            this.branchNamePrefix = 'develop'
        } else if (branchName ==~ /(.*\/release|release)(\/([0-9]+\.[0-9]+|[0-9]+\.[0-9]+\.0)|)$/) {
            this.branchNamePrefix = 'release'
        } else if (branchName ==~ /(.*\/hotfix|hotfix)(\/([0-9]+\.[0-9]+\.[0-9]+)|)$/) {
            this.branchNamePrefix = 'hotfix'
        } else if (branchName ==~ /(.*\/master)|(master)$/) {
            this.branchNamePrefix = 'master'
        } else {
            throw new SecurityException('ERROR: Branch name not compatible with gitflow. Expects value (feature/*, epicfeature/*, develop, release, release/X.Y, release/X.Y.0, hotfix, hotfix/X.Y.Z, master)')
            pipeline.error('ERROR: Branch name not compatible with gitflow. Expects value (feature/*, epicfeature/*, develop, release, release/X.Y, release/X.Y.0, hotfix, hotfix/X.Y.Z, master)')
        }
    }


}   