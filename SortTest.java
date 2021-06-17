import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SortTest {

	@Test
	void testSort1() {
		Integer[] unsorted1 = {2,5,3,0,2,3,0,3};
		Lampsort.sort(unsorted1);
		Integer[] sorted1 = {0,0,2,2,3,3,3,5};
		int count = 0;
		boolean b = true;
		for (int element : unsorted1) {
			if (unsorted1[count] != sorted1[count]) {
				b = false;
				break;
			}
		}
		assertTrue(b);
	}
	
	@Test
	void testSort2() {
		Integer[] unsorted1 = {10,9,8,7,6,5,4,3,2,1};
		Lampsort.sort(unsorted1);
		Integer[] sorted1 = {1,2,3,4,5,6,7,8,9,10};
		int count = 0;
		boolean b = true;
		for (int element : unsorted1) {
			if (unsorted1[count] != sorted1[count]) {
				b = false;
				break;
			}
		}
		assertTrue(b);
	}
	
	@Test
	void testSort3() {
		Integer[] unsorted1 = {1,2,3,4,5,6,7,8,9,10,11,12,13};
		Lampsort.sort(unsorted1);
		Integer[] sorted1 = {1,2,3,4,5,6,7,8,9,10,11,12,13};
		int count = 0;
		boolean b = true;
		for (int element : unsorted1) {
			if (unsorted1[count] != sorted1[count]) {
				b = false;
				break;
			}
		}
		assertTrue(b);
	}
	
	@Test
	void testSort4() {
		Integer[] unsorted1 = {1};
		Lampsort.sort(unsorted1);
		Integer[] sorted1 = {1};
		int count = 0;
		boolean b = true;
		for (int element : unsorted1) {
			if (unsorted1[count] != sorted1[count]) {
				b = false;
				break;
			}
		}
		assertTrue(b);
	}
	
	@Test
	void testSort5() {
		Integer[] unsorted1 = {9,8,7,6,5,4,3,2,1};
		Lampsort.sort(unsorted1);
		Integer[] sorted1 = {1,2,3,4,5,6,7,8,9};
		int count = 0;
		boolean b = true;
		for (int element : unsorted1) {
			if (unsorted1[count] != sorted1[count]) {
				b = false;
				break;
			}
		}
		assertTrue(b);
	}
	
	@Test
	void testSort6() {
		Integer[] unsorted1 = {5,4,7,1,8,3,2,9,0,6};
		Lampsort.sort(unsorted1);
		Integer[] sorted1 = {0,1,2,3,4,5,6,7,8,9};
		int count = 0;
		boolean b = true;
		for (int element : unsorted1) {
			if (unsorted1[count] != sorted1[count]) {
				b = false;
				break;
			}
		}
		assertTrue(b);
	}
	
	@Test
	void testSort7() {
		Integer[] unsorted1 = {3,4,7,1,8,5,2,9,0,6};
		Lampsort.sort(unsorted1);
		Integer[] sorted1 = {0,1,2,3,4,5,6,7,8,9};
		int count = 0;
		boolean b = true;
		for (int element : unsorted1) {
			if (unsorted1[count] != sorted1[count]) {
				b = false;
				break;
			}
		}
		assertTrue(b);
	}
	
	@Test
	void testSort8() {
		Integer[] unsorted1 = {3,2,1};
		Lampsort.sort(unsorted1);
		Integer[] sorted1 = {1,2,3};
		int count = 0;
		boolean b = true;
		for (int element : unsorted1) {
			if (unsorted1[count] != sorted1[count]) {
				b = false;
				break;
			}
		}
		assertTrue(b);
	}
}
