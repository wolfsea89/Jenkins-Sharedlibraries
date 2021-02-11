def call(Object formParams) {

    println(formParams)
    isBranchDevelp(formParams.branch)
}

def getBranch(String branch){
    String result
    if(result = isBranchDevelp(formParams.branch))

    println(result)
}

def isBranchDevelp(String branch){
    String result
    if(branch ==~/(.*\/develop\/)|(develop)$/){
        result = "develop"
    }
    return result
}