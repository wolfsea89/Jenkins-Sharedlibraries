package org.ci

/* groovylint-disable-next-line ClassJavadoc, CompileStatic, UnnecessaryPublicModifier */
public class GatheringInformation {

    /* groovylint-disable-next-line MethodReturnTypeRequired, NoDef */
    def getBranchPrefix (String branchName) {
        if (branchName) {
            if (branchName.indexOf('/') > 1) {
                /* groovylint-disable-next-line DuplicateStringLiteral */
                return branchName.split('/')[1]
            }
        } else {
            error('ERROR: branchName argument not specified ')
        }
    }

}
