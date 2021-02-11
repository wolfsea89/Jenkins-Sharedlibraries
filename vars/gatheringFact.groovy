def call(Object formParams) {
    println(formParams)
    isBranchDevelp(formParams.branch)
}

def getBranch(String branch) {
    String result
    String develop = isBranchDevelop(formParams.branch)
    String feature = isBranchFeature(formParams.branch)
    String epicfeature = isBranchEpicfeature(formParams.branch)
    String release = isBranchRelease(formParams.branch)
    // if(result = isBranchDevelp(formParams.branch))

    println(result)
    println(develop)
    println(feature)
    println(epicfeature)
    println(release)
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