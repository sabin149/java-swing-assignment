package examReplacementCW;

import java.util.ArrayList;

class BinarySearch {

	int binarySearch(ArrayList<Integer> quan, int x) {

		int l = 0, r = quan.size() - 1;

		while (l <= r) {
			int m = l + (r - l) / 2;

			if (quan.get(m) == x)
				return m;

			if (quan.get(m) < x)
				l = m + 1;

			else
				r = m - 1;
		}
		return -1;
	}

}