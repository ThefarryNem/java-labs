class Key {
	private String name;
	private boolean pressed;

	public Key(String name) {
		this.name = name;
		this.pressed = false;
	}

	public void press() {
		pressed = true;
		System.out.println(name + " pressed.");
	}

	public void release() {
		pressed = false;
		System.out.println(name + " released.");
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Key key = (Key) obj;
		return name.equals(key.name);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		return "Key{" +
				"name='" + name + '\'' +
				", pressed=" + pressed +
				'}';
	}
}

class Piano {

	public static void main(String[] args) {
		Piano piano = new Piano(5);
		piano.tune();
		piano.play();
		piano.pressKey(2);
		System.out.println(piano);
	}

	private Key[] keys;

	public Piano(int numberOfKeys) {
		keys = new Key[numberOfKeys];
		for (int i = 0; i < numberOfKeys; i++) {
			keys[i] = new Key("Key " + (i + 1));
		}
	}

	public void tune() {
		System.out.println("Piano tuned.");
	}

	public void play() {
		System.out.println("Playing the piano:");
		for (Key key : keys) {
			key.press();
			key.release();
		}
	}

	public void pressKey(int index) {
		if (index >= 0 && index < keys.length) {
			keys[index].press();
		} else {
			System.out.println("Invalid key index.");
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Piano piano = (Piano) obj;
		return keys.length == piano.keys.length;
	}

	@Override
	public int hashCode() {
		return java.util.Arrays.hashCode(keys);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Piano with keys:\n");
		for (Key key : keys) {
			sb.append(key.toString()).append("\n");
		}
		return sb.toString();
	}
}
