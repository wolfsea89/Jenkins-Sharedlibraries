package devops.ci

class GatheringFacts implements IGatheringFacts {

    private def params
    // GatheringFacts(){

    // }

    @Override
    void setParams(def params){
        this.params
    }

    def getParams(){
        return this.params
    }

}   