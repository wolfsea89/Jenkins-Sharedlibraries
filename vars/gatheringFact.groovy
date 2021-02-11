def call(Object formParams) {

    println(formParams)
    isBranchDevelp(formParams.branch)
}

def isBranchDevelp(String branch){
    if(branch ==~/(.*\/develop\/)|(develop)$/){
        println('ok')
    } else {
        println('fail')
    }
}