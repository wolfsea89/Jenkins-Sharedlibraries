package org.ci

/* groovylint-disable-next-line ClassJavadoc, CompileStatic, UnnecessaryPublicModifier */
class GatheringInformation {

    String branchPrefix

    GatheringInformation(String branchName) {
        print(branchName)
        if (branchName) {
        // if (branchName.indexOf('/') > 1) {
        // this.branchPrefix = branchName.split('/')[1]
            this.branchPrefix = branchName
        // }
        } else {
            this.branchPrefix = "dupa"
            println('ERROR: branchName argument not specified ')
        }
    }

    /* groovylint-disable-next-line MethodReturnTypeRequired, NoDef */
    String getBranchPrefix () {
        return this.branchPrefix
    }

}
