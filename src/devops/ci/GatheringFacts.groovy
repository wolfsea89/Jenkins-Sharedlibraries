package devops.ci

class GatheringFacts implements IGatheringFacts {

    private static final defaultBranchPrefix = 'feature'

    private String branchName
    private String branchNamePrefix
    
    GatheringFacts(){ }

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
        
        String branchNamePrefixx
        
        if (branchName ==~ /(.*\/feature)|(feature)\/.*$/) {
            branchNamePrefixx = 'feature'
        } else if (branchName ==~ /(.*\/epicfeature)|(epicfeature)\/.*$/) {
            branchNamePrefixx = 'epicfeature'
        } else if (branchName ==~ /(.*\/develop)|(develop)$/) {
            branchNamePrefixx = 'develop'
        } else if (branchName ==~ /(.*\/release|release)(\/([0-9]+\.[0-9]+|[0-9]+\.[0-9]+\.0)|)$/) {
            branchNamePrefixx = 'release'
        } else if (branchName ==~ /(.*\/hotfix|hotfix)(\/([0-9]+\.[0-9]+\.[0-9]+)|)$/) {
            branchNamePrefixx = 'hotfix'
        } else if (branchName ==~ /(.*\/master)|(master)$/) {
            branchNamePrefixx = 'master'
        } else {
            throw new SecurityException('ERROR: Branch name not compatible with gitflow. Expects value (feature/*, epicfeature/*, develop, release, release/X.Y, release/X.Y.0, hotfix, hotfix/X.Y.Z, master)')
        }
        this.branchNamePrefix = branchNamePrefix
    }


}   