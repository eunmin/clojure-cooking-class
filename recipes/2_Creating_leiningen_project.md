# Leiningen 프로젝트 만들기

by Eunmin Kim

## 이럴 때 쓰세요

Clojure 프로젝트를 만들어 보고 싶습니다.

## 어떻게 하나요?

### 1. Leiningen 설치

[macOS에 Leiningen 설치하기](1_Leiningen_macOS.md)를 보고 Leiningen을 설치합니다.

### 2. 프로젝트 생성

터미널에 아래 명령어를 입력합니다.

```bash
lein new hello
```

### 3. 프로젝트 확인

```bash
cd hello
tree
.
├── CHANGELOG.md
├── LICENSE
├── README.md
├── doc
│   └── intro.md
├── project.clj
├── resources
├── src
│   └── hello
│       └── core.clj
└── test
    └── hello
        └── core_test.clj

6 directories, 7 files
```

## 이런 것도 보세요

* tree 명령어는 Homebrew로 설치할 수 있어요.

```
brew install tree
```
