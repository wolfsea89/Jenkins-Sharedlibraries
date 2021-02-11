def call(Object formParams) {
    println(formParams)
    getBranch(formParams.branch)
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
        result = 'hotfix'
    }
    println(result)
}

String isBranchFeature(String branch) {
    String result
    if (branch ==~ /(.*\/feature\/)|(feature).*$/) {
        result = 'feature'
    }
    return result
}

String isBranchEpicfeature(String branch) {
    String result
    if (branch ==~ /(.*\/epicfeature\/)|(epicfeature).*$/) {
        result = 'epicfeature'
    }
    return result
}

String isBranchDevelop(String branch) {
    String result
    if (branch ==~ /(.*\/develop\/)|(develop)$/) {
        result = 'develop'
    }
    return result
}

String isBranchRelease(String branch) {
    String result
    if (branch ==~ /(.*\/release\/)|(release)$/) {
        result = 'release'
    }
    return result
}