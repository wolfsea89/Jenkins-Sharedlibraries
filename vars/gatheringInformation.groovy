import org.ci.GatheringInformation

def branchNamePrefix = ""

// def call() {
//     return new GatheringInformation()
// }

def call (String branchName) {
    if (branchName) {
        if (branchName.indexOf('/') > 1) {
            /* groovylint-disable-next-line DuplicateStringLiteral */
            return branchName.split('/')[1]
        }
    } else {
        error('ERROR: branchName argument not specified ')
    }
}