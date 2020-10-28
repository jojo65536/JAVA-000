# 学习笔记

## GC日志

### 参数
- -Xloggc:
- -XX:+PrintGCDetail
- -XX:+PrintGCDateStamps

### 一般情况

- Young GC可以回收较多内存，Old GC可以回收较小内存
- Young GC耗时远低于Full GC
- 各种GC算法表现差异（见作业）

## JVM线程堆栈分析
- 内部线程：VM线程、定时任务、GC线程、编译器线程、信号分发线程
- 安全点：方法代码中植入，辅助GC进行相关操作
- 工具：jstack

## 内存

### 对象在JVM中的结构
- 对象头：标记字（64位系统4字节）（包含锁、GC标记等信息），Class指针（对应的类）（64位系统4字节），数组长度（64位系统2字节）
- 对象体：数据字段，按8字节对齐

### 内存分析
- 多维数组s[][]，s[x]都是单独的Object，占用空间
- 内存不足
    - Java heap space
        - 堆内存不足以创建新的对象
        - 外部原因：
            - 业务量飙升
            - 内存泄漏，比如Map
    - Metaspace/PermGen Space
        - 主要原因是class数量太多或体积太大
        - 解决办法
            - -XX:MaxPermSize/MaxMetaspaceSize
            - -XX:+CMSClassUnloadingEnable 卸载
    - Unable to create new native thread
        - 线程数量超过上限
        - 解决办法
            - 调整系统参数，文件描述符，线程最大数
            - 降低xss参数？
            - 改代码，优化线程使用

## JVM问题分析
- 分配速率，表示Eden区的使用状况，如果速率偏高，考虑增大Young区，进而减少minor gc次数，只要有少量对象存活，minor gc暂停时间不会明显增加。
- 对象过早提升，表现为每次full gc后，老年代的占用空间较小，说明存活时间不如预期那么长，会导致更早将old区填满频繁full gc。因full gc的暂停时间通常较长，严重情况会影响吞吐量。调整参数--XX:MaxTenuringThreshold
- 尽量让年轻代可以放得下暂存的数据，配置上可以增加空间，业务上可以尝试减少批处理数量。

## 疑难问题

- 可以另作展开







