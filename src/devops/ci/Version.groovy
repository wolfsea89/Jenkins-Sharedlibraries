package devops.ci

class Version implements Serializable {

  String version

  Version(String branchNamePrefix, String branchName, String manualVersion = "0.0.0"){

    def now = new Date()
    def version = "${now.format('yyyy.M.d', TimeZone.getTimeZone('UTC'))}"

    if (branchNamePrefix ==~ /release|hotfix|master/) {
            
        if(this.manualVersion != '0.0.0'){
            version = "${manualVersion}"
        } else {
            def substring = this.branchName.split('/')
            if(substring.last() ==~ /[0-9]+\.[0-9]+$/){
                version = "${substring.last()}.0"
            } else if(substring.last() ==~ /[0-9]+\.[0-9]+\.[0-9]+$/){
                version = "${substring.last()}"
            } else {
                throw new IOException('ERROR: I can\'t set the version')
            }
        }
    }
    this.version = version
  }
}