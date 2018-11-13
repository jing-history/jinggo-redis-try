package wang.jinggo.tutorial.es.cnword.bigramSeg;

/**
 * 存储线程局域变量
 */
public class ThreadContext {
	public CircularWordResultBuffer result;
	
	private ThreadContext(){
		result = new CircularWordResultBuffer();		
	}

	private static class ThreadLocalInstance extends ThreadLocal<ThreadContext> {
		public ThreadContext initialValue()
	    {
			return new ThreadContext();// 类里创建对象会调用私有构造函数，进而调用new CircularWordResultBuffer();	
		}
	}
	
	private static ThreadLocalInstance _theInstanceWrapped = new ThreadLocalInstance();

	public static ThreadContext getInstance() {
		return _theInstanceWrapped.get();// _theInstanceWrapped.get()会调用initialValue()
	}
}