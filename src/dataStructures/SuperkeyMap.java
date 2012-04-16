package dataStructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

@SuppressWarnings("serial")
public class SuperkeyMap<A> extends
		HashMap<Integer, A> {

	private ArrayList<Integer> keySuperset;

	public SuperkeyMap() {
		super();
		keySuperset = new ArrayList<Integer>();
	}

	public Integer getUniqueKey() {
		Integer i = 0;
		while (i < Integer.MAX_VALUE) {
			if (!keySuperset.contains(i)) {
				keySuperset.add(i);
				return i;
			}
			i++;
		}
		return -1;
	}

	public void clearUnusedKeys() {
		Set<Integer> usedKeys = this.keySet();
		ArrayList<Integer> toRemove = new ArrayList<Integer>();
		for (Integer I : keySuperset)
			if (!usedKeys.contains(I))
				toRemove.add(I);
		keySuperset.removeAll(toRemove);
	}

	@Override
	public A remove(Object key) {
		if (key.getClass() != Integer.class)
			throw new IllegalArgumentException();
		keySuperset.remove(key);
		return super.remove(key);
	}

	@Override
	public String toString() {
		String toReturn = "TupleMap: [ ";
		Set<Entry<Integer, A>> set = this.entrySet();
		for (Entry<Integer, A> e : set)
			toReturn += "key: " + e.getKey().toString() + " value: "
					+ e.getValue().toString() +  " | ";
		toReturn += "keySuperset: ";
		for (Integer I : keySuperset)
			toReturn += I.toString() + " ";
		toReturn += "]";
		return toReturn;
	}

}
