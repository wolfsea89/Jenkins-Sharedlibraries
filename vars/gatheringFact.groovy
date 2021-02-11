def call(Object formParams, Object env ) {
    def setfact = [
        'branchPrefix': getBranch(formParams.branch),
        'version': getVersion([formParams.branch, env.BUILD_NUMBER])
    ]
    println(env.BUILD_NUMBER.getClass())
    println(setfact)
}

def getVersion(String branch, def buildNumber){
    def version
    String branchPrefix = getBranch(branch)
    if (branchPrefix ==~/feature|epicfeature|develop/) {
        def now = new Date()
        version = "${now.getYear()}.${now.getMonth()}.${now.getDay()}"
    } else if(branchPrefix ==~/release|hotfix|master/){
        for(def substring in branchPrefix.split('/')){
            if(substring ==~ /[0-9]+\.[0-9]+$/){
                version = "${substring}.0"
            } else if (substring ==~ /[0-9]+\.[0-9]+\.[0-9]+$/){
                version = "${substring}.0"
            }
        }
    }
    if (version != ""){
        result = [
            'semanticVersion': "${version}",
            'semanticVersionWithBuildNumber': "${version}.${buildNumber}"
        ]
    } else {
        error("ERROR: Branch name not compatible with gitflow. Expects value (feature/*, epicfeature/*, develop, release, release/X.Y, release/X.Y.0, hotfix, hotfix/X.Y.Z, master)")
    }
    println(result)
}

def getBranch(String branch) {
    String result
    if (branch ==~ /(.*\/feature)|(feature)\/.*$/) {
        result = 'feature'
    } else if (branch ==~ /(.*\/epicfeature)|(epicfeature)\/.*$/) {
        result = 'epicfeature'
    } else if (branch ==~ /(.*\/develop)|(develop)$/) {
        result = 'develop'
    } else if (branch ==~ /(.*\/release|release)(\/([0-9]+\.[0-9]+|[0-9]+\.[0-9]+\.0)|)$/) {
        result = 'release'
    } else if (branch ==~ /(.*\/hotfix|hotfix)(\/([0-9]+\.[0-9]+\.[0-9]+)|)$/) {
        result = 'hotfix'
    } else if (branch ==~ /(.*\/master)|(master)$/) {
        result = 'master'
    } else {
        error("ERROR: Branch name not compatible with gitflow. Expects value (feature/*, epicfeature/*, develop, release, release/X.Y, release/X.Y.0, hotfix, hotfix/X.Y.Z, master)")
    }
    return result
}
