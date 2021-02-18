package devops.ci

interface IGatheringFacts extends Serializable {
    void setParams(Object params);

}

class GatheringFacts implements IGatheringFacts {

    // GatheringFacts(){

    // }

    @Override
    void setParams(Object params){
        println(params)
    }

    
}   