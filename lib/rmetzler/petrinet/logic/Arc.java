package petrinet.logic;

/**
 * Eine Kante geht von einer Stelle zu einer Transition oder umgekehrt.
 * Das wird �ber die Konstruktoren abgebildet.
 * 
 * Un arco va da un Posto ad una Transizione, o viceversa.
�* Questo (la direzione) viene indicato tramite i costruttori.
 * 
 * @author rmetzler
 */
public class Arc
extends PetrinetObject {

    Place place;
    Transition transition;
    Direction direction;
    int weight = 1;
    
    enum Direction {
        
        /**
         * Die 2 Richtungen, die so eine Kante haben darf
         * 
         * Le 2 Direzioni che pu� avere un arco
         */
        
        PLACE_TO_TRANSITION {
            @Override
            public boolean canFire(Place p, int weight) {
                return p.hasAtLeastTokens(weight);
            }

            @Override
            public void fire(Place p, int weight) {
                p.removeTokens(weight);
            }

        },
        
        TRANSITION_TO_PLACE {
            @Override
            public boolean canFire(Place p, int weight) {
                return ! p.maxTokensReached(weight);
            }

            @Override
            public void fire(Place p, int weight) {
                p.addTokens(weight);
            }

        };

        public abstract boolean canFire(Place p, int weight);

        public abstract void fire(Place p, int weight);
    }
    
    private Arc(String name, Direction d, Place p, Transition t) {
        super(name);
        this.direction = d;
        this.place = p;
        this.transition = t;
    }

    protected Arc(String name, Place p, Transition t) {
        this(name, Direction.PLACE_TO_TRANSITION, p, t);
        t.addIncoming(this);
        p.addOutgoing(this);
    }

    protected Arc(String name, Transition t, Place p) {
        this(name, Direction.TRANSITION_TO_PLACE, p, t);
        t.addOutgoing(this);
        p.addIncoming(this);
    }

    public boolean canFire() {
        return direction.canFire(place, weight);
    }
    
    public void fire() {
        this.direction.fire(place, this.weight);
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    public int getWeight() {
        return weight;
    }
    
    public Place getPlace() {
    	return place;
    }
    
    public Transition getTransition() {
    	return transition;
    }
    
    public int getDirection() {
    	if( direction == Direction.PLACE_TO_TRANSITION )
    		return 1;
    	else return 0;
    }
}