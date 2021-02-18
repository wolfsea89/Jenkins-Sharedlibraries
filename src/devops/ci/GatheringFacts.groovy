package devops.ci

interface IGatheringFacts {
    void setParams(Object params)

}

class GatheringFacts implements IGatheringFacts extends Serializable {

    GatheringFacts(){

    }

    @Override
    void setParams(Object params){
        println(params)
    }

    
}   