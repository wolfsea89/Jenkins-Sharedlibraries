# Jenkins.SharedLibraries

Język: [EN](README.md), [PL](README.PL.md)

# Lista stworzonych funkcji użytych w repozytorium

 **vars**              | **method**               | **descriptions**
:---------------------:|:------------------------:|:-----------------------------------------------------------------------------------------------------------------------------:
 debug                 | call                     | wyświetla użyte zminne globalne
 gatheringFacts        | call                     | tworzy obiect z faktami
                       | setVersion               | ustawia wersje artefaktu
                       | setBranch                | zwraca nazwę artafaktu zgodnego z gitflow
                       | applicationConfiguration | zwraca obiekt z jsona znajdującego się z repozytorium aplikacji (definicja budowania)
                       | setArifactType           | ustawia typ artefaktu (snapshot/release)
 gitcheckout           | application              | pobiera repozytorium aplikacji
                       | jenkinsSripts            | pobiera repozytorium scryptów jenkins (jenkinsfile)
 prebuildScriptsDocker | setVersion               | zapisuje wersje w dockerfile \(use regexp \`$\{jenkins\_include\_version\}` 
                       | setCredentials           | zapisuje poświadczenia logowania dla administratora dockerfile $\`\{jenkins\_include\_admin\_username\}` `$\{jenkins\_include\_admin\_password\}`
                       | setJenkinsJobInfo        | zapisuje informacje z budowania jenkinsa \`$\{jenkins\_include\_jenkinsJob\} 
 dockerCi              | buildProjects            | buduje projekt oparty o dockerfile \(docker build \)
                       | publishBaseImage         | publikuje obraz dockerowy \(docker tag and docker push \)
                       | cleanAfterBuild          | sprząta po budowaniu (usuwa obrazy) \(docker rmi\)