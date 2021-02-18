package devops.ci

interface IGatheringFacts extends Serializable {
    
    @Override
    GatheringFacts setBranchName(String branchName);
    
    String getBranchName();
    String getBranchNamePrefix();
}