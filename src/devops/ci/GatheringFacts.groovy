package devops.ci

class GatheringFacts extends Abstractcommand {

    List<Command> list = new ArrayList<Command>()

    List<Command> getCommandList(){
        return list
    }

    GatheringFacts setParams(Object params){
        list.add(new SetParams(params))
        return this
    }

    @Override
    GatheringFacts clone(){
        try{
            GatheringFacts cmd = (GatheringFacts) super.clone()
            cmd.setList((getList().clone() as ArrayList<Command>))
            return cmd
        } catch (CloneNotSupportedException e){
            throw new AssertionError(e)
        }
    }
}   