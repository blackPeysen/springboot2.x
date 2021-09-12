package com.peysen.netty.nio.buffer;

import java.nio.IntBuffer;

/**
 * @Auther: peimengmeng
 * @Date: 2021/9/11_07:42
 * @Desc:
 */
public class BufferDemo {

    public static void main(String[] args) {
        /**
         * 创建一个Buffer
         */
        IntBuffer allocate = IntBuffer.allocate(5);

        for(int i=0;i<allocate.capacity();i++){
            allocate.put(i*2);
        }

        /**
         * 数组超过capacity后，再写入数据，就数据越界，报错
         */
        allocate.put(10);

        /**
         * 将buffer进行转换，读写切换；
         *      比如：
         *      a、先写后读：
         *          put是写操作，写N个元素之后，position移动到N，position=N
         *          flip之后，将position置为0，表示从0位置开始读取，最多可以读到到之前position的位置
         *       b、后读后写：
         *          get是读操作，读N个元素之后，position移动到N，position=N
         *          clear之后，将所有的元素都清空，再调用flip，这样就可以继续从0位置继续写入数据。
         *
         *  limit = position;
         *  position = 0;
         *  mark = -1;
         */
        allocate.flip();

        for(int i=0;i<allocate.limit();i++){
            System.out.println(i + ":" + allocate.get(i));
        }
    }

}
