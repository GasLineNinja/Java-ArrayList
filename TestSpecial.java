import java.util.List;

import junit.framework.TestCase;

public class TestSpecial extends TestCase{

	Person div = new Person();
	Person j = new Person("John");
	Person a = new Person("Aaron");
	Person r = new Person("Rohit");
	
	GroupList group;

	
	
	public void setUp(){
		
	}
	
	//testing constructors
	public void test00() {
		boolean caught = false;
		try {
			group = new GroupList(null);
		}
		catch (IllegalArgumentException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	public void test01() {
		Person[] people = new Person[] {null};
		boolean caught = false;
		try {
			group = new GroupList(people);
		}
		catch (IllegalArgumentException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	public void test02() {
		Person[] people = new Person[] {j, a, a, r, null, a, j};
		boolean caught = false;
		try {
			group = new GroupList(people);
		}
		catch (IllegalArgumentException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	public void test03() {
		Person[] people = new Person[] {div};
		boolean caught = false;
		try {
			group = new GroupList(people);
		}
		catch (IllegalArgumentException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	public void test04() {
		Person[] people = new Person[] {div, j, a, r, div};
		boolean caught = false;
		try {
			group = new GroupList(people);
		}
		catch (IllegalArgumentException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	public void test05() {
		Person[] people = new Person[] {j, a, j, a, div, div, a};
		boolean caught = false;
		try {
			group = new GroupList(people);
		}
		catch (IllegalArgumentException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	public void test06() {
		Person[] people = new Person[] {j, div, j, a, div, div};
		boolean caught = false;
		try {
			group = new GroupList(people);
		}
		catch (IllegalArgumentException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	public void test07() {
		Person[] people = new Person[] {div, a , j, a, div, div};
		boolean caught = false;
		try {
			group = new GroupList(people);
		}
		catch (IllegalArgumentException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	public void test08() {
		Person[] people = new Person[] {div, a , null, a, div, div, a};
		boolean caught = false;
		try {
			group = new GroupList(people);
		}
		catch (IllegalArgumentException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	//testing closeGroup
	
	public void test10() {
		group = new GroupList();
		assertTrue(group.readyForGroup());
		boolean caught = false;
		try {
			group.closeGroup();
		}
		catch (IllegalStateException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	public void test11() {
		Person[] people = new Person[0];
		group = new GroupList(people);
		assertTrue(group.readyForGroup());
		boolean caught = false;
		try {
			group.closeGroup();
		}
		catch (IllegalStateException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	public void test12() {
		Person[] people = new Person[] {j, div};
		group = new GroupList(people);
		assertTrue(group.readyForGroup());
		boolean caught = false;
		try {
			group.closeGroup();
		}
		catch (IllegalStateException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	public void test13() {
		Person[] people = new Person[] {j, a, div, r, div};
		group = new GroupList(people);
		assertTrue(group.readyForGroup());
		boolean caught = false;
		try {
			group.closeGroup();
		}
		catch (IllegalStateException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	//testing removeNextGroup
	
	public void test20() {
		group = new GroupList();
		boolean caught = false;
		try {
			group.removeNextGroup();
		}
		catch (IllegalStateException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	public void test21() {
		Person[] people = new Person[0];
		group = new GroupList(people);
		boolean caught = false;
		try {
			group.removeNextGroup();
		}
		catch (IllegalStateException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	public void test22() {
		Person[] people = new Person[] {j};
		group = new GroupList(people);
		group.removeNextGroup();
		boolean caught = false;
		try {
			group.removeNextGroup();
		}
		catch (IllegalStateException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	public void test23() {
		Person[] people = new Person[] {j, a, div};
		group = new GroupList(people);
		group.removeNextGroup();
		boolean caught = false;
		try {
			group.removeNextGroup();
		}
		catch (IllegalStateException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	public void test24() {
		Person[] people = new Person[] {j, a, div, r, div};
		group = new GroupList(people);
		group.removeNextGroup();
		group.removeNextGroup();
		boolean caught = false;
		try {
			group.removeNextGroup();
		}
		catch (IllegalStateException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	public void test25() {
		Person[] people = new Person[] {j, a, div, r, j};
		group = new GroupList(people);
		group.removeNextGroup();
		group.removeNextGroup();
		boolean caught = false;
		try {
			group.removeNextGroup();
		}
		catch (IllegalStateException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	//testing addIndividual
	
	public void test30() {
		group = new GroupList();
		boolean caught = false;
		try {
			group.addIndividual(null);
		}
		catch (IllegalArgumentException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	public void test31() {
		group = new GroupList();
		boolean caught = false;
		try {
			group.addIndividual(div);
		}
		catch (IllegalArgumentException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	public void test32() {
		Person[] people = new Person[] {j};
		group = new GroupList(people);
		boolean caught = false;
		try {
			group.addIndividual(null);
		}
		catch (IllegalArgumentException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	public void test33() {
		Person[] people = new Person[] {j};
		group = new GroupList(people);
		boolean caught = false;
		try {
			group.addIndividual(div);
		}
		catch (IllegalArgumentException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	public void test34() {
		Person[] people = new Person[] {j, a, div};
		group = new GroupList(people);
		boolean caught = false;
		try {
			group.addIndividual(div);
		}
		catch (IllegalArgumentException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	//testing addGroup
	
	public void test40() {
		Person[] people = new Person[] {j, a};
		group = new GroupList(people);
		boolean caught = false;
		try {
			group.addGroup(people);
		}
		catch (IllegalStateException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	public void test41() {
		Person[] people = new Person[] {j, a, div};
		group = new GroupList(people);
		boolean caught = false;
		try {
			group.addGroup(null);
		}
		catch (IllegalArgumentException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	public void test42() {
		Person[] people = new Person[] {j, a, div};
		group = new GroupList(people);
		boolean caught = false;
		try {
			group.addGroup(new Person[0]);
		}
		catch (IllegalArgumentException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	public void test43() {
		Person[] people = new Person[] {j, a, div};
		group = new GroupList(people);
		Person[] people2 = new Person[] {r, div};
		boolean caught = false;
		try {
			group.addGroup(people2);
		}
		catch (IllegalArgumentException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	public void test44() {
		Person[] people = new Person[] {j, a, div};
		group = new GroupList(people);
		Person[] people2 = new Person[] {r, null};
		boolean caught = false;
		try {
			group.addGroup(people2);
		}
		catch (IllegalArgumentException ex) {
			caught = true;
		}
		assertTrue(caught);
	}
	
	//test usability after exceptions
	
	public void test50() {
		Person[] people = new Person[] {j, a, div};
		group = new GroupList(people);
		try {
			group.closeGroup();
		}
		catch (IllegalStateException ex) {
			
		}
		
		List<Person> next = group.removeNextGroup();
		assertEquals(2, next.size());
		assertEquals(j, next.get(0));
		assertEquals(a, next.get(1));
		assertTrue(group.readyForGroup());
		
		group.addIndividual(r);
		group.addIndividual(a);
		next = group.removeNextGroup();
		assertEquals(2, next.size());
		assertEquals(r, next.get(0));
		assertEquals(a, next.get(1));
	}
	
	public void test51() {
		group = new GroupList();
		try {
			group.removeNextGroup();
		}
		catch (IllegalStateException ex) {
			
		}
		
		group.addIndividual(r);
		group.addIndividual(a);
		List<Person> next = group.removeNextGroup();
		assertEquals(2, next.size());
		assertEquals(r, next.get(0));
		assertEquals(a, next.get(1));
	}
	
	public void test52() {
		Person[] people = new Person[] {j, a, div};
		group = new GroupList(people);
		try {
			group.addIndividual(null);
		}
		catch (IllegalArgumentException ex) {
			
		}
		try {
			group.addIndividual(div);
		}
		catch (IllegalArgumentException ex) {
			
		}
		
		List<Person> next = group.removeNextGroup();
		assertEquals(2, next.size());
		assertEquals(j, next.get(0));
		assertEquals(a, next.get(1));
		assertTrue(group.readyForGroup());
		
		group.addIndividual(r);
		group.addIndividual(a);
		next = group.removeNextGroup();
		assertEquals(2, next.size());
		assertEquals(r, next.get(0));
		assertEquals(a, next.get(1));
	}
	
	public void test53() {
		Person[] people = new Person[] {j, a, div};
		group = new GroupList(people);
		try {
			group.addGroup(new Person[0]);
		}
		catch (IllegalArgumentException ex) {
			
		}
		assertTrue(group.readyForGroup());
		
		List<Person> next = group.removeNextGroup();
		assertEquals(2, next.size());
		assertEquals(j, next.get(0));
		assertEquals(a, next.get(1));
		assertTrue(group.readyForGroup());
		
		group.addIndividual(r);
		group.addIndividual(a);
		next = group.removeNextGroup();
		assertEquals(2, next.size());
		assertEquals(r, next.get(0));
		assertEquals(a, next.get(1));
	}
	
	public void test54() {
		group = new GroupList();
		Person[] people = new Person[] {j, a, div, null, j};
		try {
			group.addGroup(people);
		}
		catch (IllegalArgumentException ex) {
			
		}
		
		assertTrue(group.readyForGroup());
		group.addIndividual(r);
		group.addIndividual(a);
		List<Person> next = group.removeNextGroup();
		assertEquals(2, next.size());
		assertEquals(r, next.get(0));
		assertEquals(a, next.get(1));
	}
	
	public void test55() {
		Person[] people = new Person[] {j, a};
		group = new GroupList(people);
		try {
			group.addGroup(people);
		}
		catch (IllegalStateException ex) {
			
		}
		assertFalse(group.readyForGroup());
		
		List<Person> next = group.removeNextGroup();
		assertEquals(2, next.size());
		assertEquals(j, next.get(0));
		assertEquals(a, next.get(1));
		assertTrue(group.readyForGroup());
		
		group.addIndividual(r);
		group.addIndividual(a);
		next = group.removeNextGroup();
		assertEquals(2, next.size());
		assertEquals(r, next.get(0));
		assertEquals(a, next.get(1));
	}
	
	
}
