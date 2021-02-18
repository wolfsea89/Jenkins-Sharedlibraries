package devops.ci

interface IGatheringFacts extends Serializable {
    GatheringFacts setParams(def params);
    String getBranchName();
    String getBranchNamePrefix();
}