package devops.ci

class GatheringFacts implements Serializable  {

    private static final defaultBranchPrefix = 'feature'

    private String branchName
    private String branchNamePrefix
    
    GatheringFacts(){ }

    GatheringFacts setBranchName(String branchName){
        this.branchName = branchName
        this.setPrefix(branchName)
        return this
    }

    private void setPrefix(String branchName){
        this.branchNamePrefix = (branchName ==~ /(.*\/feature)|(feature)\/.*$/) ? 'feature' : this.branchNamePrefix
            
        // } else if (branchName =~ /(.*\/epicfeature)|(epicfeature)\/.*$/) {
        //     branchNamePrefix = 'epicfeature'
        // } else if (branchName =~ /(.*\/develop)|(develop)$/) {
        //     branchNamePrefix = 'develop'
        // } else if (branchName =~ /(.*\/release|release)(\/([0-9]+\.[0-9]+|[0-9]+\.[0-9]+\.0)|)$/) {
        //     branchNamePrefix = 'release'
        // } else if (branchName =~ /(.*\/hotfix|hotfix)(\/([0-9]+\.[0-9]+\.[0-9]+)|)$/) {
        //     branchNamePrefix = 'hotfix'
        // } else if (branchName =~ /(.*\/master)|(master)$/) {
        //     branchNamePrefix = 'master'
        // } else {
        //     throw new SecurityException('ERROR: Branch name not compatible with gitflow. Expects value (feature/*, epicfeature/*, develop, release, release/X.Y, release/X.Y.0, hotfix, hotfix/X.Y.Z, master)')
        // }
        // this.branchNamePrefix = branchNamePrefix
    }

}   