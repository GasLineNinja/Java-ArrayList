import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;

public class TestClass extends TestCase{

	Person div = new Person();
	Person j = new Person("John");
	Person a = new Person("Aaron");
	Person r = new Person("Rohit");
	
	GroupList group;

	
	
	public void setUp(){
		
	}
	
	//testing constructors and readyForGroup
	public void test00() {
		group = new GroupList();
		assertTrue(group.readyForGroup());
	}
	
	public void test01() {
		Person[] people = new Person[0];
		group = new GroupList(people);
		assertTrue(group.readyForGroup());
	}
	
	public void test02() {
		Person[] people = new Person[] {j};
		group = new GroupList(people);
		assertFalse(group.readyForGroup());
	}
	
	public void test03() {
		Person[] people = new Person[] {j, a};
		group = new GroupList(people);
		assertFalse(group.readyForGroup());
	}
	
	public void test04() {
		Person[] people = new Person[] {j, a, div};
		group = new GroupList(people);
		assertTrue(group.readyForGroup());
	}
	
	public void test05() {
		Person[] people = new Person[] {j, a, div, r, div};
		group = new GroupList(people);
		assertTrue(group.readyForGroup());
	}
	
	public void test06() {
		Person[] people = new Person[] {j, a, div, r, j};
		group = new GroupList(people);
		assertFalse(group.readyForGroup());
	}
	
	//testing closeGroup
	
	public void test10() {
		Person[] people = new Person[] {j};
		group = new GroupList(people);
		assertFalse(group.readyForGroup());
		group.closeGroup();
		assertTrue(group.readyForGroup());
	}
	
	public void test11() {
		Person[] people = new Person[] {j, a};
		group = new GroupList(people);
		assertFalse(group.readyForGroup());
		group.closeGroup();
		assertTrue(group.readyForGroup());
	}
	
	public void test12() {
		Person[] people = new Person[] {j, a, div, r, j};
		group = new GroupList(people);
		assertFalse(group.readyForGroup());
		group.closeGroup();
		assertTrue(group.readyForGroup());
	}
	
	//testing removeNextGroup
	
	public void test20() {
		Person[] people = new Person[] {j};
		group = new GroupList(people);
		List<Person> next = group.removeNextGroup();
		assertEquals(1, next.size());
		assertEquals(j, next.get(0));
		assertTrue(group.readyForGroup());
	}
	
	public void test21() {
		Person[] people = new Person[] {j, a, div};
		group = new GroupList(people);
		List<Person> next = group.removeNextGroup();
		assertEquals(2, next.size());
		assertEquals(j, next.get(0));
		assertEquals(a, next.get(1));
		assertTrue(group.readyForGroup());
	}
	
	public void test22() {
		Person[] people = new Person[] {j, a, div, r, div};
		group = new GroupList(people);
		List<Person> next = group.removeNextGroup();
		assertEquals(2, next.size());
		assertEquals(j, next.get(0));
		assertEquals(a, next.get(1));
		assertTrue(group.readyForGroup());
		next = group.removeNextGroup();
		assertEquals(1, next.size());
		assertEquals(r, next.get(0));
		assertTrue(group.readyForGroup());
	}
	
	public void test23() {
		Person[] people = new Person[] {j, a, div, r, j};
		group = new GroupList(people);
		List<Person> next = group.removeNextGroup();
		assertEquals(2, next.size());
		assertEquals(j, next.get(0));
		assertEquals(a, next.get(1));
		assertFalse(group.readyForGroup());
		next = group.removeNextGroup();
		assertEquals(2, next.size());
		assertEquals(r, next.get(0));
		assertEquals(j, next.get(1));
		assertTrue(group.readyForGroup());
	}
	
	//testing addIndividual
	
	public void test30() {
		group = new GroupList();
		group.addIndividual(j);
		assertFalse(group.readyForGroup());
		List<Person> next = group.removeNextGroup();
		assertEquals(1, next.size());
		assertEquals(j, next.get(0));
	}
	
	public void test31() {
		group = new GroupList(new Person[0]);
		group.addIndividual(r);
		group.addIndividual(a);
		assertFalse(group.readyForGroup());
		group.closeGroup();
		assertTrue(group.readyForGroup());
		List<Person> next = group.removeNextGroup();
		assertEquals(2, next.size());
		assertEquals(r, next.get(0));
		assertEquals(a, next.get(1));
	}
	
	public void test32() {
		Person[] people = new Person[] {j, a};
		group = new GroupList(people);
		group.addIndividual(r);
		group.closeGroup();
		group.addIndividual(r);
		List<Person> next = group.removeNextGroup();
		assertEquals(3, next.size());
		assertEquals(j, next.get(0));
		assertEquals(a, next.get(1));
		assertEquals(r, next.get(2));
		assertFalse(group.readyForGroup());
		next = group.removeNextGroup();
		assertEquals(1, next.size());
		assertEquals(r, next.get(0));
	}
	
	public void test33() {
		Person[] people = new Person[] {j, a, div};
		group = new GroupList(people);
		group.addIndividual(r);
		List<Person> next = group.removeNextGroup();
		assertEquals(2, next.size());
		assertEquals(j, next.get(0));
		assertEquals(a, next.get(1));
		group.addIndividual(r);
		next = group.removeNextGroup();
		assertEquals(2, next.size());
		assertEquals(r, next.get(0));
		assertEquals(r, next.get(1));
	}
	
	//testing addGroup
	
	public void test40() {
		group = new GroupList();
		Person[] people = new Person[] {j};
		group.addGroup(people);
		assertTrue(group.readyForGroup());
		List<Person> next = group.removeNextGroup();
		assertEquals(1, next.size());
		assertEquals(j, next.get(0));
	}
	
	public void test41() {
		group = new GroupList();
		Person[] people = new Person[] {j, a};
		group.addGroup(people);
		assertTrue(group.readyForGroup());
		List<Person> next = group.removeNextGroup();
		assertEquals(2, next.size());
		assertEquals(j, next.get(0));
		assertEquals(a, next.get(1));
	}
	
	public void test42() {
		group = new GroupList();
		Person[] people = new Person[] {j, a};
		Person[] people2 = new Person[] {r, j};
		group.addGroup(people);
		group.addGroup(people2);
		List<Person> next = group.removeNextGroup();
		assertEquals(2, next.size());
		assertEquals(j, next.get(0));
		assertEquals(a, next.get(1));
		group.addGroup(people);
		next = group.removeNextGroup();
		assertEquals(2, next.size());
		assertEquals(r, next.get(0));
		assertEquals(j, next.get(1));
		next = group.removeNextGroup();
		assertEquals(2, next.size());
		assertEquals(j, next.get(0));
		assertEquals(a, next.get(1));
	}
	
	public void test43() {
		Person[] people = new Person[] {j, a, div, r};
		group = new GroupList(people);
		group.closeGroup();
		Person[] people2 = new Person[] {a};
		group.addGroup(people2);
		group.addGroup(people2);
		List<Person> next = group.removeNextGroup();
		assertEquals(2, next.size());
		assertEquals(j, next.get(0));
		assertEquals(a, next.get(1));
		next = group.removeNextGroup();
		assertEquals(1, next.size());
		assertEquals(r, next.get(0));
		next = group.removeNextGroup();
		assertEquals(1, next.size());
		assertEquals(a, next.get(0));
		next = group.removeNextGroup();
		assertEquals(1, next.size());
		assertEquals(a, next.get(0));
		group.addGroup(people2);
		next = group.removeNextGroup();
		assertEquals(1, next.size());
		assertEquals(a, next.get(0));
	}
	
	//testing containsInOrder
	
	public void test50() {
		group = new GroupList();
		List<Person> list = new LinkedList<Person>();
		assertTrue(group.containsInOrder(list));
	}
	
	public void test51() {
		group = new GroupList();
		assertFalse(group.containsInOrder(null));
	}
	
	public void test52() {
		Person[] people = new Person[] {j, a};
		group = new GroupList(people);
		List<Person> list = new LinkedList<Person>();
		list.add(j);
		list.add(a);
		assertTrue(group.containsInOrder(list));
	}
	
	public void test53() {
		Person[] people = new Person[] {j, a};
		group = new GroupList(people);
		List<Person> list = new LinkedList<Person>();
		assertFalse(group.containsInOrder(list));
	}
	
	public void test54() {
		group = new GroupList();
		List<Person> list = new LinkedList<Person>();
		list.add(j);
		list.add(a);
		assertFalse(group.containsInOrder(list));
	}
	
	public void test55() {
		Person[] people = new Person[] {j, a};
		group = new GroupList(people);
		List<Person> list = new LinkedList<Person>();
		list.add(a);
		list.add(j);
		assertFalse(group.containsInOrder(list));
	}
	
	public void test56() {
		Person[] people = new Person[] {j, div, a};
		group = new GroupList(people);
		List<Person> list = new LinkedList<Person>();
		list.add(j);
		list.add(a);
		assertTrue(group.containsInOrder(list));
	}
	
	public void test57() {
		Person[] people = new Person[] {j, a};
		group = new GroupList(people);
		List<Person> list = new LinkedList<Person>();
		list.add(j);
		list.add(div);
		list.add(a);
		assertTrue(group.containsInOrder(list));
	}
	
	public void test58() {
		Person[] people = new Person[] {j, a, div};
		group = new GroupList(people);
		List<Person> list = new LinkedList<Person>();
		list.add(j);
		list.add(div);
		list.add(a);
		assertTrue(group.containsInOrder(list));
	}
	
	public void test59() {
		Person[] people = new Person[] {j, a, div, a, div};
		group = new GroupList(people);
		List<Person> list = new LinkedList<Person>();
		list.add(j);
		list.add(a);
		list.add(div);
		list.add(a);
		assertTrue(group.containsInOrder(list));
	}
	
	public void test60() {
		Person[] people = new Person[] {j, a};
		group = new GroupList(people);
		List<Person> list = new LinkedList<Person>();
		list.add(div);
		list.add(div);
		list.add(div);
		list.add(div);
		list.add(div);
		list.add(j);
		list.add(div);
		list.add(div);
		list.add(div);
		list.add(div);
		list.add(div);
		list.add(a);
		list.add(div);
		list.add(div);
		list.add(div);
		list.add(div);
		list.add(div);
		assertTrue(group.containsInOrder(list));
	}
	
	public void test61() {
		Person[] people = new Person[] {j, a, div};
		group = new GroupList(people);
		List<Person> list = new LinkedList<Person>();
		list.add(j);
		list.add(a);
		list.add(div);
		assertTrue(group.containsInOrder(list));
	}
	
	public void test62() {
		Person[] people = new Person[] {j, a, div};
		group = new GroupList(people);
		List<Person> list = new LinkedList<Person>();
		list.add(j);
		list.add(a);
		list.add(div);
		list.add(div);
		assertTrue(group.containsInOrder(list));
	}
	
	public void test63() {
		Person[] people = new Person[] {j, a, div};
		group = new GroupList(people);
		List<Person> list = new LinkedList<Person>();
		list.add(j);
		list.add(a);
		list.add(div);
		list.add(div);
		list.add(div);
		list.add(div);
		list.add(div);
		list.add(r);
		assertFalse(group.containsInOrder(list));
	}
	
	public void test64() {
		Person[] people = new Person[] {j, div, a, div, r, div};
		group = new GroupList(people);
		List<Person> list = new LinkedList<Person>();
		list.add(div);
		list.add(div);
		list.add(div);
		list.add(j);
		list.add(a);
		list.add(div);
		list.add(div);
		list.add(div);
		list.add(div);
		list.add(div);
		list.add(r);
		list.add(div);
		list.add(div);
		assertTrue(group.containsInOrder(list));
	}
	
	public void test65() {
		Person[] people = new Person[] {j, div, a, div, r, div};
		group = new GroupList(people);
		List<Person> list = new LinkedList<Person>();
		list.add(div);
		list.add(div);
		list.add(div);
		list.add(j);
		list.add(a);
		list.add(div);
		list.add(div);
		list.add(div);
		list.add(div);
		list.add(div);
		list.add(j);
		list.add(div);
		list.add(div);
		assertFalse(group.containsInOrder(list));
	}
	
	public void test66() {
		Person[] people = new Person[] {j, div, a, div, r, div};
		group = new GroupList(people);
		List<Person> list = new LinkedList<Person>();
		list.add(j);
		list.add(a);
		list.add(r);
		assertTrue(group.containsInOrder(list));
	}
	
	public void test67() {
		Person[] people = new Person[] {j, div, a, div, r, div, a};
		group = new GroupList(people);
		List<Person> list = new LinkedList<Person>();
		list.add(j);
		list.add(a);
		list.add(r);
		assertFalse(group.containsInOrder(list));
	}
	
	public void test68() {
		Person[] people = new Person[] {j, a, r};
		group = new GroupList(people);
		List<Person> list = new LinkedList<Person>();
		list.add(j);
		list.add(a);
		list.add(r);
		list.add(div);
		list.add(div);
		list.add(div);
		list.add(div);
		list.add(div);
		list.add(a);
		assertFalse(group.containsInOrder(list));
	}
	
	//test encapsulation on specifying constructor and addGroup
	
	public void test70() {
		Person[] people = new Person[] {j, a, r};
		group = new GroupList(people);
		people[0] = r;
		List<Person> next = group.removeNextGroup();
		assertEquals(j, next.get(0));
	}
	
	public void test71() {
		Person[] people = new Person[] {j, a, r};
		group = new GroupList();
		group.addGroup(people);
		people[0] = r;
		List<Person> next = group.removeNextGroup();
		assertEquals(j, next.get(0));
	}
}
