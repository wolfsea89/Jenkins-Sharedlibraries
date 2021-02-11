def call(Object formParams, Object env ) {
    def setfact = [
        'branchPrefix': getBranch(formParams.branch),
        'version': getVersion([formParams.branch, env.BUILD_NUMBER])
    ]
    println(setfact)
}

def getVersion(String branch, String buildNumber]){
    // def result = []
    String branchPrefix = getBranch(branch)
    if (branchPrefix ==~/feature|epicfeature|develop/) {
        def now = new Date()
        println("${now.getYear()}.${now.getMonth()}.${now.getDay()}.${buildNumber}")
        println("${now.getYear()}.${now.getMonth()}.${now.getDay()}")
    }
}

def getBranch(String branch) {
    String result
    if (branch ==~ /(.*\/feature)|(feature)\/.*$/) {
        result = 'feature'
    } else if (branch ==~ /(.*\/epicfeature)|(epicfeature)\/.*$/) {
        result = 'epicfeature'
    } else if (branch ==~ /(.*\/develop)|(develop)$/) {
        result = 'develop'
    } else if (branch ==~ /(.*\/release)|(release)\/.*$/) {
        result = 'release'
    } else if (branch ==~ /(.*\/hotfix)|(hotfix)\/.*$/) {
        result = 'hotfix'
    } else if (branch ==~ /(.*\/master)|(master)$/) {
        result = 'master'
    }
    return result
}