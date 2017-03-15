import java.util.ArrayList;

public class Head {
	Roomba roomba;
	float left = 0;
	float right = 0;
	int milliseconds = 0;
	ArrayList<Drive> commands;
	Drive current;
	boolean rightBump;
	boolean leftBump;

	public Head(Roomba roomba) {
		this.roomba = roomba;
		commands = new ArrayList<Drive>();
		commands.add(new Drive(0, 0));
		initialize();
		current = commands.get(0);
	}
	void go() {
		roomba.driveDirect(current.getLeft(), current.getRight());
		if (!(current.isSleeping()) && commands.size() > 0) {
			current = commands.get(0);
			commands.remove(0);
		} else if (current.isSleeping()) {
			current.check();
		} else {
			this.loop();
		}
	}

	void driveDirect(float l, float r) {
		Drive d = new Drive(l, r);
		commands.add(d);
	}

	void sleep(int mils) {
		Drive s = commands.get(commands.size() - 1);
		s.setSleep(mils);
	}

	void loop() {
	}

	int getMilliseconds() {
		driveDirect(left, right);
		milliseconds--;
		return milliseconds;
	}

	void setBump(boolean b) {
		leftBump = b;
		rightBump = b;
	}

	boolean isBumpedLeft() {
		return leftBump;
	}
	boolean isBumpedRight() {
		return rightBump;
	}
	void readSensors(int num) {

	}

	void initialize() {
	}

}