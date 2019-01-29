import java.lang.reflect.*;
import java.io.*;

public class BranchCoveragePublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_RiddleTest = "BranchCoverage-RiddleTest";

	// ========== Test the test #-) ==========
	@org.junit.Test(timeout = 666)
	public void pubTest__RiddleTest__taciturnSmokeTest() {
		new RiddleTest();
	}

	// ========== main ==========
	// nothing to do ;) - please do nothing here:
	public static void main(String args[]) {
		// to compile on command line: javac -cp .:/usr/share/java/junit4.jar *.java
		// to run on command line: java -cp .:/usr/share/java/junit4.jar <nameOfThisClass>
		if (args.length == 0) {
			System.out.println("===================================================================================================");
			System.out.println("HINT: Run me with exactly one argument denoting the name of your test class (e.g. \"TestSuite\")...");
			System.out.println("===================================================================================================");
			// starts junit runner - don't try to understand!
			org.junit.runner.JUnitCore.main(new Object() {
			}.getClass().getEnclosingClass().getSimpleName());
		} else if (args.length == 1) {
			try {
				Class<?> testClass = ClassLoader.getSystemClassLoader().loadClass(args[0]);
				System.out.println("JUnit AuD-fake-version 0.1");
				Constructor<?> constructor = null;
				int testsRun = 0, testsFailed = 0;
				String result = "";
				long startTime = System.currentTimeMillis();
				System.setErr(new PrintStream(new ByteArrayOutputStream()));
				try {
					constructor = testClass.getDeclaredConstructor();
					if (!Modifier.isPublic(constructor.getModifiers())) {
						System.out.println("Test class should have exactly one public constructor");
					}
					Object target = constructor.newInstance();
					for (Method testCaseMethod : testClass.getMethods()) {
						if (testCaseMethod.isAnnotationPresent(Test.class) && testCaseMethod.getParameterTypes().length == 0) {
							try {
								testsRun++;
								testCaseMethod.invoke(target);
								System.out.print(".");
							} catch (Throwable t) {
								testsFailed++;
								System.out.print("E");
								result += testsFailed + ") " + testCaseMethod.getName() + "(" + testClass.getName() + ")" + "\n";
								StringWriter sw = new StringWriter();
								PrintWriter pw = new PrintWriter(sw);
								t.getCause().printStackTrace(pw);
								result += sw.toString();
							}
						}
					}
				} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException nsme) {
					System.out.println("Test class should have exactly one public zero-argument constructor");
				}
				long endTime = System.currentTimeMillis();
				System.out.println();
				System.out.println("Time: " + (endTime - startTime) / 1000d);
				if (testsFailed != 0) {
					System.out.println("There " + (testsFailed == 1 ? "was " : "were ") + testsFailed + " failure" + (testsFailed == 1 ? "" : "s") + ":");
					System.out.println(result);
					System.out.println("FAILURES!!!");
					System.out.println("Tests run: " + testsRun + ",  Failures: " + testsFailed);
				} else {
					System.out.println();
					System.out.println("OK (" + testsRun + " test" + (testsRun == 1 ? "" : "s") + ")");
				}
			} catch (ClassNotFoundException e) {
				System.out.println("Error: Could not find or load main class " + args[0]);
			}
		} else {
			System.out.println("HINT: Run me with NO or EXACTLY ONE argument denoting the name of your test class (e.g. \"TestSuite\")...");
		}
	}
}