package new_simvasos.localization;

import new_simvasos.localization.CS;

import java.util.ArrayList;
import java.util.Random;

public class PatrolDrone extends CS {
    int location_i;
    int location_j;
    int rescued;
    ArrayList<RescueRobot> messageConnection;
    int delay;
    int speed;

    /**
     * Instantiates a new CS.
     *
     * @param name the name
     */
    public PatrolDrone(String name, int speed, ArrayList<RescueRobot> connection) {  //constructor
        super(name);
        this.speed = speed;
        this.location_i = -1;
        this.location_j = -1;
        this.messageConnection = connection;
        this.speed = 2;
        this.delay = 1;
    }

    @Override
    public String act(int tick, ArrayList<ArrayList<Integer>> environment) {
        String ret = "CS : ";
        Random random = new Random();

        if (this.location_i == -1 || this.location_j == -1) {
            this.location_i = random.nextInt(environment.size());
            this.location_j = random.nextInt(environment.size());
        }
        ret = ret + this.name + " at Location: (" + this.location_i + "," + this.location_j + ")";

        for(int s = 0; s < speed; s++){
            System.out.println(this.location_i + ", " + this.location_j);
            if(environment.get(this.location_i).get(this.location_j) > 0) { //rescue
                // send to message
                String contents = "(" + this.location_i + "," + this.location_j + ")";
                int openTime = tick + delay;
                for(RescueRobot cs : messageConnection){
                    Message message = new Message(contents, openTime, name, cs.getName());
                    cs.addMessage(message);
                }
            }
            else{   //todo: patrol policy implementation
                float decision = random.nextFloat() * 4;

                if (decision < 1){
                    this.location_i = (this.location_i + environment.size() - 1) % environment.size();
                }
                else if (decision < 2){
                    this.location_j = (this.location_i + environment.size() + 1) % environment.size();
                }
                else if (decision < 3){
                    this.location_i = (this.location_i + environment.size() + 1) % environment.size();
                }
                else{
                    this.location_j = (this.location_i + environment.size() - 1) % environment.size();
                }
            }
        }
        return ret;
    }

    public void reset() {
        this.location_i = -1;
        this.location_j = -1;
    }
}
