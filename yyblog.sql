/*
Navicat MySQL Data Transfer

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2018-06-21 22:09:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_about
-- ----------------------------
DROP TABLE IF EXISTS `t_about`;
CREATE TABLE `t_about` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `tab` varchar(50) NOT NULL,
  `content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_about
-- ----------------------------
INSERT INTO `t_about` VALUES ('1', '关于「博客系统」', 'about_blog', '<p> </p><div class=\"layui-row layui-col-space20 layui-mt10\">此处可去后台 内容管理-&gt; 关于内容 处设置&nbsp;&nbsp;<br></div>');
INSERT INTO `t_about` VALUES ('2', '关于「我」', 'about_me', '<p>此处可去后台 内容管理-> 关于内容 处设置</p>');
INSERT INTO `t_about` VALUES ('3', '关于「网站」', 'about_website', '<p></p><p>此处可去后台 内容管理-&gt; 关于内容 处设置aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</p><p></p><p><br></p>');

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` bigint(20) unsigned NOT NULL,
  `title` varchar(100) NOT NULL,
  `cate_id` bigint(20) NOT NULL,
  `cover` varchar(255) DEFAULT NULL,
  `summary` varchar(300) DEFAULT NULL,
  `content` text NOT NULL,
  `text_content` text NOT NULL,
  `views` int(11) NOT NULL DEFAULT '0' COMMENT '浏览数',
  `approve_cnt` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `commented` tinyint(1) NOT NULL DEFAULT '1',
  `appreciable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否开放赞赏',
  `draft` tinyint(1) NOT NULL DEFAULT '1',
  `top` tinyint(1) NOT NULL DEFAULT '0' COMMENT '排序顺序，置顶文章用',
  `author_id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES ('152759704083198', 'Java多线程与并发编程学习', '152759695653794', 'http://images.laoyeye.net/1527597023959928.png', '一、线程三大特性多线程有三大特性，原子性、可见性、有序性1.1什么是原子性即一个操作或者多个操作要么全部执行并且执行的过程不会被任何因素打断，要么就都不执行。一个很经典的例子就是银行账户转账问题：&nbsp;比如从账户A向账户B转1000元，那么必然包括2个操作：从账户A减去1000元，往账户B加上1000元。这2个操作必须要具备原子性才能保证不出现一些意外的问题。我们操作数据也是如此，比如i=i+1；其中就包括，读取i的值，计算i，写入i。这行代码在Java中是不具备原子性的，则', '<p style=\"text-indent:2em;\">\n	本文原由小卖铺的老爷爷发表于<a href=\"https://www.cnblogs.com/laoyeye/p/7636788.html\" target=\"_blank\">博客园</a>\n</p>\n<h1 style=\"text-indent:2em;\">\n	一、线程三大特性\n</h1>\n<p style=\"text-indent:2em;\">\n	多线程有三大特性，原子性、可见性、有序性\n</p>\n<h2 style=\"text-indent:2em;\">\n	1.1 什么是原子性\n</h2>\n<p style=\"text-indent:2em;\">\n	即一个操作或者多个操作 要么全部执行并且执行的过程不会被任何因素打断，要么就都不执行。<br />\n一个很经典的例子就是银行账户转账问题：&nbsp;<br />\n比如从账户A向账户B转1000元，那么必然包括2个操作：从账户A减去1000元，往账户B加上1000元。这2个操作必须要具备原子性才能保证不出现一些意外的问题。<br />\n我们操作数据也是如此，比如i = i+1；其中就包括，读取i的值，计算i，写入i。这行代码在Java中是不具备原子性的，则多线程运行肯定会出问题，所以也需要我们使用同步和lock这些东西来确保这个特性了。&nbsp;<br />\n原子性其实就是保证数据一致、线程安全一部分，\n</p>\n<h2 style=\"text-indent:2em;\">\n	1.2 什么是可见性\n</h2>\n<p style=\"text-indent:2em;\">\n	当多个线程访问同一个变量时，一个线程修改了这个变量的值，其他线程能够立即看得到修改的值。<br />\n若两个线程在不同的cpu，那么线程1改变了i的值还没刷新到主存，线程2又使用了i，那么这个i值肯定还是之前的，线程1对变量的修改线程没看到这就是可见性问题。&nbsp;\n</p>\n<h2 style=\"text-indent:2em;\">\n	1.3什么是有序性\n</h2>\n<p style=\"text-indent:2em;\">\n	程序执行的顺序按照代码的先后顺序执行。<br />\n一般来说处理器为了提高程序运行效率，可能会对输入代码进行优化，它不保证程序中各个语句的执行先后顺序同代码中的顺序一致，但是它会保证程序最终执行结果和代码顺序执行的结果是一致的。如下：<br />\nint a = 10;    //语句1<br />\nint r = 2;    //语句2<br />\na = a + 3;    //语句3<br />\nr = a*a;     //语句4<br />\n则因为重排序，他还可能执行顺序为 2-1-3-4，1-3-2-4<br />\n但绝不可能 2-1-4-3，因为这打破了依赖关系。<br />\n显然重排序对单线程运行是不会有任何问题，而多线程就不一定了，所以我们在多线程编程时就得考虑这个问题了。\n</p>\n<h1 style=\"text-indent:2em;\">\n	二：Java内存模型\n</h1>\n<p style=\"text-indent:2em;\">\n	共享内存模型指的就是Java内存模型(简称JMM)，JMM决定一个线程对共享变量的写入时,能对另一个线程可见。从抽象的角度来看，JMM定义了线程和主内存之间的抽象关系：线程之间的共享变量存储在主内存（main memory）中，每个线程都有一个私有的本地内存（local memory），本地内存中存储了该线程以读/写共享变量的副本。本地内存是JMM的一个抽象概念，并不真实存在。它涵盖了缓存，写缓冲区，寄存器以及其他的硬件和编译器优化。\n</p>\n<p style=\"text-indent:2em;\">\n	<img src=\"http://images2017.cnblogs.com/blog/1075594/201710/1075594-20171008105817356-514001318.png\" alt=\"\" /> \n</p>\n<p style=\"text-indent:2em;\">\n	从上图来看，线程A与线程B之间如要通信的话，必须要经历下面2个步骤：<br />\n1. 首先，线程A把本地内存A中更新过的共享变量刷新到主内存中去。<br />\n2. 然后，线程B到主内存中去读取线程A之前已更新过的共享变量。 <br />\n下面通过示意图来说明这两个步骤：&nbsp;\n</p>\n<p>\n	<img src=\"http://images2017.cnblogs.com/blog/1075594/201710/1075594-20171008105852871-1594576754.png\" alt=\"\" /> \n</p>\n<p style=\"text-indent:2em;\">\n	如上图所示，本地内存A和B有主内存中共享变量x的副本。假设初始时，这三个内存中的x值都为0。线程A在执行时，把更新后的x值（假设值为1）临时存放在自己的本地内存A中。当线程A和线程B需要通信时，线程A首先会把自己本地内存中修改后的x值刷新到主内存中，此时主内存中的x值变为了1。随后，线程B到主内存中去读取线程A更新后的x值，此时线程B的本地内存的x值也变为了1。<br />\n从整体来看，这两个步骤实质上是线程A在向线程B发送消息，而且这个通信过程必须要经过主内存。JMM通过控制主内存与每个线程的本地内存之间的交互，来为java程序员提供内存可见性保证。\n</p>\n<p style=\"text-indent:2em;\">\n	<span style=\"color:#ff0000;\"><strong>总结</strong><strong><span style=\"font-family:宋体;\">：什么是</span></strong><strong>J</strong><strong>ava<span style=\"font-family:宋体;\">内存模型：</span><span style=\"font-family:Calibri;\">java</span><span style=\"font-family:宋体;\">内存模型</span></strong><strong>简称</strong><strong>jmm<span style=\"font-family:宋体;\">，</span></strong><strong>定</strong><strong><span style=\"font-family:宋体;\">义了</span></strong><strong>一个线程</strong><strong><span style=\"font-family:宋体;\">对</span></strong><strong>另一个</strong><strong><span style=\"font-family:宋体;\">线程可见。</span></strong><strong>共享</strong><strong><span style=\"font-family:宋体;\">变量存放在主内存中，每个线程都有自己的</span></strong><strong>本地</strong><strong><span style=\"font-family:宋体;\">内存，</span></strong><strong>当</strong><strong><span style=\"font-family:宋体;\">多个线程同时访问一个数据的时候，可能本地内存没有及时刷新到主内存，所以就会发生线程</span></strong><strong>安全</strong><strong><span style=\"font-family:宋体;\">问题。</span></strong></span> \n</p>\n<h1 style=\"text-indent:2em;\">\n	三、Volatile关键字\n</h1>\n<h2 style=\"text-indent:2em;\">\n	3.1 什么是Volatile\n</h2>\n<p style=\"text-indent:2em;\">\n	Volatile 关键字的作用是变量在多个线程之间可见。\n</p>\n<div class=\"cnblogs_code\">\n<pre>\n\n\n\n\n<pre class=\"layui-code prettyprint\">class ThreadVolatileDemo extends Thread { public boolean flag = true;\n    @Override public void run() {\n        System.out.println(\"开始执行子线程....\"); while (flag) {\n        }\n        System.out.println(\"线程停止\");\n    } public void setRuning(boolean flag) { this.flag = flag;\n    }\n\n} public class ThreadVolatile { public static void main(String[] args) throws InterruptedException {\n        ThreadVolatileDemo threadVolatileDemo = new ThreadVolatileDemo();\n        threadVolatileDemo.start();\n        Thread.sleep(3000);\n        threadVolatileDemo.setRuning(false);\n        System.out.println(\"flag 已经设置成false\");\n        Thread.sleep(1000);\n        System.out.println(threadVolatileDemo.flag);\n\n    }\n}</pre>\n</pre>\n</div>\n<p style=\"text-indent:2em;\">\n	运行结果:\n</p>\n<p>\n	<img src=\"http://images2017.cnblogs.com/blog/1075594/201710/1075594-20171008112922918-542106488.png\" alt=\"\" /> \n</p>\n<p style=\"text-indent:2em;\">\n	<strong><span style=\"color:#ff0000;\">threadVolatileDemo.flag值也是false,可是为什么程序还是一直在运行呢？</span></strong> \n</p>\n<p style=\"text-indent:2em;\">\n	<strong><span style=\"color:#ff0000;\">原因:线程之间是不可见的，读取的是副本，没有及时读取到主内存结果。</span></strong><br />\n<strong><span style=\"color:#ff0000;\">解决办法：使用Volatile关键字将解决线程之间可见性, 强制线程每次读取该值的时候都去“主内存”中取值。</span></strong> \n</p>\n<h2 style=\"text-indent:2em;\">\n	3.2 Volatile非原子性\n</h2>\n<div class=\"cnblogs_code\">\n<pre><span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">class</span> VolatileNoAtomic <span style=\"color:#0000ff;\">extends</span><span style=\"color:#000000;\"> Thread { </span><span style=\"color:#0000ff;\">private</span> <span style=\"color:#0000ff;\">static</span> <span style=\"color:#0000ff;\">volatile</span> <span style=\"color:#0000ff;\">int</span><span style=\"color:#000000;\"> count; </span><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\"> private static AtomicInteger count = new AtomicInteger(0);</span> <span style=\"color:#0000ff;\">private</span> <span style=\"color:#0000ff;\">static</span> <span style=\"color:#0000ff;\">void</span><span style=\"color:#000000;\"> addCount() { </span><span style=\"color:#0000ff;\">for</span> (<span style=\"color:#0000ff;\">int</span> i = 0; i &lt; 1000; i++<span style=\"color:#000000;\">) {\n            count</span>++<span style=\"color:#000000;\">; </span><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\"> count.incrementAndGet();</span> <span style=\"color:#000000;\"> }\n        System.out.println(count);\n    } </span><span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">void</span><span style=\"color:#000000;\"> run() {\n        addCount();\n    } </span><span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">static</span> <span style=\"color:#0000ff;\">void</span><span style=\"color:#000000;\"> main(String[] args) {\n\n        VolatileNoAtomic[] arr </span>= <span style=\"color:#0000ff;\">new</span> VolatileNoAtomic[100<span style=\"color:#000000;\">]; </span><span style=\"color:#008000;\"> //</span><span style=\"color:#008000;\"> 初始化10个线程</span> <span style=\"color:#0000ff;\">for</span> (<span style=\"color:#0000ff;\">int</span> i = 0; i &lt; 10; i++<span style=\"color:#000000;\">) {\n            arr[i] </span>= <span style=\"color:#0000ff;\">new</span><span style=\"color:#000000;\"> VolatileNoAtomic();\n        } </span><span style=\"color:#0000ff;\">for</span> (<span style=\"color:#0000ff;\">int</span> i = 0; i &lt; 10; i++<span style=\"color:#000000;\">) {\n            arr[i].start();\n        }\n    }\n\n}</span></pre>\n</div>\n<p style=\"text-indent:2em;\">\n	<span style=\"font-family:宋体;\">运行结果</span>:\n</p>\n<p>\n	<img src=\"http://images2017.cnblogs.com/blog/1075594/201710/1075594-20171008113355653-747283103.png\" alt=\"\" /> \n</p>\n<p style=\"text-indent:2em;\">\n	<span style=\"color:#ff0000;\"><strong>结果发现数据不同步，因为Volatile不用具备原子性。所以Volatile只能解决将将结果刷新到主内存中去，并不能解决并发原子性问题。</strong></span> \n</p>\n<h2 style=\"text-indent:2em;\">\n	3.3 使用AtomicInteger<span style=\"font-family:宋体;\">原子类</span> \n</h2>\n<p style=\"text-indent:2em;\">\n	AtomicInteger是一个提供原子操作的Integer类，通过线程安全的方式操作加减，因此十分适合高并发情况下的使用，来源于java并发包。\n</p>\n<p style=\"text-indent:2em;\">\n	修改上面的代码：\n</p>\n<div class=\"cnblogs_code\">\n<pre><span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">class</span> VolatileNoAtomic <span style=\"color:#0000ff;\">extends</span><span style=\"color:#000000;\"> Thread { </span><span style=\"color:#0000ff;\"> private</span> <span style=\"color:#0000ff;\">static</span> AtomicInteger atomicInteger = <span style=\"color:#0000ff;\">new</span> AtomicInteger(0<span style=\"color:#000000;\">);\n\n    @Override </span><span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">void</span><span style=\"color:#000000;\"> run() { </span><span style=\"color:#0000ff;\">for</span> (<span style=\"color:#0000ff;\">int</span> i = 0; i &lt; 1000; i++<span style=\"color:#000000;\">) { </span><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\">等同于count++ </span> <span style=\"color:#000000;\"> atomicInteger.incrementAndGet();\n        }\n        System.out.println(atomicInteger);\n    } </span><span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">static</span> <span style=\"color:#0000ff;\">void</span><span style=\"color:#000000;\"> main(String[] args) { </span><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\"> 初始化10个线程</span> VolatileNoAtomic[] volatileNoAtomic = <span style=\"color:#0000ff;\">new</span> VolatileNoAtomic[10<span style=\"color:#000000;\">]; </span><span style=\"color:#0000ff;\">for</span> (<span style=\"color:#0000ff;\">int</span> i = 0; i &lt; 10; i++<span style=\"color:#000000;\">) { </span><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\"> 创建</span> volatileNoAtomic[i] = <span style=\"color:#0000ff;\">new</span><span style=\"color:#000000;\"> VolatileNoAtomic();\n        } </span><span style=\"color:#0000ff;\">for</span> (<span style=\"color:#0000ff;\">int</span> i = 0; i &lt; volatileNoAtomic.length; i++<span style=\"color:#000000;\">) {\n            volatileNoAtomic[i].start();\n        }\n    }\n\n}</span></pre>\n</div>\n<p style=\"text-indent:2em;\">\n	运行结果：\n</p>\n<p>\n	<img src=\"http://images2017.cnblogs.com/blog/1075594/201710/1075594-20171008120251356-1010404030.png\" alt=\"\" /> \n</p>\n<h2 style=\"text-indent:2em;\">\n	3.4 volatile与synchronized区别\n</h2>\n<p style=\"text-indent:2em;\">\n	仅靠volatile不能保证线程的安全性。（原子性）<br />\n①volatile轻量级，只能修饰变量。synchronized重量级，还可修饰方法<br />\n②volatile只能保证数据的可见性，不能用来同步（没有原子性，不能保证线程安全），因为多个线程并发访问volatile修饰的变量不会阻塞。<br />\n③synchronized不仅保证可见性，而且还保证原子性，因为，只有获得了锁的线程才能进入临界区，从而保证临界区中的所有语句都全部执行。多个线程争抢synchronized锁对象时，会出现阻塞。<br />\n总结：<br />\n线程安全性包括两个方面，①可见性。②原子性。<br />\n从上面自增的例子中可以看出：仅仅使用volatile并不能保证线程安全性。而synchronized则可实现线程的安全性。\n</p>\n<h1 style=\"text-indent:2em;\">\n	四、ThreadLocal\n</h1>\n<h2 style=\"text-indent:2em;\">\n	4.1、什么是ThreadLocal\n</h2>\n<p style=\"text-indent:2em;\">\n	ThreadLocal提高一个线程的局部变量，<span style=\"color:#ff0000;\">访问某个线程拥有自己局部变量。</span><br />\n<span style=\"color:#000000;\">当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。</span><br />\nThreadLocal的接口方法<br />\nThreadLocal类接口很简单，只有4个方法，我们先来了解一下：\n</p>\n<p style=\"text-indent:2em;\">\n	<br />\n</p>\n<ul>\n	<li>\n		void set(Object value)设置当前线程的线程局部变量的值。\n	</li>\n	<li>\n		public Object get()该方法返回当前线程所对应的线程局部变量。\n	</li>\n	<li>\n		public void remove()将当前线程局部变量的值删除，目的是为了减少内存的占用，该方法是JDK 5.0新增的方法。需要指出的是，当线程结束后，对应该线程的局部变量将自动被垃圾回收，所以显式调用该方法清除线程的局部变量并不是必须的操作，但它可以加快内存回收的速度。\n	</li>\n	<li>\n		protected Object initialValue()返回该线程局部变量的初始值，该方法是一个protected的方法，显然是为了让子类覆盖而设计的。这个方法是一个延迟调用方法，在线程第1次调用get()或set(Object)时才执行，并且仅执行1次。ThreadLocal中的缺省实现直接返回一个null。\n	</li>\n</ul>\n<p>\n	<br />\n</p>\n<p style=\"text-indent:2em;\">\n	<br />\n案例:创建三个线程，每个线程生成自己独立序列号。<br />\n代码:\n</p>\n<div class=\"cnblogs_code\">\n<pre><span style=\"color:#0000ff;\">class</span><span style=\"color:#000000;\"> Res { </span><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\"> 生成序列号共享变量 </span><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\">public static Integer count = 0; </span><span style=\"color:#008000;\"> //</span><span style=\"color:#008000;\"> 设置本地局部变量，与其他线程互不影响</span> <span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">static</span> ThreadLocal&lt;Integer&gt; threadLocal = <span style=\"color:#0000ff;\">new</span> ThreadLocal&lt;Integer&gt;<span style=\"color:#000000;\">() { </span><span style=\"color:#008000;\"> //</span><span style=\"color:#008000;\"> 设置当前线程局部变量初始化值</span> <span style=\"color:#0000ff;\">protected</span><span style=\"color:#000000;\"> Integer initialValue() { </span><span style=\"color:#0000ff;\">return</span> 0<span style=\"color:#000000;\">;\n        };\n\n    }; </span><span style=\"color:#0000ff;\">public</span><span style=\"color:#000000;\"> Integer getNum() { </span><span style=\"color:#0000ff;\">int</span> count = <span style=\"color:#0000ff;\">this</span>.threadLocal.get() + 1<span style=\"color:#000000;\">; </span><span style=\"color:#0000ff;\">this</span><span style=\"color:#000000;\">.threadLocal.set(count); </span><span style=\"color:#0000ff;\">return</span><span style=\"color:#000000;\"> count;\n    }\n} </span><span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">class</span> ThreadLocaDemo2 <span style=\"color:#0000ff;\">extends</span><span style=\"color:#000000;\"> Thread { </span><span style=\"color:#0000ff;\">private</span><span style=\"color:#000000;\"> Res res; </span><span style=\"color:#0000ff;\">public</span><span style=\"color:#000000;\"> ThreadLocaDemo2(Res res) { </span><span style=\"color:#0000ff;\">this</span>.res =<span style=\"color:#000000;\"> res;\n    }\n\n    @Override </span><span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">void</span><span style=\"color:#000000;\"> run() { </span><span style=\"color:#0000ff;\">for</span> (<span style=\"color:#0000ff;\">int</span> i = 0; i &lt; 3; i++<span style=\"color:#000000;\">) {\n            System.out.println(Thread.currentThread().getName() </span>+ \"---\" + \"i---\" + i + \"--num:\" +<span style=\"color:#000000;\"> res.getNum());\n        }\n\n    } </span><span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">static</span> <span style=\"color:#0000ff;\">void</span><span style=\"color:#000000;\"> main(String[] args) {\n        Res res </span>= <span style=\"color:#0000ff;\">new</span><span style=\"color:#000000;\"> Res();\n        ThreadLocaDemo2 threadLocaDemo1 </span>= <span style=\"color:#0000ff;\">new</span><span style=\"color:#000000;\"> ThreadLocaDemo2(res);\n        ThreadLocaDemo2 threadLocaDemo2 </span>= <span style=\"color:#0000ff;\">new</span><span style=\"color:#000000;\"> ThreadLocaDemo2(res);\n        ThreadLocaDemo2 threadLocaDemo3 </span>= <span style=\"color:#0000ff;\">new</span><span style=\"color:#000000;\"> ThreadLocaDemo2(res);\n        threadLocaDemo1.start();\n        threadLocaDemo2.start();\n        threadLocaDemo3.start();\n    }\n\n}</span></pre>\n</div>\n<h2 style=\"text-indent:2em;\">\n	4.2、ThreadLocal实现原理\n</h2>\n<p style=\"text-indent:2em;\">\n	源码：\n</p>\n<div class=\"cnblogs_code\">\n<pre>    <span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">void</span><span style=\"color:#000000;\"> set(T value) {\n        Thread t </span>=<span style=\"color:#000000;\"> Thread.currentThread();\n        ThreadLocalMap map </span>=<span style=\"color:#000000;\"> getMap(t); </span><span style=\"color:#0000ff;\">if</span> (map != <span style=\"color:#0000ff;\">null</span><span style=\"color:#000000;\">)\n            map.set(</span><span style=\"color:#0000ff;\">this</span><span style=\"color:#000000;\">, value); </span><span style=\"color:#0000ff;\">else</span><span style=\"color:#000000;\"> createMap(t, value);\n    }</span></pre>\n</div>\n<p style=\"text-indent:2em;\">\n	从源码中我们可以看出，ThreadLoca通过map集合，Map.put(“当前线程”,值)；\n</p>\n<h1 style=\"text-indent:2em;\">\n	<strong>五、线程池</strong> \n</h1>\n<h2 style=\"text-indent:2em;\">\n	<strong>5.1&nbsp;<span style=\"font-family:宋体;\">什么是线程池？</span></strong> \n</h2>\n<p style=\"text-indent:2em;\">\n	<span>&nbsp;线程池是指在初始化一个多线程应用程序过程中创建一个线程集合，然后在需要执行新的任务时重用这些线程而不是新建一个线程。线程池中线程的数量通常完全取决于可用内存数量和应用程序的需求。然而，增加可用线程数量是可能的。线程池中的每个线程都有被分配一个任务，一旦任务已经完成了，线程回到池子中并等待下一次分配任务。</span> \n</p>\n<h2 style=\"text-indent:2em;\">\n	<strong>5.2&nbsp;<span style=\"font-family:宋体;\">线程池作用</span></strong> \n</h2>\n<p style=\"text-indent:2em;\">\n	基于以下几个原因在多线程应用程序中使用线程是必须的：\n</p>\n<p style=\"text-indent:2em;\">\n	1. 线程池改进了一个应用程序的响应时间。由于线程池中的线程已经准备好且等待被分配任务，应用程序可以直接拿来使用而不用新建一个线程。\n</p>\n<p style=\"text-indent:2em;\">\n	2. 线程池节省了CLR 为每个短生存周期任务创建一个完整的线程的开销并可以在任务完成后回收资源。\n</p>\n<p style=\"text-indent:2em;\">\n	3. 线程池根据当前在系统中运行的进程来优化线程时间片。\n</p>\n<p style=\"text-indent:2em;\">\n	4. 线程池允许我们开启多个任务而不用为每个线程设置属性。\n</p>\n<p style=\"text-indent:2em;\">\n	5. 线程池允许我们为正在执行的任务的程序参数传递一个包含状态信息的对象引用。\n</p>\n<p style=\"text-indent:2em;\">\n	6. 线程池可以用来解决处理一个特定请求最大线程数量限制问题。\n</p>\n<h2 style=\"text-indent:2em;\">\n	<strong>5.3 <span style=\"font-family:宋体;\">线程池四种创建方式</span></strong> \n</h2>\n<p style=\"text-indent:2em;\">\n	Java通过Executors（jdk1.5并发包）提供四种线程池，分别为：<br />\nnewCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。<br />\nnewFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。<br />\nnewScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。<br />\nnewSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。\n</p>\n<h2 style=\"text-indent:2em;\">\n	5.4 <span style=\"font-family:宋体;\">代码Demo</span><span style=\"font-family:宋体;\"><br />\n</span> \n</h2>\n<p style=\"text-indent:2em;\">\n	newCachedThreadPool\n</p>\n<p style=\"text-indent:2em;\">\n	创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。示例代码如下：\n</p>\n<div class=\"cnblogs_code\">\n<pre>ExecutorService cachedThreadPool =<span style=\"color:#000000;\"> Executors.newCachedThreadPool(); </span><span style=\"color:#0000ff;\">for</span> (<span style=\"color:#0000ff;\">int</span> i = 0; i &lt; 10; i++<span style=\"color:#000000;\">) { </span><span style=\"color:#0000ff;\">final</span> <span style=\"color:#0000ff;\">int</span> index =<span style=\"color:#000000;\"> i; </span><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\"> try { </span><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\"> Thread.sleep(index * 1000); </span><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\"> } catch (InterruptedException e) { </span><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\"> e.printStackTrace(); </span><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\"> }</span> cachedThreadPool.execute(<span style=\"color:#0000ff;\">new</span><span style=\"color:#000000;\"> Runnable() { </span><span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">void</span><span style=\"color:#000000;\"> run() {\n                    System.out.println(Thread.currentThread().getName() </span>+ \"---\" +<span style=\"color:#000000;\"> index);\n                }\n            });\n        }</span></pre>\n</div>\n<p style=\"text-indent:2em;\">\n	总结: 线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。\n</p>\n<p style=\"text-indent:2em;\">\n	newFixedThreadPool\n</p>\n<p style=\"text-indent:2em;\">\n	创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。示例代码如下：\n</p>\n<div class=\"cnblogs_code\">\n<pre><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\"> 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待</span> <span style=\"color:#0000ff;\">final</span> ExecutorService newCachedThreadPool = Executors.newFixedThreadPool(3<span style=\"color:#000000;\">); </span><span style=\"color:#0000ff;\">for</span> (<span style=\"color:#0000ff;\">int</span> i = 0; i &lt; 10; i++<span style=\"color:#000000;\">) { </span><span style=\"color:#0000ff;\">final</span> <span style=\"color:#0000ff;\">int</span> index =<span style=\"color:#000000;\"> i;\n            newCachedThreadPool.execute(</span><span style=\"color:#0000ff;\">new</span><span style=\"color:#000000;\"> Runnable() { </span><span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">void</span><span style=\"color:#000000;\"> run() { </span><span style=\"color:#0000ff;\">try</span><span style=\"color:#000000;\"> {\n                        Thread.sleep(</span>1000<span style=\"color:#000000;\">);\n                    } </span><span style=\"color:#0000ff;\">catch</span><span style=\"color:#000000;\"> (Exception e) { </span><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\"> TODO: handle exception</span> <span style=\"color:#000000;\"> }\n                    System.out.println(</span>\"i:\" +<span style=\"color:#000000;\"> index);\n                }\n            });\n        }</span></pre>\n</div>\n<p style=\"text-indent:2em;\">\n	总结:因为线程池大小为3，每个任务输出index后sleep 2秒，所以每两秒打印3个数字。<br />\n定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()\n</p>\n<p style=\"text-indent:2em;\">\n	newScheduledThreadPool\n</p>\n<p style=\"text-indent:2em;\">\n	创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例代码如下：\n</p>\n<div class=\"cnblogs_code\">\n<pre><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\"> 创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例代码如下：</span> ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(5<span style=\"color:#000000;\">);\n        newScheduledThreadPool.schedule(</span><span style=\"color:#0000ff;\">new</span><span style=\"color:#000000;\"> Runnable() { </span><span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">void</span><span style=\"color:#000000;\"> run() {\n                System.out.println(</span>\"delay 3 seconds\"<span style=\"color:#000000;\">);\n            }\n        }, </span>3, TimeUnit.SECONDS);</pre>\n</div>\n<p style=\"text-indent:2em;\">\n	表示延迟3秒执行。\n</p>\n<p style=\"text-indent:2em;\">\n	newSingleThreadExecutor<br />\n创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。示例代码如下：\n</p>\n<div class=\"cnblogs_code\">\n<pre>    ExecutorService newSingleThreadExecutor =<span style=\"color:#000000;\"> Executors.newSingleThreadExecutor(); </span><span style=\"color:#0000ff;\">for</span> (<span style=\"color:#0000ff;\">int</span> i = 0; i &lt; 10; i++<span style=\"color:#000000;\">) { </span><span style=\"color:#0000ff;\">final</span> <span style=\"color:#0000ff;\">int</span> index =<span style=\"color:#000000;\"> i;\n            newSingleThreadExecutor.execute(</span><span style=\"color:#0000ff;\">new</span><span style=\"color:#000000;\"> Runnable() {\n\n                @Override </span><span style=\"color:#0000ff;\">public</span> <span style=\"color:#0000ff;\">void</span><span style=\"color:#000000;\"> run() {\n                    System.out.println(</span>\"index:\" +<span style=\"color:#000000;\"> index); </span><span style=\"color:#0000ff;\">try</span><span style=\"color:#000000;\"> {\n                        Thread.sleep(</span>200<span style=\"color:#000000;\">);\n                    } </span><span style=\"color:#0000ff;\">catch</span><span style=\"color:#000000;\"> (Exception e) { </span><span style=\"color:#008000;\">//</span><span style=\"color:#008000;\"> TODO: handle exception</span> <span style=\"color:#000000;\"> }\n                }\n            });\n        }</span></pre>\n</div>\n<p style=\"text-indent:2em;\">\n	注意: 结果依次输出，相当于顺序执行各个任务。\n</p>\n<p style=\"text-indent:2em;\">\n	最后推荐篇：<a id=\"cb_post_title_url\" class=\"postTitle2\" href=\"http://www.cnblogs.com/laoyeye/p/6589150.html\">JAVA线程池应用的DEMO</a> \n</p>', '本文原由小卖铺的老爷爷发表于博客园一、线程三大特性多线程有三大特性，原子性、可见性、有序性1.1什么是原子性即一个操作或者多个操作要么全部执行并且执行的过程不会被任何因素打断，要么就都不执行。一个很经典的例子就是银行账户转账问题：&nbsp;比如从账户A向账户B转1000元，那么必然包括2个操作：从账户A减去1000元，往账户B加上1000元。这2个操作必须要具备原子性才能保证不出现一些意外的问题。我们操作数据也是如此，比如i=i+1；其中就包括，读取i的值，计算i，写入i。这行代码在Java中是不具备原子性的，则多线程运行肯定会出问题，所以也需要我们使用同步和lock这些东西来确保这个特性了。&nbsp;原子性其实就是保证数据一致、线程安全一部分，1.2什么是可见性当多个线程访问同一个变量时，一个线程修改了这个变量的值，其他线程能够立即看得到修改的值。若两个线程在不同的cpu，那么线程1改变了i的值还没刷新到主存，线程2又使用了i，那么这个i值肯定还是之前的，线程1对变量的修改线程没看到这就是可见性问题。&nbsp;1.3什么是有序性程序执行的顺序按照代码的先后顺序执行。一般来说处理器为了提高程序运行效率，可能会对输入代码进行优化，它不保证程序中各个语句的执行先后顺序同代码中的顺序一致，但是它会保证程序最终执行结果和代码顺序执行的结果是一致的。如下：inta=10;//语句1intr=2;//语句2a=a+3;//语句3r=a*a;//语句4则因为重排序，他还可能执行顺序为2-1-3-4，1-3-2-4但绝不可能2-1-4-3，因为这打破了依赖关系。显然重排序对单线程运行是不会有任何问题，而多线程就不一定了，所以我们在多线程编程时就得考虑这个问题了。二：Java内存模型共享内存模型指的就是Java内存模型(简称JMM)，JMM决定一个线程对共享变量的写入时,能对另一个线程可见。从抽象的角度来看，JMM定义了线程和主内存之间的抽象关系：线程之间的共享变量存储在主内存（mainmemory）中，每个线程都有一个私有的本地内存（localmemory），本地内存中存储了该线程以读/写共享变量的副本。本地内存是JMM的一个抽象概念，并不真实存在。它涵盖了缓存，写缓冲区，寄存器以及其他的硬件和编译器优化。从上图来看，线程A与线程B之间如要通信的话，必须要经历下面2个步骤：1.首先，线程A把本地内存A中更新过的共享变量刷新到主内存中去。2.然后，线程B到主内存中去读取线程A之前已更新过的共享变量。下面通过示意图来说明这两个步骤：&nbsp;如上图所示，本地内存A和B有主内存中共享变量x的副本。假设初始时，这三个内存中的x值都为0。线程A在执行时，把更新后的x值（假设值为1）临时存放在自己的本地内存A中。当线程A和线程B需要通信时，线程A首先会把自己本地内存中修改后的x值刷新到主内存中，此时主内存中的x值变为了1。随后，线程B到主内存中去读取线程A更新后的x值，此时线程B的本地内存的x值也变为了1。从整体来看，这两个步骤实质上是线程A在向线程B发送消息，而且这个通信过程必须要经过主内存。JMM通过控制主内存与每个线程的本地内存之间的交互，来为java程序员提供内存可见性保证。总结：什么是Java内存模型：java内存模型简称jmm，定义了一个线程对另一个线程可见。共享变量存放在主内存中，每个线程都有自己的本地内存，当多个线程同时访问一个数据的时候，可能本地内存没有及时刷新到主内存，所以就会发生线程安全问题。三、Volatile关键字3.1什么是VolatileVolatile关键字的作用是变量在多个线程之间可见。classThreadVolatileDemoextendsThread{publicbooleanflag=true;@Overridepublicvoidrun(){System.out.println(\"开始执行子线程....\");while(flag){}System.out.println(\"线程停止\");}publicvoidsetRuning(booleanflag){this.flag=flag;}}publicclassThreadVolatile{publicstaticvoidmain(String[]args)throwsInterruptedException{ThreadVolatileDemothreadVolatileDemo=newThreadVolatileDemo();threadVolatileDemo.start();Thread.sleep(3000);threadVolatileDemo.setRuning(false);System.out.println(\"flag已经设置成false\");Thread.sleep(1000);System.out.println(threadVolatileDemo.flag);}}运行结果:threadVolatileDemo.flag值也是false,可是为什么程序还是一直在运行呢？原因:线程之间是不可见的，读取的是副本，没有及时读取到主内存结果。解决办法：使用Volatile关键字将解决线程之间可见性,强制线程每次读取该值的时候都去“主内存”中取值。3.2Volatile非原子性publicclassVolatileNoAtomicextendsThread{privatestaticvolatileintcount;//privatestaticAtomicIntegercount=newAtomicInteger(0);privatestaticvoidaddCount(){for(inti=0;i&lt;1000;i++){count++;//count.incrementAndGet();}System.out.println(count);}publicvoidrun(){addCount();}publicstaticvoidmain(String[]args){VolatileNoAtomic[]arr=newVolatileNoAtomic[100];//初始化10个线程for(inti=0;i&lt;10;i++){arr[i]=newVolatileNoAtomic();}for(inti=0;i&lt;10;i++){arr[i].start();}}}运行结果:结果发现数据不同步，因为Volatile不用具备原子性。所以Volatile只能解决将将结果刷新到主内存中去，并不能解决并发原子性问题。3.3使用AtomicInteger原子类AtomicInteger是一个提供原子操作的Integer类，通过线程安全的方式操作加减，因此十分适合高并发情况下的使用，来源于java并发包。修改上面的代码：publicclassVolatileNoAtomicextendsThread{privatestaticAtomicIntegeratomicInteger=newAtomicInteger(0);@Overridepublicvoidrun(){for(inti=0;i&lt;1000;i++){//等同于count++atomicInteger.incrementAndGet();}System.out.println(atomicInteger);}publicstaticvoidmain(String[]args){//初始化10个线程VolatileNoAtomic[]volatileNoAtomic=newVolatileNoAtomic[10];for(inti=0;i&lt;10;i++){//创建volatileNoAtomic[i]=newVolatileNoAtomic();}for(inti=0;i&lt;volatileNoAtomic.length;i++){volatileNoAtomic[i].start();}}}运行结果：3.4volatile与synchronized区别仅靠volatile不能保证线程的安全性。（原子性）①volatile轻量级，只能修饰变量。synchronized重量级，还可修饰方法②volatile只能保证数据的可见性，不能用来同步（没有原子性，不能保证线程安全），因为多个线程并发访问volatile修饰的变量不会阻塞。③synchronized不仅保证可见性，而且还保证原子性，因为，只有获得了锁的线程才能进入临界区，从而保证临界区中的所有语句都全部执行。多个线程争抢synchronized锁对象时，会出现阻塞。总结：线程安全性包括两个方面，①可见性。②原子性。从上面自增的例子中可以看出：仅仅使用volatile并不能保证线程安全性。而synchronized则可实现线程的安全性。四、ThreadLocal4.1、什么是ThreadLocalThreadLocal提高一个线程的局部变量，访问某个线程拥有自己局部变量。当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。ThreadLocal的接口方法ThreadLocal类接口很简单，只有4个方法，我们先来了解一下：voidset(Objectvalue)设置当前线程的线程局部变量的值。publicObjectget()该方法返回当前线程所对应的线程局部变量。publicvoidremove()将当前线程局部变量的值删除，目的是为了减少内存的占用，该方法是JDK5.0新增的方法。需要指出的是，当线程结束后，对应该线程的局部变量将自动被垃圾回收，所以显式调用该方法清除线程的局部变量并不是必须的操作，但它可以加快内存回收的速度。protectedObjectinitialValue()返回该线程局部变量的初始值，该方法是一个protected的方法，显然是为了让子类覆盖而设计的。这个方法是一个延迟调用方法，在线程第1次调用get()或set(Object)时才执行，并且仅执行1次。ThreadLocal中的缺省实现直接返回一个null。案例:创建三个线程，每个线程生成自己独立序列号。代码:classRes{//生成序列号共享变量//publicstaticIntegercount=0;//设置本地局部变量，与其他线程互不影响publicstaticThreadLocal&lt;Integer&gt;threadLocal=newThreadLocal&lt;Integer&gt;(){//设置当前线程局部变量初始化值protectedIntegerinitialValue(){return0;};};publicIntegergetNum(){intcount=this.threadLocal.get()+1;this.threadLocal.set(count);returncount;}}publicclassThreadLocaDemo2extendsThread{privateResres;publicThreadLocaDemo2(Resres){this.res=res;}@Overridepublicvoidrun(){for(inti=0;i&lt;3;i++){System.out.println(Thread.currentThread().getName()+\"---\"+\"i---\"+i+\"--num:\"+res.getNum());}}publicstaticvoidmain(String[]args){Resres=newRes();ThreadLocaDemo2threadLocaDemo1=newThreadLocaDemo2(res);ThreadLocaDemo2threadLocaDemo2=newThreadLocaDemo2(res);ThreadLocaDemo2threadLocaDemo3=newThreadLocaDemo2(res);threadLocaDemo1.start();threadLocaDemo2.start();threadLocaDemo3.start();}}4.2、ThreadLocal实现原理源码：publicvoidset(Tvalue){Threadt=Thread.currentThread();ThreadLocalMapmap=getMap(t);if(map!=null)map.set(this,value);elsecreateMap(t,value);}从源码中我们可以看出，ThreadLoca通过map集合，Map.put(“当前线程”,值)；五、线程池5.1&nbsp;什么是线程池？&nbsp;线程池是指在初始化一个多线程应用程序过程中创建一个线程集合，然后在需要执行新的任务时重用这些线程而不是新建一个线程。线程池中线程的数量通常完全取决于可用内存数量和应用程序的需求。然而，增加可用线程数量是可能的。线程池中的每个线程都有被分配一个任务，一旦任务已经完成了，线程回到池子中并等待下一次分配任务。5.2&nbsp;线程池作用基于以下几个原因在多线程应用程序中使用线程是必须的：1.线程池改进了一个应用程序的响应时间。由于线程池中的线程已经准备好且等待被分配任务，应用程序可以直接拿来使用而不用新建一个线程。2.线程池节省了CLR为每个短生存周期任务创建一个完整的线程的开销并可以在任务完成后回收资源。3.线程池根据当前在系统中运行的进程来优化线程时间片。4.线程池允许我们开启多个任务而不用为每个线程设置属性。5.线程池允许我们为正在执行的任务的程序参数传递一个包含状态信息的对象引用。6.线程池可以用来解决处理一个特定请求最大线程数量限制问题。5.3线程池四种创建方式Java通过Executors（jdk1.5并发包）提供四种线程池，分别为：newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。newFixedThreadPool创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。newScheduledThreadPool创建一个定长线程池，支持定时及周期性任务执行。newSingleThreadExecutor创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO,LIFO,优先级)执行。5.4代码DemonewCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。示例代码如下：ExecutorServicecachedThreadPool=Executors.newCachedThreadPool();for(inti=0;i&lt;10;i++){finalintindex=i;//try{//Thread.sleep(index*1000);//}catch(InterruptedExceptione){//e.printStackTrace();//}cachedThreadPool.execute(newRunnable(){publicvoidrun(){System.out.println(Thread.currentThread().getName()+\"---\"+index);}});}总结:线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。newFixedThreadPool创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。示例代码如下：//创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待finalExecutorServicenewCachedThreadPool=Executors.newFixedThreadPool(3);for(inti=0;i&lt;10;i++){finalintindex=i;newCachedThreadPool.execute(newRunnable(){publicvoidrun(){try{Thread.sleep(1000);}catch(Exceptione){//TODO:handleexception}System.out.println(\"i:\"+index);}});}总结:因为线程池大小为3，每个任务输出index后sleep2秒，所以每两秒打印3个数字。定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()newScheduledThreadPool创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例代码如下：//创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例代码如下：ScheduledExecutorServicenewScheduledThreadPool=Executors.newScheduledThreadPool(5);newScheduledThreadPool.schedule(newRunnable(){publicvoidrun(){System.out.println(\"delay3seconds\");}},3,TimeUnit.SECONDS);表示延迟3秒执行。newSingleThreadExecutor创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO,LIFO,优先级)执行。示例代码如下：ExecutorServicenewSingleThreadExecutor=Executors.newSingleThreadExecutor();for(inti=0;i&lt;10;i++){finalintindex=i;newSingleThreadExecutor.execute(newRunnable(){@Overridepublicvoidrun(){System.out.println(\"index:\"+index);try{Thread.sleep(200);}catch(Exceptione){//TODO:handleexception}}});}注意:结果依次输出，相当于顺序执行各个任务。最后推荐篇：JAVA线程池应用的DEMO', '60', '1', '1', '1', '0', '0', '152336334290854', '2018-05-29 20:30:41', '2018-05-31 18:57:34');
INSERT INTO `t_article` VALUES ('152776394790103', '将jar包安装到maven仓库', '152776367270658', 'http://images.laoyeye.net/1527763730193334.jpg', '&lt;!--https://mvnrepository.com/artifact/ojdbc/ojdbc--&gt;&lt;!--(参数一)：下载到本地的ojdbc-10.2.0.4.0.jar包的真实存放路径--&gt;&lt;dependency&gt;&lt;groupId&gt;ojdbc&lt;/groupId&gt;-----------------(参数二)&lt;artifactId&gt;ojdbc&lt;/artifactId&gt;-----------(参', '<pre class=\"layui-code prettyprint\">&lt;!-- https://mvnrepository.com/artifact/ojdbc/ojdbc --&gt;\n&lt;!-- (参数一)：下载到本地的ojdbc-10.2.0.4.0.jar包的真实存放路径 --&gt;\n&lt;dependency&gt;\n&lt;groupId&gt;ojdbc&lt;/groupId&gt;-----------------(参数二)\n&lt;artifactId&gt;ojdbc&lt;/artifactId&gt;-----------(参数三)\n&lt;version&gt;10.2.0.4.0&lt;/version&gt;------------(参数四)\n&lt;/dependency&gt;</pre>\n语法：<br />\n<p>\n	<strong><span style=\"color:#E53333;\">mvn install:install-file -Dfile=jar包的位置(参数一) -DgroupId=groupId(参数二) -DartifactId=artifactId(参数三) -Dversion=version(参数四) -Dpackaging=jar</span></strong>\n</p>\n我把“ojdbc-10.2.0.4.0.jar”放到了“D:\\Program Files\\mvn\\”下<br />\n<p>\n	注意：“Program Files”中间有空格，所以要加双引号，另外三个参数，从上面复制过来即可，下面是我安装ojdbc-10.2.0.4.0.jar包使用的命令：\n</p>\n<strong><span style=\"color:#E53333;\">mvn install:install-file -Dfile=\"D:\\Program Files\\mvn\\ojdbc-10.2.0.4.0.jar\" -DgroupId=com.oracle -DartifactId=ojdbc14 -Dversion=10.2.0.4.0 -Dpackaging=jar</span></strong>\n<p>\n	需要注意以下几点：\n</p>\n<ol>\n	<li>\n		注意\"-\"不能缺少 install后面的\"-\"是没有空格的\n	</li>\n	<li>\n		注意\"-Dfile\"中jar包的路径和jar包的名字.\n	</li>\n	<li>\n		注意看cmd命令提示,查看本地repository中是否成功的复制了jar包.\n	</li>\n</ol>\n重点：Jar包默认都安装在“C:\\Users\\Administrator\\.m2\\repository\\”下，其实上面的(参数二，参数三，参数四)就是指定安装具体的安装路径。<br />\n（以后也可以根据自己需求进行更改参数二，三，四，其实就是更改安装路径）。<br />', '&lt;!--https://mvnrepository.com/artifact/ojdbc/ojdbc--&gt;&lt;!--(参数一)：下载到本地的ojdbc-10.2.0.4.0.jar包的真实存放路径--&gt;&lt;dependency&gt;&lt;groupId&gt;ojdbc&lt;/groupId&gt;-----------------(参数二)&lt;artifactId&gt;ojdbc&lt;/artifactId&gt;-----------(参数三)&lt;version&gt;10.2.0.4.0&lt;/version&gt;------------(参数四)&lt;/dependency&gt;语法：mvninstall:install-file-Dfile=jar包的位置(参数一)-DgroupId=groupId(参数二)-DartifactId=artifactId(参数三)-Dversion=version(参数四)-Dpackaging=jar我把“ojdbc-10.2.0.4.0.jar”放到了“D:\\ProgramFiles\\mvn\\”下注意：“ProgramFiles”中间有空格，所以要加双引号，另外三个参数，从上面复制过来即可，下面是我安装ojdbc-10.2.0.4.0.jar包使用的命令：mvninstall:install-file-Dfile=\"D:\\ProgramFiles\\mvn\\ojdbc-10.2.0.4.0.jar\"-DgroupId=com.oracle-DartifactId=ojdbc14-Dversion=10.2.0.4.0-Dpackaging=jar需要注意以下几点：注意\"-\"不能缺少install后面的\"-\"是没有空格的注意\"-Dfile\"中jar包的路径和jar包的名字.注意看cmd命令提示,查看本地repository中是否成功的复制了jar包.重点：Jar包默认都安装在“C:\\Users\\Administrator\\.m2\\repository\\”下，其实上面的(参数二，参数三，参数四)就是指定安装具体的安装路径。（以后也可以根据自己需求进行更改参数二，三，四，其实就是更改安装路径）。', '16', '0', '1', '1', '0', '0', '152336334290854', '2018-05-31 18:52:28', '2018-06-18 22:40:03');
INSERT INTO `t_article` VALUES ('152933278046718', 'yyblog一个开源的java博客系统', '152933163827668', 'http://images.laoyeye.net/1529332591056229.jpg', '一个java实现的博客系统目前博客后台管理系统已经完成，项目集成QQ登录，腾讯云对象存储等功能2018年6月18号，beat1.0版正式发布，预览地址在&nbsp;www.laoyeye.net&nbsp;。代码在：https://github.com/allanzhuo/yyblog本来这个版本是不准备上传git的，但是因为后面可能架构方面会做较大的调整，想着还是在git上做个记录吧。如果有需要的同学还请方便给我点个star目前前端的页面来自笔记博客，后面会做完全的重构，下个版本', '<p style=\"color:#24292E;font-family:-apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;;font-size:16px;\">\n	一个java实现的博客系统\n</p>\n<p style=\"color:#24292E;font-family:-apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;;font-size:16px;\">\n	目前博客后台管理系统已经完成，项目集成QQ登录，腾讯云对象存储等功能\n</p>\n<p style=\"color:#24292E;font-family:-apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;;font-size:16px;\">\n	2018年6月18号，beat1.0版正式发布，<strong>预览地址在&nbsp;</strong><a href=\"http://www.laoyeye.net/\"><strong>www.laoyeye.net</strong></a><strong>&nbsp; 。 代码在：</strong><strong><a href=\"https://github.com/allanzhuo/yyblog\" target=\"_blank\">https://github.com/allanzhuo/yyblog</a></strong> \n</p>\n<p style=\"color:#24292E;font-family:-apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;;font-size:16px;\">\n	本来这个版本是不准备上传git的，但是因为后面可能架构方面会做较大的调整，想着还是在git上做个记录吧。\n</p>\n<p style=\"color:#24292E;font-family:-apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;;font-size:16px;\">\n	<strong><span style=\"font-size:18px;color:#E53333;\">如果有需要的同学还请方便给我点个star</span></strong><img src=\"http://www.laoyeye.net/static/plugins/nkeditor/plugins/emoticons/images/21.gif\" border=\"0\" alt=\"\" /> \n</p>\n<p style=\"color:#24292E;font-family:-apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;;font-size:16px;\">\n	目前前端的页面来自笔记博客，后面会做完全的重构，下个版本会增加权限管理，主要前后端都会应用Shiro替代目前的springmvc拦截器模式。\n</p>\n<p style=\"color:#24292E;font-family:-apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;;font-size:16px;\">\n	总的来说目前项目还处于初始阶段，但是后面我会不断完善下去的。最后欢迎点赞Star关注本项目，无论是使用还是学习，相信一定不会让你失望的！！\n</p>\n<p style=\"color:#24292E;font-family:-apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;;font-size:16px;\">\n	项目基本上是完全的代码上传，没做任何保留和压缩。\n</p>\n<p style=\"color:#24292E;font-family:-apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;;font-size:16px;\">\n	导入后需要做的是：\n</p>\n<p style=\"color:#24292E;font-family:-apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;;font-size:16px;\">\n	1、修改application.yml的数据库连接\n</p>\n<p style=\"color:#24292E;font-family:-apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;;font-size:16px;\">\n	2、修改qqconnectconfig.properties的QQ互联的相关连接配置，主要是app_ID，app_KEY，和回调地址redirect_URI\n</p>\n<p style=\"color:#24292E;font-family:-apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;;font-size:16px;\">\n	3、修改腾讯云存储的配置，目前在COSClientUtils.java文件中配置，会面会移到数据库配置。\n</p>', '一个java实现的博客系统目前博客后台管理系统已经完成，项目集成QQ登录，腾讯云对象存储等功能2018年6月18号，beat1.0版正式发布，预览地址在&nbsp;www.laoyeye.net&nbsp;。代码在：https://github.com/allanzhuo/yyblog本来这个版本是不准备上传git的，但是因为后面可能架构方面会做较大的调整，想着还是在git上做个记录吧。如果有需要的同学还请方便给我点个star目前前端的页面来自笔记博客，后面会做完全的重构，下个版本会增加权限管理，主要前后端都会应用Shiro替代目前的springmvc拦截器模式。总的来说目前项目还处于初始阶段，但是后面我会不断完善下去的。最后欢迎点赞Star关注本项目，无论是使用还是学习，相信一定不会让你失望的！！项目基本上是完全的代码上传，没做任何保留和压缩。导入后需要做的是：1、修改application.yml的数据库连接2、修改qqconnectconfig.properties的QQ互联的相关连接配置，主要是app_ID，app_KEY，和回调地址redirect_URI3、修改腾讯云存储的配置，目前在COSClientUtils.java文件中配置，会面会移到数据库配置。', '10', '0', '1', '1', '0', '1', '152336334290854', '2018-06-18 22:39:40', '2018-06-18 23:07:34');

-- ----------------------------
-- Table structure for t_cate
-- ----------------------------
DROP TABLE IF EXISTS `t_cate`;
CREATE TABLE `t_cate` (
  `id` bigint(20) NOT NULL,
  `code` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cate
-- ----------------------------
INSERT INTO `t_cate` VALUES ('152759695653794', 'dxc', '多线程');
INSERT INTO `t_cate` VALUES ('152776367270658', 'maven', 'maven');
INSERT INTO `t_cate` VALUES ('152933163827668', 'git', '开源');

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `article_id` bigint(20) NOT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `ip_addr` varchar(50) DEFAULT NULL,
  `ip_cn_addr` varchar(100) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `enable` tinyint(1) NOT NULL DEFAULT '1',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=152776401560637 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES ('152626195065750', '152518101502084', '152277118900681', '<p>1</p>', '127.0.0.1', '中国上海上海', null, '1', '2018-05-14 09:41:10');
INSERT INTO `t_comment` VALUES ('152626294338264', '152518101502084', '152277118900681', '<blockquote class=\"layui-elem-quote layui-quote-nm\">   <a href=\"javascript:;\" class=\"re\">回复@小卖铺的老爷爷</a>：undefined</blockquote><p>哈哈哈</p>', '127.0.0.1', '中国上海上海', null, '1', '2018-05-14 09:55:44');
INSERT INTO `t_comment` VALUES ('152626451544044', '152518101502084', '152277118900681', '<blockquote class=\"layui-elem-quote layui-quote-nm\">   <a href=\"javascript:;\" class=\"re\">回复@小卖铺的老爷爷</a>：<blockquote class=\"layui-elem-quote layui-quote-nm\">   <a href=\"javascript:;\" class=\"re\">回复@小卖铺的老爷爷</a>：undefined</blockquote><p>哈哈哈</p></blockquote><p>1111111111111111</p>', '127.0.0.1', '中国上海上海', null, '1', '2018-05-14 10:21:56');
INSERT INTO `t_comment` VALUES ('152629854337258', '152518101502084', '152277118900681', '<p>chongxin<br></p>', '101.81.12.7', '中国上海上海', null, '1', '2018-05-14 19:49:04');
INSERT INTO `t_comment` VALUES ('152776400603496', '152336334290854', '152759704083198', '<p>测试</p>', '116.231.32.33', '中国上海上海', null, '1', '2018-05-31 18:53:26');
INSERT INTO `t_comment` VALUES ('152776401560636', '152336334290854', '152759704083198', '<blockquote class=\"layui-elem-quote layui-quote-nm\">   <a href=\"javascript:;\" class=\"re\">回复@小卖铺的老爷爷</a>：<p>测试</p></blockquote><p>测试2</p>', '116.231.32.33', '中国上海上海', null, '1', '2018-05-31 18:53:36');

-- ----------------------------
-- Table structure for t_file
-- ----------------------------
DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `url` varchar(500) DEFAULT NULL,
  `post` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_file
-- ----------------------------

-- ----------------------------
-- Table structure for t_keyword
-- ----------------------------
DROP TABLE IF EXISTS `t_keyword`;
CREATE TABLE `t_keyword` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `words` varchar(255) NOT NULL,
  `enable` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=152326873615711 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_keyword
-- ----------------------------

-- ----------------------------
-- Table structure for t_note
-- ----------------------------
DROP TABLE IF EXISTS `t_note`;
CREATE TABLE `t_note` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `text_content` varchar(999) DEFAULT NULL,
  `content` varchar(999) NOT NULL,
  `top` tinyint(1) NOT NULL DEFAULT '0',
  `is_show` tinyint(1) NOT NULL DEFAULT '1',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=152404872681821 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_note
-- ----------------------------

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'sa', '超级管理员');
INSERT INTO `t_role` VALUES ('2', 'user', '网站用户');

-- ----------------------------
-- Table structure for t_setting
-- ----------------------------
DROP TABLE IF EXISTS `t_setting`;
CREATE TABLE `t_setting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL,
  `value` varchar(600) NOT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_setting
-- ----------------------------
INSERT INTO `t_setting` VALUES ('1', 'all_comment_open', '1', '是否全局开放评论');
INSERT INTO `t_setting` VALUES ('2', 'website_title', '小卖铺的老爷爷--未来面前，你我还都是孩子', '网站标题的文字');
INSERT INTO `t_setting` VALUES ('3', 'footer_words', '2015 - 2018', '页脚的文字');
INSERT INTO `t_setting` VALUES ('4', 'index_top_words', '未来面前，我们还都是孩子。', '首页置顶文字');
INSERT INTO `t_setting` VALUES ('5', 'menu_home', '主页', '导航菜单_首页');
INSERT INTO `t_setting` VALUES ('6', 'menu_note', '树洞', '导航菜单_笔记');
INSERT INTO `t_setting` VALUES ('7', 'menu_link', 'GitHub', '导航菜单_额外的链接');
INSERT INTO `t_setting` VALUES ('8', 'menu_link_icon', 'fa fa-github', '导航菜单_额外的链接的字体图标logo');
INSERT INTO `t_setting` VALUES ('9', 'menu_link_href', 'https://github.com/allanzhuo', '导航菜单_额外的链接url');
INSERT INTO `t_setting` VALUES ('10', 'menu_mine', '关于', '导航菜单_关于我');
INSERT INTO `t_setting` VALUES ('11', 'menu_link_show', '1', '是否显示额外的导航链接（譬如github）');
INSERT INTO `t_setting` VALUES ('12', 'wechat_pay', 'http://images.laoyeye.net/1523874512630026.png', '微信付款码');
INSERT INTO `t_setting` VALUES ('13', 'alipay', 'http://images.laoyeye.net/1523874492832284.png', '支付宝付款码');
INSERT INTO `t_setting` VALUES ('14', 'app_id', '', 'qq登录API的app_id');
INSERT INTO `t_setting` VALUES ('15', 'app_key', '', 'qq登录API的app_key');
INSERT INTO `t_setting` VALUES ('16', 'qq_login', '1', '是否开放qq登录');
INSERT INTO `t_setting` VALUES ('17', 'statistics_code', '', '统计代码');
INSERT INTO `t_setting` VALUES ('18', 'info_label', '<p>此处可去后台 偏好设置->网站设置->信息板内容 处自定义文案</p>', '信息板内容');
INSERT INTO `t_setting` VALUES ('19', 'menu_search', '搜索', '导航菜单_搜索');
INSERT INTO `t_setting` VALUES ('20', 'website_logo_words', '小卖铺的老爷爷', '网站logo的文字');
INSERT INTO `t_setting` VALUES ('21', 'comment_notice', '<span style=\"color:#FF5722;\">遵守国家法律法规，请勿回复无意义内容，请不要回复嵌套过多的楼层！</span>', '评论置顶公告');
INSERT INTO `t_setting` VALUES ('22', 'is_set_master', '0', '是否设置了网站管理员');

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=152933278052261 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES ('152285154952392', '洗洗');
INSERT INTO `t_tag` VALUES ('152308852858466', 'ff');
INSERT INTO `t_tag` VALUES ('152309162675104', 'g');
INSERT INTO `t_tag` VALUES ('152309381772208', 'N');
INSERT INTO `t_tag` VALUES ('152336325266493', 'ffff');
INSERT INTO `t_tag` VALUES ('152370710862973', '1');
INSERT INTO `t_tag` VALUES ('152429785033384', 'z');
INSERT INTO `t_tag` VALUES ('152759711378671', '');
INSERT INTO `t_tag` VALUES ('152776394791168', 'java');
INSERT INTO `t_tag` VALUES ('152933278052260', 'github');

-- ----------------------------
-- Table structure for t_tag_refer
-- ----------------------------
DROP TABLE IF EXISTS `t_tag_refer`;
CREATE TABLE `t_tag_refer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `refer_id` bigint(20) NOT NULL,
  `tag_id` bigint(20) NOT NULL,
  `is_show` tinyint(1) NOT NULL DEFAULT '0' COMMENT '默认tag不显示到文章',
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=152933445449542 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_tag_refer
-- ----------------------------
INSERT INTO `t_tag_refer` VALUES ('152776394791213', '152776394790103', '152776394791168', '1', '1');
INSERT INTO `t_tag_refer` VALUES ('152776425381090', '152759704083198', '152776394791168', '1', '1');
INSERT INTO `t_tag_refer` VALUES ('152933445449541', '152933278046718', '152933278052260', '1', '1');

-- ----------------------------
-- Table structure for t_upload
-- ----------------------------
DROP TABLE IF EXISTS `t_upload`;
CREATE TABLE `t_upload` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `disk_path` varchar(255) NOT NULL,
  `virtual_path` varchar(255) NOT NULL,
  `upload` datetime DEFAULT NULL,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_upload
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `qq_num` varchar(20) DEFAULT NULL,
  `role_id` bigint(20) NOT NULL,
  `enable` tinyint(1) DEFAULT '1',
  `open_id` varchar(32) DEFAULT NULL COMMENT 'qq登录api的openid',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=152725083392245 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('152336334290854', 'admin', '管理员', '910c9ad1b9ecf677a41b3b9e3e49ff29', 'http://qzapp.qlogo.cn/qzapp/101469429/6AA052D0E490736B61556FF12F8E69A6/50', null, '1', '1', '', '2018-04-16 10:36:48', '2018-04-17 08:53:32');
