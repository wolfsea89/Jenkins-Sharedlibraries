package devops.ci

class GatheringFacts implements IGatheringFacts {

    private def params
    // GatheringFacts(){

    // }

    @Override
    void setParams(def params){
        this.params = params
    }

    def getParams(){
        return this.params
    }

}   