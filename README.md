# Baz Compiler

This project is my Homework 1 in Compiler class

## Test Environment
OS : Ubuntu 16.04

JAVA Version : 1.8.0_144

Test C Compiler : GNU GCC

IDE : IntelliJ IDEA 2017.2.3

## Excution Example
```
java BazCompiler.class ./Test.baz ./Test.c
```


## Explanation

프로그램 실행시 아규먼트로 입력 파일 위치과 출력 파일 위치를 입력해 주어야합니다.

사용할 수 있는 타입은 true,false,baz 입니다.

대소문자를 구분합니다.

wiki에서 스코프관련 사항은 없어서 스코프가 있다고 생각하고 stack을 이용하여 compiler를 만들었습니다.

컴파일시 baz 문법상 에러가 발견되면 콘솔로 에러를 출력하고 C 파일에서 에러가 출력되도록 구현되어있습니다.

ex) 에러 발생시 C 파일
```
#include <stdio.h>
int main(){
printf("YOU ARE WRONG!\n");
return 0;
}
```
ex ) 에러 발생시 c 파일 출력
```
YOU ARE WRONG!

Process finished with exit code 0
```
## Reference
http://esolangs.org/wiki/Baz
