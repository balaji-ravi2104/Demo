package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class JunitRunner {
	public static void main(String[] args)  {
		Result result = JUnitCore.runClasses(DemoTest.class);
	}
}
