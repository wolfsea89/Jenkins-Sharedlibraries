def call(Object formParams) {
    def setfact = [
        'branchPrefix': getBranch(formParams.branch),
        'version': getVersion(formParams.branch)
    ]
    println(setfact)
    
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

def getVersion(String branch){
    String branchPrefix = getBranch(branch)
    if (branchPrefix ==~/feature|epicfeature|develop/) {
        def now = new Date()
        def time1 = now.toMonth()
        def time2 = now.toYear()
        def time3 = now.toMonthDay()
        println("${time1},${time2},${time3}")
    }
}