# Jenkins.SharedLibraries

Language: [EN](README.md), [PL](README.PL.md)

# List of created functions used in the repository
| **vars**              | **method**               | **descriptions**                                                                                                              
|:---------------------:|:------------------------:|:-----------------------------------------------------------------------------------------------------------------------------:
| debug                 | call                     | print env variable                                                                                                            
| gatheringFacts        | call                     | create object with facts                                                                                                      
|                       | setVersion               | set the version                                                                                                               
|                       | setBranch                | returns the name of the branch compatible with gitflow                                                                        
|                       | applicationConfiguration | return object from jenkins\\\.json in application repositorry (build definition)
|                       | setArifactType           | determines whether it is a snapshot or release artifact                                                                       
| gitcheckout           | application              | clone repository with aplication code                                                                                         
|                       | jenkinsSripts            | clone repository with jenkinsfile code                                                                                        
| prebuildScriptsDocker | setVersion               | set version in dockerfile \(use regexp \`$\{jenkins\_include\_version\}`                                                      
|                       | setCredentials           | set administrator credential in dockerfile $\`\{jenkins\_include\_admin\_username\}` `$\{jenkins\_include\_admin\_password\}` 
|                       | setJenkinsJobInfo        | set jenkins info \`$\{jenkins\_include\_jenkinsJob\}                                                                          
| dockerCi              | buildProjects            | build project \(docker build \)                                                                                               
|                       | publishBaseImage         | publish docker image \(docker tag and docker push \)                                                                          
|                       | cleanAfterBuild          | clean afte build \(docker rmi\)                                                                                               

