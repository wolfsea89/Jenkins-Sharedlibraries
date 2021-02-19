package devops.ci

class BranchNamePrefix implements Serializable {

  String branchNamePrefix

  BranchNamePrefix (String branchName){
    isFeature()
    isEpicFeature()
    isDevelop()
    isRelease()
    isHotfix()
    isMaster()
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
}