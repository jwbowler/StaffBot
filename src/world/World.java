package world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import devices.Camera;

public class World {
	private static final int NUM_REACTORS = 3;
	
	Reactor reactors[] = new Reactor[NUM_REACTORS];
	List<Ball> balls = new ArrayList<Ball>();
	
	public World() {
		for (int i = 0; i < NUM_REACTORS; i++) {
			reactors[i] = new Reactor();
		}
	}

	public void update(Camera camera) {
		balls = camera.getBalls();
		double[][] reactorLocations = camera.getReactorLocations();
		for (int i = 0; i < NUM_REACTORS; i++) {
			reactors[i].setLocation(reactorLocations[i]);
		}
	}
	
	public Ball getClosestBall() {
		return Collections.min(balls, new Comparator<Ball>() {
			public int compare(Ball b1, Ball b2) {
				return (b1.getZ() > b2.getZ()) ? 1 : -1;
			}
		});
	}
	
	public Reactor getClosestReactor() {
		int bestIndex = 0;
		double bestZ = reactors[0].getZ();
		for (int i = 1; i < NUM_REACTORS; i++) {
			if (reactors[0].getZ() < bestZ) {
				bestIndex = i;
			}
		}
		return reactors[bestIndex];
	}

}
