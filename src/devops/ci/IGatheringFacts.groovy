package devops.ci

interface IGatheringFacts extends Serializable {
    
    GatheringFacts setBranchName(String branchName);
    String getBranchName();
    String getBranchNamePrefix();
}