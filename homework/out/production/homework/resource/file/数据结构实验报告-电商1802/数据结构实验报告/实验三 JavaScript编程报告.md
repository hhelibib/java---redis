# **实验三** **JavaScript****编程**

## **一、实验目的**

•        掌握JavaScript的基本语法；

•        掌握JavaScript的常用内置对象的作用、属性、方法的运用；

•        掌握 JavaScript的程序流程控制语句的运用；

•        理解Javascrip中对象的基本概念；

•        掌握常用的Javascrip内置对象及其属性、方法等；

•        掌握Javascrip中事件处理的方法；

•        掌握BOM和DOM模型中的常见对象及其属性、方法；

•        了解JQuery框架的基本应用。

## **二、实习题目**

1.编写JavaScript程序实现 输出“九九乘法表”（左下三角形形式）。

2. 编写JavaScript程序，测试输入的数是否是素数。

3.编写函数isAnagram(str1,str2)用来检查两个字符串是否互为变位词。变位词指不计顺序的情况下两个单词包含完全相同的字母，不多不少。比如“silent”和“listen”互为变位词。

4.编写函数checkPalindrome(str)判断字符串str是否为回文字符串。如果一个字符串从前往后读和从后往前读是一样的，那么它就是一个回文字符串。例如，“mom”、“dad”以及“noon”都是回文字符串。如，checkPalinadrome("mom")结果为true。

5. 编写Javascript程序,找出符合条件的数。

   (1)页面的标题是“找出符合条件的数”

   (2)页面内容：3号标题显示“找出1000 ~ 9999之间能够被17和13同时整除的整数、个数、累加和”。

   (2)输出格式：每行10个整数。

6.利用Date对象编写程序，判断并输出今天是开学的第几周，星期几。

7.设计一个网页，输入一串用英文逗号分隔的数字字符串。编写程序，输出找出该组数中的最大、最小值、和 。并按从大到小排序后的输出结果（以逗号分隔）。

8.要求用户在文本框中年份，点击判断按钮，用alert函数输出该年是否是闰年的结果。

```
<input type="text" name="txtYear" id="txtYear">  

<input type="button" value="判断" onclick="testLeapYear()"> 

<script>  

function testLeapYear (){  

//你的代码 

} 

</script> 
```

9．要求在文本框中输入用户名，点击“注册”按钮进行提交。当文本框为空时，则弹出提示信息，并取消提交操作。

```
<form name="frmLx1" method="post" onSubmit="return funCheck()">  

姓名：<input type="text" name="txtName"><br>  

<input type="submit" value="注册">  

</form>  

<script>  

function funCheck(){  

​           //你的代码

}  

</script>  
```

10.直接使用Javascript的计时器、DOM模型操作，将当前目录下的10张图片循环显示在网页上。

11.利用JQuery框架编写程序，将当前目录下的10张图片循环显示在网页上。

## **三、实验要求**

1．独立完成网页设计实习内容； 

2.记录实验中出现的问题以及解决办法。

3．实习报告中要有对本次实验的总结。

## 四、实验报告总结

### 解题的思路：

#### 第一题：

通过table表格，将要输出的内容和table通过循环编辑成字符串，然后将结果输出。

#### 第二题

本题主要是获取input标签里面的属性值。

#### 第三题

本题承接上一题，获取到两个input标签里面的属性值之后，属性值是以字符串的形式保存的，然后将字符串转换为数组，通过循环进行一一对应赋值，然后可调用数组的sort()方法，可对里面的字符进行排序，最后把排序的数组调用toString（）方法，转换为字符串，这里转换的字符串的格式和我们之前输入进去的不太一样，元素与元素之间是以逗号隔开的，然后判断字符串是否相等即可。

#### 第四题

此题和第三题类似，由于输入的是字符串，首先判断单词的长度是奇数还是偶数，然后调用subString(index1,index2)方法把字符串进行分割，然后可以类似的方法，将两个字符串转换成数组，但是其中有一个字符串转换成数组的方式是倒序的，这样才能保证这两个字符串是相同顺序的，接下来的方法和上一题类似（有点复杂，但是上一题是这样解决的，然后就直接复制粘贴的）。

#### 第五题

此题算是一道纯编程的题目，用JavaScript输出的时候，用“\n”不能实现换行，但是可采用“\<br>”实现换行。

#### 第六题

创建两个Date实例，一个是开学时间，通过set的方法进行初始化时间，另一个直接获取当前的时间；调用getTime()方法，将时间转换成毫秒，做差，将差值转换除以（1000\*60\*60\*24），结果很大的几率是小数，然后用parseInt()将小数转换成整数,然后将结果除以7，整除的时候不加一，有余数的时候结果加一，获取星期几的时候注意返回值是0~6，0表示的事星期天。

#### 第七题

使用split（str）方法，返回的类型是数组类型，然后调用sort(Array)

#### 第八题

此题没什么需要注意的，和上面差不多。

#### 第九题

这里获取input标签里面的值采用的是：

```
document.getElementsByName("frmLx1")[0].value
```

判断获得的属性值是否为空，为空弹出提示消息。

#### 第十题

本题可采用更换img标签里面的src属性的值，然后内部使用setTime("function()",time)方法实现轮播。

#### 第十一题

本题将十张图片展示出来，然后调用$("#id").hide(),将图片进行隐藏，调用$("#id").show将图片进行显示。

### 遇到的问题以及解决方案：

1、题目2，不能获取input标签中输入的值，查了资料之后才明白了,下面是获取value的两种方法，

```
document.getElementById("inputn").value;
document.getElementsByName("inputn")[0].value
```





