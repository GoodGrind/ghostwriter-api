package io.ghostwriter;

import java.util.ArrayList;
import java.util.List;

public class ScottRuntime {
	
	private static ThreadLocal<List<Object>> params = new ThreadLocal<List<Object>>();
	
	public static void trackMethodStart(int lineNumber, String methodName, Class<?> clazz) {
		params.set(new ArrayList<>());
	}
	
	public static void trackEndOfArgumentsAtMethodStart(int lineNumber, String methodName, Class<?> clazz) {
		Object[] paramsArray = params.get().toArray(new Object[params.get().size()]);
		params.set(null);
		GhostWriter.entering(clazz, methodName, paramsArray);
	}
	
	public static void trackLocalVariableState(Object value, String name, int lineNumber, String methodName, Class<?> clazz) {
		boolean argumentTracking = params.get() != null;
		
		if (argumentTracking) {
			params.get().add(name);
			params.get().add(value);
		} else {
			GhostWriter.valueChange(clazz, methodName, name, value);
		}
	}
	
	public static void trackLocalVariableState(byte value, String name, int lineNumber, String methodName, Class<?> clazz) {
		trackLocalVariableState(Byte.valueOf(value), name, lineNumber, methodName, clazz);
	}

	public static void trackLocalVariableState(short value, String name, int lineNumber, String methodName, Class<?> clazz) {
		trackLocalVariableState(Short.valueOf(value), name, lineNumber, methodName, clazz);
	}

	public static void trackLocalVariableState(int value, String name, int lineNumber, String methodName, Class<?> clazz) {
		trackLocalVariableState(Integer.valueOf(value), name, lineNumber, methodName, clazz);
	}

	public static void trackLocalVariableState(long value, String name, int lineNumber, String methodName, Class<?> clazz) {
		trackLocalVariableState(Long.valueOf(value), name, lineNumber, methodName, clazz);
	}

	public static void trackLocalVariableState(float value, String name, int lineNumber, String methodName, Class<?> clazz) {
		trackLocalVariableState(Float.valueOf(value), name, lineNumber, methodName, clazz);
	}

	public static void trackLocalVariableState(double value, String name, int lineNumber, String methodName, Class<?> clazz) {
		trackLocalVariableState(Double.valueOf(value), name, lineNumber, methodName, clazz);
	}

	public static void trackLocalVariableState(boolean value, String name, int lineNumber, String methodName, Class<?> clazz) {
		trackLocalVariableState(Boolean.valueOf(value), name, lineNumber, methodName, clazz);
	}

	public static void trackLocalVariableState(char value, String name, int lineNumber, String methodName, Class<?> clazz) {
		trackLocalVariableState(Character.valueOf(value), name, lineNumber, methodName, clazz);
	}
	
	public static void trackUnhandledException(Throwable throwable, int lineNumber, String methodName, Class<?> clazz) {
		GhostWriter.onError(clazz, methodName, throwable);
		GhostWriter.exiting(clazz, methodName);
	}
	
	public static void trackReturn(int lineNumber, String methodName, Class<?> clazz) {
		GhostWriter.exiting(clazz, methodName);
	}
	
	public static void trackReturn(Object value, int lineNumber, String methodName, Class<?> clazz) {
		GhostWriter.returning(clazz, methodName, value);
		GhostWriter.exiting(clazz, methodName);
	}

	public static void trackReturn(byte value, int lineNumber, String methodName, Class<?> clazz) {
		trackReturn(Byte.valueOf(value), lineNumber, methodName, clazz);
	}

	public static void trackReturn(short value, int lineNumber, String methodName, Class<?> clazz) {
		trackReturn(Short.valueOf(value), lineNumber, methodName, clazz);
	}

	public static void trackReturn(int value, int lineNumber, String methodName, Class<?> clazz) {
		trackReturn(Integer.valueOf(value), lineNumber, methodName, clazz);
	}

	public static void trackReturn(long value, int lineNumber, String methodName, Class<?> clazz) {
		trackReturn(Long.valueOf(value), lineNumber, methodName, clazz);
	}

	public static void trackReturn(float value, int lineNumber, String methodName, Class<?> clazz) {
		trackReturn(Float.valueOf(value), lineNumber, methodName, clazz);
	}

	public static void trackReturn(double value, int lineNumber, String methodName, Class<?> clazz) {
		trackReturn(Double.valueOf(value), lineNumber, methodName, clazz);
	}

	public static void trackReturn(boolean value, int lineNumber, String methodName, Class<?> clazz) {
		trackReturn(Boolean.valueOf(value), lineNumber, methodName, clazz);
	}

	public static void trackReturn(char value, int lineNumber, String methodName, Class<?> clazz) {
		trackReturn(Character.valueOf(value), lineNumber, methodName, clazz);
	}
	
	public static void trackLambdaDefinition(int lineNumber, String methodName, Class<?> clazz) {
		// No-op.
	}
	
	public static void trackFieldState(byte value, String name, int lineNumber, String methodName, Class<?> clazz, boolean isStatic, String owner) {
		// No-op.
	}
	
	public static void trackFieldState(short value, String name, int lineNumber, String methodName, Class<?> clazz, boolean isStatic, String owner) {
		// No-op.
	}
	
	public static void trackFieldState(int value, String name, int lineNumber, String methodName, Class<?> clazz, boolean isStatic, String owner) {
		// No-op.
	}
	
	public static void trackFieldState(long value, String name, int lineNumber, String methodName, Class<?> clazz, boolean isStatic, String owner) {
		// No-op.
	}
	
	public static void trackFieldState(float value, String name, int lineNumber, String methodName, Class<?> clazz, boolean isStatic, String owner) {
		// No-op.
	}
		
	public static void trackFieldState(double value, String name, int lineNumber, String methodName, Class<?> clazz, boolean isStatic, String owner) {
		// No-op.
	}
		
	public static void trackFieldState(boolean value, String name, int lineNumber, String methodName, Class<?> clazz, boolean isStatic, String owner) {
		// No-op.
	}
		
	public static void trackFieldState(char value, String name, int lineNumber, String methodName, Class<?> clazz, boolean isStatic, String owner) {
		// No-op.
	}
		
	public static void trackFieldState(Object value, String name, int lineNumber, String methodName, Class<?> clazz, boolean isStatic, String owner) {
		// No-op.
	}
	
}
