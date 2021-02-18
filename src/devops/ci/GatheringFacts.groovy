package devops.ci

interface IGatheringFacts extends Serializable {

}

class GatheringFacts implements IGatheringFacts {

    GatheringFacts(){

    }

    @Override
    void setParams(Object params){
        println(params)
    }

    
}   