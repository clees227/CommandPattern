package headfirst.command.simpleremote;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LightOnCommandTest {

	PrintStream ps;
	LightOnCommand lC;
	PrintStream def;
	ByteArrayOutputStream bs;
	@Before
	public void setUp() throws Exception {
		def = System.out;
		bs = new ByteArrayOutputStream();
		ps = new PrintStream(bs);
		System.setOut(ps);
		Light light = new Light();
		lC = new LightOnCommand(light);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExecute() {
		lC.execute();
		String output = new String(bs.toByteArray());
		System.setOut(def);
		System.out.println(output);
		assertEquals("Light is on\n", output);
	}

}
