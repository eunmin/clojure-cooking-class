# Leiningen REPL 사용하기

by Eunmin Kim

## 이럴 때 쓰세요

Leiningen 프로젝트에서 REPL 사용하기

## 어떻게 하나요?

### 1. REPL 실행하기

`project.clj`가 있는 디렉토리에서 아래 명령어를 실행합니다.

```bash
$ lein repl
nREPL server started on port 54308 on host 127.0.0.1 - nrepl://127.0.0.1:54308
REPL-y 0.3.7, nREPL 0.2.12
Clojure 1.8.0
Java HotSpot(TM) 64-Bit Server VM 1.8.0_121-b13
    Docs: (doc function-name-here)
          (find-doc "part-of-name-here")
  Source: (source function-name-here)
 Javadoc: (javadoc java-object-or-class-here)
    Exit: Control+D or (exit) or (quit)
 Results: Stored in vars *1, *2, *3, an exception in *e

user=> (+ 1 2)
3
user=>
```
