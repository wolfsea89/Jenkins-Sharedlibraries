package devops.ci

class GatheringFacts implements IGatheringFacts {

    private static final defaultBranchPrefix = 'feature'

    private String branchName
    private String branchNamePrefix
    
    GatheringFacts(){ }

    public GatheringFacts setBranchName(String branchName){
        this.branchName = branchName
        this.setBranchPrefix()
        return this
    }

    public String getBranchNamePrefix(){
        return this.branchNamePrefix
    }

    public String getBranchName(){
        return this.branchName
    }

    @Override
    private def setBranchPrefix(){

        String branchNamePrefixx

        switch(this.branchName) {
            case ~/(.*\/feature)|(feature)\/.*$/:
                branchNamePrefixx = 'feature'
                break;
            case ~ /(.*\/epicfeature)|(epicfeature)\/.*$/:
                branchNamePrefixx = 'epicfeature'
                break;
            case ~ /(.*\/develop)|(develop)$/:
                branchNamePrefixx = 'develop'
                break;
            default:
                throw new SecurityException('ERROR: Branch name not compatible with gitflow. Expects value (feature/*, epicfeature/*, develop, release, release/X.Y, release/X.Y.0, hotfix, hotfix/X.Y.Z, master)')
        }
        this.branchNamePrefix = branchNamePrefix
        // if (branchName ==~) {
            
        // } else if (branchName ==~ /(.*\/epicfeature)|(epicfeature)\/.*$/) {
        //     branchNamePrefixx = 'epicfeature'
        // } else if (branchName ==~ /(.*\/develop)|(develop)$/) {
        //     branchNamePrefixx = 'develop'
        // } else if (branchName ==~ /(.*\/release|release)(\/([0-9]+\.[0-9]+|[0-9]+\.[0-9]+\.0)|)$/) {
        //     branchNamePrefixx = 'release'
        // } else if (branchName ==~ /(.*\/hotfix|hotfix)(\/([0-9]+\.[0-9]+\.[0-9]+)|)$/) {
        //     branchNamePrefixx = 'hotfix'
        // } else if (branchName ==~ /(.*\/master)|(master)$/) {
        //     branchNamePrefixx = 'master'
        // } else {
        //     throw new SecurityException('ERROR: Branch name not compatible with gitflow. Expects value (feature/*, epicfeature/*, develop, release, release/X.Y, release/X.Y.0, hotfix, hotfix/X.Y.Z, master)')
        // }
        
    }


}   