def call(Object formParams) {

    println(formParams)
    isBranchDevelp(formParams.branch)
}

def isBranchDevelp(String branch){
    if(branch ==~/.*(\/develop\/)$/){
        println('ok')
    }
}