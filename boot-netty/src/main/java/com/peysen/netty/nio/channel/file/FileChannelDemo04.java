package com.peysen.netty.nio.channel.file;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Auther: peimengmeng
 * @Date: 2021/9/11_18:48
 * @Desc: 使用{@link MappedByteBuffer} 进行文件拷贝
 *      可以让文件直接在系统内存中进行修改，无需让操作系统将数据拷贝到JVM进程中，提升性能
 */
public class FileChannelDemo04 {
    public static void main(String[] args) throws IOException {

        // 创建文件输入，输出流
        RandomAccessFile accessFile = new RandomAccessFile("test1.txt", "rw");
        FileChannel channel = accessFile.getChannel();

        /**
         * 将此通道文件的一个区域直接映射到内存中。
         * 文件的一个区域可以通过以下三种模式之一映射到内存中：
         *      只读：任何修改结果缓冲区的尝试都将导致抛出java.nio.ReadOnlyBufferException 。 ( MapMode.READ_ONLY )
         *      读/写：对结果缓冲区所做的更改最终将传播到文件； 它们可能对映射相同文件的其他程序可见，也可能不可见。 ( MapMode.READ_WRITE )
         *      私有：对结果缓冲区所做的更改不会传播到文件，并且对映射相同文件的其他程序不可见； 相反，它们将导致创建缓冲区修改部分的私有副本。 ( MapMode.PRIVATE )
         * 对于只读映射，此通道必须已打开进行读取； 对于读/写或私有映射，必须已打开此通道进行读和写。
         * 此方法返回的mapped byte buffer的位置为零，限制和容量为size ； 它的标记将是未定义的。 缓冲区及其表示的映射将保持有效，直到缓冲区本身被垃圾收集。
         * 映射一旦建立，就不再依赖于用于创建它的文件通道。 特别是关闭通道对映射的有效性没有影响。
         * 内存映射文件的许多细节本质上取决于底层操作系统，因此未指定。 当请求的区域未完全包含在此频道的文件中时，此方法的行为未指定。 未指定此程序或其他程序对基础文件的内容或大小所做的更改是否传播到缓冲区。 未指定缓冲区更改传播到文件的速率。
         * 对于大多数操作系统，文件映射到内存比读，或通过一般书写的几十数据的千字节的更昂贵的read和write的方法。 从性能的角度来看，通常只值得将相对较大的文件映射到内存中。
         */
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(new byte[]{98,99,100});

        /**
         * 关闭相关的流
         */
        channel.close();
        accessFile.close();
    }

}
