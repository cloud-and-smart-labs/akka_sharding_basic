package actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import pi_swarm_approx.JobMessage;

public class PiWorker extends AbstractActor {
	  private ActorRef workProcessorRouter;
	  
	  public static Props props(ActorRef workProcessorRouter) {
	    return Props.create(PiWorker.class, workProcessorRouter);
	  }
	  
	  public PiWorker(ActorRef workProcessorRouter) {
	        this.workProcessorRouter = workProcessorRouter;
	  }
	  
	  @Override
	    public Receive createReceive() {
	        return receiveBuilder()
	        	.match(JobMessage.class, this::sendSensorDataForProcessing)
	        	.build();
	  }
	  
	  private void sendSensorDataForProcessing(JobMessage j) {
		  System.out.println("#######: get pi value!!!!!!!");
            workProcessorRouter.tell(j, self());
	  }
	  
}