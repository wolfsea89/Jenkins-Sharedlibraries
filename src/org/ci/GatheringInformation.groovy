package org.ci

/* groovylint-disable-next-line ClassJavadoc, CompileStatic, UnnecessaryPublicModifier */
class GatheringInformation implements Serializable {

    def branchPrefix
    
    GatheringInformation(env, branchName){
        this.env = env
        print(branchName)
        if (branchName) {
            if (branchName.indexOf('/') > 1) {
                this.branchPrefix = branchName.split('/')[1]
            }
        } else {
            error('ERROR: branchName argument not specified ')
        }
    }

    /* groovylint-disable-next-line MethodReturnTypeRequired, NoDef */
    def getBranchPrefix (String branchName) {
        return this.branchPrefix
    }

}
