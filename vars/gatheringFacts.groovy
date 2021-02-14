def call(Object formParams, Object env ) {

    String branchPrefix = setBranch(formParams.branch)
    Object version = setVersion(formParams.branch, branchPrefix, env.BUILD_NUMBER, formParams.manualVersion)
    String artifactType = setArifactType(branchPrefix)

    def setfact = [
        'branchName':     formParams.branch,
        'branchPrefix':   branchPrefix,
        'version':        version,
        'repositoryUrl':  formParams.repositoryUrl,
        'jobName':        env.JOB_BASE_NAME,
        'jobBuildNumber': env.BUILD_NUMBER,
        'artifactType':   artifactType,
        'nodeName':       env.NODE_NAME,
        'workspace':      env.WORKSPACE
    ]
    return setfact
}

def setVersion(String branch, String branchPrefix, String buildNumber, String manualVersion) {
    
    def version

    if (branchPrefix ==~ /feature|epicfeature|develop/) {
        
        def now = new Date()
        version = "${now.format('yyyy.M.d', TimeZone.getTimeZone('UTC'))}"

    } else if (branchPrefix ==~ /release|hotfix|master/) {

        if (branch ==~ /(.*\/(release|hotfix|master))|(release|hotfix|master)$/){
            
            if (manualVersion ==~ /[0-9]+\.[0-9]+\.[0-9]+$/) {
                version = "${manualVersion}"
            } else {
                error('ERROR: Invalid set manual version')
            }

        } else {
            
            for (def substring in branch.split('/')) {
                
                if (substring ==~ /[0-9]+\.[0-9]+$/) {
                    version = "${substring}.0"
                } else if (substring ==~ /[0-9]+\.[0-9]+\.[0-9]+$/) {
                    version = "${substring}"
                }
            }
        }
    }
    if (version != '') {
        return [
            'semanticVersion': "${version}",
            'semanticVersionWithBuildNumber': "${version}.${buildNumber}"
        ]
    } else {
        error('ERROR: I can\'t set the version')
    }
    
}

def setBranch(String branch) {
    String result
    if (branch ==~ /(.*\/feature)|(feature)\/.*$/) {
        result = 'feature'
    } else if (branch ==~ /(.*\/epicfeature)|(epicfeature)\/.*$/) {
        result = 'epicfeature'
    } else if (branch ==~ /(.*\/develop)|(develop)$/) {
        result = 'develop'
    } else if (branch ==~ /(.*\/release|release)(\/([0-9]+\.[0-9]+|[0-9]+\.[0-9]+\.0)|)$/) {
        result = 'release'
    } else if (branch ==~ /(.*\/hotfix|hotfix)(\/([0-9]+\.[0-9]+\.[0-9]+)|)$/) {
        result = 'hotfix'
    } else if (branch ==~ /(.*\/master)|(master)$/) {
        result = 'master'
    } else {
        error('ERROR: Branch name not compatible with gitflow. Expects value (feature/*, epicfeature/*, develop, release, release/X.Y, release/X.Y.0, hotfix, hotfix/X.Y.Z, master)')
    }
    return result
}

def applicationConfiguration(String appCfgJsonPath) {
    def app = readJSON file: appCfgJsonPath
    return app
}

def setArifactType(String branchPrefix){
    def artifactType = "snapshot"
    if(branchPrefix ==~ /^release|hotfix$/){
        artifactType = 'release'
    }
    return artifactType
}