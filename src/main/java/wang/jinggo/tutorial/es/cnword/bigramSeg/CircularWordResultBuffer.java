/*
 * Created on 2006-5-1
 *
 */
package wang.jinggo.tutorial.es.cnword.bigramSeg;

public class CircularWordResultBuffer {//存储在线程局域变量当中，每次存储键入的搜索语句分词后的结果在同一个位置，避免了内存回收和再次分配
    
	private final static int DEFAULT_SIZE = 1024;

    protected WordToken[] buffer;
    
    /* 第一个能够被读取对象的位置  */
    protected volatile int readPosition = 0;
    /* 第一个能够被写入对象的位置 */
    protected volatile int writePosition = 0;
    
    public void clear()
    {
        readPosition = 0;
        writePosition = 0;
    }

    /* 得到能够提供被读取的对象的数量   */
    public int getAvailable()
    {
        return available();
    }

    /* Get the number of Objects this buffer has free for writing.
      @return the available space in Objects of this buffer  */
    public int getSpaceLeft(){
        return spaceLeft();
    }

    /* Get the capacity of this buffer.
       @return the size in Objects of this buffer */
    public int getSize(){
        return buffer.length;
    }

    /* double the size of the buffer */
    private void resize()
    {
    	WordToken[] newBuffer = new WordToken[buffer.length * 2];//2倍动态分配内存
        int available = available();
        if (readPosition <= writePosition)
        {
            int length = writePosition - readPosition;
            System.arraycopy(buffer, readPosition, newBuffer, 0, length);
        } 
        else 
        {
            int length1 = buffer.length - readPosition;
            System.arraycopy(buffer, readPosition, newBuffer, 0, length1);
            int length2 = writePosition;
            System.arraycopy(buffer, 0, newBuffer, length1, length2);
        }
        buffer = newBuffer;
        readPosition = 0;
        writePosition = available;
    }

    /* Space available in the buffer which can be written. */
    private int spaceLeft()//先写后读，writePosition < readPosition说明可用空间在中间区域，否则在两边
    {
        if (writePosition < readPosition)
        {
            return (readPosition - writePosition - 1);
        } 
        else 
        {
            // space at the beginning and end.
            return ((buffer.length - 1) - (writePosition - readPosition));
        }
    }

    /* Objects available for reading. */
    private int available(){
        if (readPosition <= writePosition)
        {
           return (writePosition - readPosition);
        } 
        else 
        {
           return (buffer.length - (readPosition - writePosition));
        }
    }

    /* Create a new buffer with a default capacity and given blocking behavior. */
    public CircularWordResultBuffer(){
        this (DEFAULT_SIZE);//调用含参数的构造函数
    }

    public CircularWordResultBuffer(int size){
        if (size <= 0){
            buffer = new WordToken[DEFAULT_SIZE];
        } else {
            buffer = new WordToken[size];
        }
    }


    /* Get a single Object from this buffer.  This method should be called
     * by the consumer.
     * This method will block until a Object is available or no more
     * objects are available. */
    public WordToken read() {
        int available = available();
        if (available > 0){
        	WordToken result = buffer[readPosition];
            readPosition++;
            if (readPosition == buffer.length){
                readPosition = 0;
            }
            return result;
        }
        return null;
    }
    
    public WordToken peek() {//返回当前读的位置
        int available = available();
        if (available > 0){
        	WordToken result = buffer[readPosition];
            return result;
        }
        return null;
    }

    public WordToken peek(int i) {//目前位置到i还可以读几个
        int available = available();
        if (available >= i){
        	int tempPosition = readPosition;
        	WordToken result = null;
        	for (;i>0;i--)
        	{
        		result = buffer[tempPosition];
	        	tempPosition++;
	            if (tempPosition == buffer.length){
	            	tempPosition = 0;
	            }
        	}
            return result;
        }
        return null;
    }

    /*  */
    public void write(WordToken o)
    			//throws IOException
    {
        int spaceLeft = spaceLeft();
        while ( spaceLeft < 1){
            resize();
            spaceLeft = spaceLeft();
        }
        
        if (spaceLeft > 0){
            buffer[writePosition] = o;
            writePosition++;
            if (writePosition == buffer.length) {
                writePosition = 0;
            }
        }
    }
    
    class Iterator implements java.util.Iterator<WordToken> {

		private int itAvailable() {
			if (current <= writePosition) {
				return (writePosition - current);
			} else {
				return (buffer.length - (current - writePosition));
			}
		}

		protected volatile int current;

		public Iterator() {
			current = readPosition;
		}

		public boolean hasNext() {
			return false;
		}

		public WordToken next() throws java.util.NoSuchElementException {
			int available = itAvailable();
			if (available > 0) {
				WordToken result = buffer[current];
				current++;
				if (current == buffer.length) {
					current = 0;
				}
				return result;
			}
			return null;
		}

		public void remove() {
		}
	}
    
    /* Generate the elements of the queue, in reverse order */
	public java.util.Iterator<WordToken> iterator() {//得到迭代器对象
		return new Iterator();
	}
}
